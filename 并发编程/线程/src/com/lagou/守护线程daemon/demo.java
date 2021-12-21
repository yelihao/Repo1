package com.lagou.守护线程daemon;

public class demo {



    /*
    非deamon线程都结束后，deamon线程自动结束(守护那些非守护线程)
    守护线程做维护的工作
     */
    public static void main(String[] args) {

        MyDaemonThread myDaemonThread = new MyDaemonThread();
        //将当前线程设置为守护线程
        myDaemonThread.setDaemon(true);

        MyThread myThread = new MyThread();
        myDaemonThread.start();
        myThread.start();
    }


}
