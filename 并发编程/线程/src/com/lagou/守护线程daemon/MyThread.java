package com.lagou.守护线程daemon;

public class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0;i < 10;i++){
            System.out.println("非Deamon线程");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
