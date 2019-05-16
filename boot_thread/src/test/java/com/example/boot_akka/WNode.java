package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/09
 */
public class WNode {
    volatile WNode prev;
    volatile WNode next;
    volatile WNode cowait; // 读节点链表
    volatile Thread thread; // 当可能被暂停时非空
    volatile int status; // 0, WAITING, OR CANCELLED
    final int mode; //RMODE OR WMODE

    WNode(int m, WNode prev) {
        mode = m;
    }
}
