package com.example.boot_akka;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/03/28
 * 挑战无锁算法，无锁的 Vector 实现
 */
public class LockFreeVector<E> {
    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private static final int N_BUCKET = 30;

    public LockFreeVector() {
        this.buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
    }

    private static class Descriptor<E> {
        public int size;

    }

    private static class WriteDescriptor<E> {
        private E oldV;
        private E newV;
        private AtomicReferenceArray<E> addr;
        private int addr_ind;

        public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_ind, E oldV, E newV) {
            this.addr = addr;
            this.addr_ind = addr_ind;
            this.oldV = oldV;
            this.newV = newV;
        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);
        }
    }
}
