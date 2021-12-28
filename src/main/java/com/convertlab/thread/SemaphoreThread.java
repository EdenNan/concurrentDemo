package com.convertlab.thread;

import com.convertlab.domain.User;
import lombok.SneakyThrows;
import java.util.concurrent.Semaphore;


public class SemaphoreThread extends Thread {
    Semaphore semaphore;
    User user;

    public SemaphoreThread(Semaphore semaphore,User user) {
        this.semaphore = semaphore;
        this.user = user;
    }


    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                semaphore.acquire();
                user.setDollar(user.getDollar()+1);
                System.out.println(getName()+":"+user.getDollar());
            } finally{
                semaphore.release();
            }
        }
    }
}
