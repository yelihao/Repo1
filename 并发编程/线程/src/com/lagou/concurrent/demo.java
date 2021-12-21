package com.lagou.concurrent;

public class demo {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        //t1.start();
        //System.out.println(Thread.currentThread().getName());

        //方式二
        new Thread(new MyRunnable()).start();


    }



}
