package com.tesla.framework.network.http.interceptor;

import android.content.Context;

import com.tesla.framework.common.util.ContextUtil;
import com.tesla.framework.common.util.network.NetworkHelper;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author: REXZOU<br>
 * 用于默认的缓存策略,需要权限android.permission.ACCESS_NETWORK_STATE,会判断的请求的Header中是否包含有MIGCache:enable，如果包括，则有缓存，否则不缓存
 * 示例，需要缓存 MIG-Cache:enable, online=30, offline=864000
 * 或使用默认时间 MIG-Cache:enable
 * 不需要缓存 不写Header或MIG-Cache:disable
 * date 2016/2/29 14:52 <br>
 * copyright TCL-MIG<br>
 */
public class CacheInterceptor implements Interceptor {

    private static final String TAG = "CacheInterceptor";
    public static final String DECREPT_KEY = "OGUiRDpWHv6B7cW&";
    private static final long DEFAULT_ONLINE_TIMEOUT = 60;  //60秒
    private static final long DEFAULT_OFFLINE_TIMEOUT = 60 * 60 * 24 * 28;  //一个月
    private final Context mContext;
    private  long mOnlineTimeout = DEFAULT_ONLINE_TIMEOUT;
    private  long mOfflineTimout = DEFAULT_OFFLINE_TIMEOUT;
    //space+默认不进行压缩，鹰眼api时候需要设置为true
    private boolean isGzipEncode;

    public CacheInterceptor(Context context) {
        this(context, DEFAULT_ONLINE_TIMEOUT, DEFAULT_OFFLINE_TIMEOUT);
    }

    public CacheInterceptor(Context context, boolean gzipEncode){
        this(context);
        isGzipEncode=gzipEncode;
    }

    /**
     *
     * @param context
     * @param onlineTimeout 有网络时的缓存有效时间
     * @param offlineTimeout 无网络时的缓存有效时间
     */
    public CacheInterceptor(final Context context, final long onlineTimeout, final long offlineTimeout) {
        mContext = context.getApplicationContext();
        mOnlineTimeout = onlineTimeout;
        mOfflineTimout = offlineTimeout;
    }
    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String cacheControl = request.cacheControl().toString();
        boolean networkAvailable = NetworkHelper.isNetworkAvailable(ContextUtil.getContext());
        //没有网络，则强制缓存
        if (!networkAvailable) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (networkAvailable) {
            //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
            long maxAge = DEFAULT_ONLINE_TIMEOUT;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .removeHeader("Pragma")
                    .build();
        } else {
            long maxStale = DEFAULT_OFFLINE_TIMEOUT;
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .removeHeader("Pragma")
                    .build();
        }
    }

}
