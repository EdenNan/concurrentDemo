package com.convertlab.thread;


import com.convertlab.domain.User;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class ReentrantLockThread extends Thread{
    User user;
    Lock lock;

    public ReentrantLockThread(Lock lock, User user) {
        this.lock = lock;
        this.user = user;
    }

    @SneakyThrows
    public void addCount() {
        for (int i = 0; i < 10; i++) {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    user.setDollar(user.getDollar()+1);
                    System.out.println(getName()+":"+user.getDollar());
                } finally{
                    lock.unlock();
                }
            }
        }
    }


    @Override
    public void run() {
        addCount();
    }
}
