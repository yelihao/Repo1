package com.lagou.syn关键字;

public class ProducerThread extends Thread{
    private int index = 0;

    public final Myqueue myQueue;

    public ProducerThread(Myqueue myQueue){
        this.myQueue = myQueue;
    }


    @Override
    public void run() {
        while (true) {
            myQueue.put("生产数据" + index++);
            System.out.println("生产数据" + index++);
        }
    }
}
