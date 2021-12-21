package com.lagou.syn关键字;

public class CounsumerThread extends Thread{


    public final Myqueue myQueue;

    public CounsumerThread(Myqueue myQueue){
        this.myQueue = myQueue;
    }


    @Override
    public void run() {
        while (true) {
            String s = myQueue.get();
            System.out.println("\t\t消费的数据" + s);
        }
    }
}
