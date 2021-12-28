package com.convertlab.concurrentdemo;

import com.convertlab.domain.User;
import com.convertlab.thread.AtomicThread;
import com.convertlab.thread.ReentrantLockThread;
import com.convertlab.thread.SemaphoreThread;
import com.convertlab.thread.SynchronizedThread;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootTest
class ConcurrentDemoApplicationTests {

    @Test
    void AtomicThread() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        Thread thread = new AtomicThread(count);
        Thread thread1 = new AtomicThread(count);
        thread.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("finally count:"+count);
    }

    @Test
    void ReentrantLockThread() throws InterruptedException {
        User user = new User();
        user.setName("Eden");
        user.setDollar(0);
        ReentrantLock lock = new ReentrantLock(true);
        Thread thread = new ReentrantLockThread(lock,user);
        thread.setName("thread");
        Thread thread1 = new ReentrantLockThread(lock,user);
        thread1.setName("thread1");
        thread.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("finally count:"+user.getDollar());
    }

    @Test
    void SemaphoreThread() throws InterruptedException {
        User user = new User();
        user.setDollar(0);
        user.setName("Eden");
        Semaphore semaphore = new Semaphore(2);
        Thread thread = new SemaphoreThread(semaphore,user);
        thread.setName("线程A");
        Thread thread1 = new SemaphoreThread(semaphore,user);
        thread1.setName("线程B");
        Thread thread2 = new SemaphoreThread(semaphore,user);
        thread2.setName("线程C");
        thread.start();
        thread1.start();
        thread2.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("finally count:"+user.getDollar());
    }


    @Test
    void SynchronizedThread() throws InterruptedException {
        User user = new User();
        user.setName("Eden");
        user.setDollar(0);
        Thread thread = new SynchronizedThread(user);
        thread.setName("thread");
        Thread thread1 = new SynchronizedThread(user);
        thread1.setName("thread1");
        thread.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("finally count:"+user.getDollar());
    }

}
