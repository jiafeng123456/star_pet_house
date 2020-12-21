package org.star_pet_house_commons.utiltest;

/*
 *@description:
 *@author jiafeng
 *@date 2020/12/17 0017 18:16
 */
public class ThreadTest extends Thread{

    public static void main(String[] args)
    {
        final Object lock=new Object();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run()
            {
                synchronized (lock) {
                    try {
                        lock.wait();
                        System.out.println("线程开始工作");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });t1.start();
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    lock.notify();
                    System.out.println("唤醒在lock对象上等待的一个线程------》");
                }
            }
        });t2.start();
    }
}
