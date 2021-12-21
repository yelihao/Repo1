package com.lagou.concurrent;


/*
方式二，实现接口
 */
public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现接口的线程启动起来了");
    }
}
