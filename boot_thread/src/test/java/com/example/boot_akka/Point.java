package com.example.boot_akka;

import java.util.concurrent.locks.StampedLock;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class Point {
    private double x, y;
    private final StampedLock stampedLock = new StampedLock();

    /**
     * 排它锁
     * @param deltaX
     * @param deltaY
     */
    void move(double deltaX, double deltaY) {
        long stamp = stampedLock.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * 只读方法
     * @return
     */
    double distanceFromOrigin() {
        long stamp = stampedLock.tryOptimisticRead();
        double currentX = x, currentY = y;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
