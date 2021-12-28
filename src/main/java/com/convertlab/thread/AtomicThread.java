package com.convertlab.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThread extends Thread {

    private AtomicInteger count;

    public AtomicThread(AtomicInteger count) {
        this.count = count;
    }

    public void addCount() {
        for(int i = 0; i < 10; i++) {
            count.incrementAndGet();
        }
    }

    @Override
    public void run() {
        addCount();
    }
}