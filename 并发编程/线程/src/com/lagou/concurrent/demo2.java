package com.lagou.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class demo2 {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,5,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10)
        ){
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                //如果在call方法执行过程中有错误，则可以在此处进行处理
                super.afterExecute(r, t);
            }
        };

    }



}
