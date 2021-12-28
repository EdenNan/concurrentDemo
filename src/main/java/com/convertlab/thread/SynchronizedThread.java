package com.convertlab.thread;

import com.convertlab.domain.User;

public class SynchronizedThread extends Thread {
    User user;

    public SynchronizedThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized(SynchronizedThread.class){
                user.setDollar(user.getDollar()+1);
                System.out.println(getName() + ":" + user.getDollar());
            }
        }
    }
}