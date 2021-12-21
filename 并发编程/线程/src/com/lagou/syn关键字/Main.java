package com.lagou.syn关键字;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Myqueue myqueue = new Myqueue();
        CounsumerThread c = new CounsumerThread(myqueue);
        ProducerThread p = new ProducerThread(myqueue);
        c.start();
        p.start();
        Thread.sleep(5000);
        //进程结束
        System.exit(0);
    }

}
