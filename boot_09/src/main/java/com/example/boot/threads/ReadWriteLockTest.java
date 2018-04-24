package com.example.boot.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/04/19
 * 读写锁测试
 */
public class ReadWriteLockTest {

    public static void main(String[] args) {
        // 创建一个非公平锁对象
        ReadWriteLock lock = new ReentrantReadWriteLock(false);
        // 创建一个线程池
        ExecutorService pool = Executors.newCachedThreadPool();
        // 设置一个账号，初始金额 10000
        Account account = new Account(lock, "12346", 10000);

        // 账号取钱10次，存钱10次，查询20次
        for (int i = 0; i < 10; i++) {
            Operation operation1 = new Operation(account, "take");
            Operation operation2 = new Operation(account, "query");
            Operation operation3 = new Operation(account, "save");
            Operation operation4 = new Operation(account, "query");
            pool.execute(operation1);
            pool.execute(operation2);
            pool.execute(operation3);
            pool.execute(operation4);
        }

        pool.shutdown();

        while (!pool.isTerminated()) {
            // 等待所有任务结束
        }
        System.out.println("账号" + account.getAccountNo() + " 最后金额：" + account.getMoney());
    }
}

class Operation implements Runnable {

    private Account account;
    private String type;

    Operation(Account account, String type) {
        this.account = account;
        this.type = type;
    }

    @Override
    public void run() {
        if ("take".equals(type)) {
            // 获取写锁
            account.getLock().writeLock().lock();
            account.setMoney(account.getMoney() - 100);
            System.out.println("取走100，账号" + account.getAccountNo() + " 还有" + account.getMoney() + "元");
            // 释放写锁
            account.getLock().writeLock().unlock();
        } else if ("query".equals(type)) {
            // 获取读锁
            account.getLock().readLock().lock();
            System.out.println("查询账号" + account.getAccountNo() + " 还有" + account.getMoney() + "元");
            // 释放读锁
            account.getLock().readLock().unlock();
        } else if ("save".equals(type)) {
            // 获取写锁
            account.getLock().writeLock().lock();
            account.setMoney(account.getMoney() + 100);
            System.out.println("存入100元，账号" + account.getAccountNo() + " 还有" + account.getMoney() + "元");
            // 释放写锁
            account.getLock().writeLock().unlock();
        }
    }
}

class Account {
    private ReadWriteLock lock; // 读写锁
    private String accountNo; // 账号
    private int money;

    Account(ReadWriteLock lock, String accountNo, int money) {
        this.lock = lock;
        this.accountNo = accountNo;
        this.money = money;
    }

    public ReadWriteLock getLock() {
        return lock;
    }

    public void setLock(ReadWriteLock lock) {
        this.lock = lock;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}