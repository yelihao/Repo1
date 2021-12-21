package com.lagou.concurrent;
/*
创建线程的两种方式
 */
//方式一：继承Thread类，重写run方法
public class MyThread extends Thread{
    @Override
    public void run() {
        System.out.println(getName() +" run success");
    }
}
