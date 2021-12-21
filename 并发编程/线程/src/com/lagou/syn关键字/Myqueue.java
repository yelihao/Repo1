package com.lagou.syn关键字;

public class Myqueue {

    private String[] data = new String[10]; //存放数据


    //下一条要存储记录的下标
    private int putIndex = 0;
    //下一条要获取的元素下标
    private int getIndex = 0;
    //data中的元素个数
    private int size = 0;



    public synchronized void put(String element){
        if (size == data.length){
            try {
                //阻塞，等待
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data[putIndex] = element;
        //唤醒等待的消费者线程    //如果有等待，肯定是消费者在等待
        notify();

        size++;
        putIndex++;
        if (putIndex == data.length) putIndex = 0;
    }

    public synchronized String get() {
        if (size == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String res = data[getIndex];
        getIndex++;
        if (getIndex == data.length) getIndex = 0;
        size--;
        //唤醒生产者生产，因为对象锁是当前对象，this
        notify();
        return res;

    }

}
