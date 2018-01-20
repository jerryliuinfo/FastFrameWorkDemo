package com.hawk.fast.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






    }

    private HashMap<Integer,Object> mMap = new HashMap<>();

    private static HashMap<Integer,Integer> map = new HashMap<>();
    public static void main(String[] args) {

        System.out.println("getCanonicalName = "+ MainActivity.class.getCanonicalName());
        System.out.println("getName = "+ MainActivity.class.getName());


       /* map.put("one", "zhangsan");
        String previous = map.put("two","lisi");
        System.out.println("previous = "+previous);
        testLruCache();*/

        //logMap(map);




        //testThreadLocal();
        //testClassLoader();
        testHashSet();

        UploadThread uploadThread = new UploadThread();
        uploadThread.start();
        System.out.println("uploadThread run after");


    }

    private static void testClassLoader(){
        ClassLoader loader = MainActivity.class.getClassLoader();
        while (loader != null){
            System.out.println("loader = "+ loader);
            loader = loader.getParent();
        }
        System.out.println("loader = "+ loader);
    }

    private static ThreadLocal sThreadLocal = new ThreadLocal();
    private static final Object mLock = new Object();


    private static void testThreadLocal(){
        sThreadLocal.set("main");
        System.out.println("main thread : "+ sThreadLocal.get());
        for (int i = 0 ;i < 2; i++){

            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (mLock){
                        System.out.println("thread = "+Thread.currentThread() +", value = "+ sThreadLocal.get());
                        sThreadLocal.set("runable "+ finalI);
                        System.out.println("thread = "+Thread.currentThread() +", value = "+ sThreadLocal.get());
                    }


                }
            }).start();
        }
    }


    public static void testLruCache(){
        LinkedHashMap<Integer,Integer> linkedHashMap = new LinkedHashMap<>(0,0.75f,true);
        linkedHashMap.put(0,0);
        linkedHashMap.put(1,1);
        linkedHashMap.put(2,2);
        linkedHashMap.put(3,3);
        linkedHashMap.put(4,4);
        linkedHashMap.put(5,5);

        linkedHashMap.get(1);
        linkedHashMap.get(2);

        logMap(linkedHashMap);


    }


    private static void testHashSet(){
        Set set = new HashSet();
        set.add(23);
        set.add(55);
        set.add(new String("AA"));         //重写了equals和hashcode方法
        Student stu1 = new Student("001","xi");   //没重写时，用object的equals方法比较的是地址值
        Student stu2 = new Student("001","xi");  //重写后，后面元素不能添加了
        System.out.println(stu1.hashCode());
        System.out.println(stu2.hashCode());
        set.add(stu1);
        set.add(stu2);
        System.out.println(set.size());
        System.out.print(set);
    }


    private static void logMap(Map<Integer,Integer> map){

        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            System.out.println("key = "+ entry.getKey() +", value = "+entry.getValue());
        }
    }
}
