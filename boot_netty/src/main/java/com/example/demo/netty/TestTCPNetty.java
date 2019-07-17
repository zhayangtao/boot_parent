package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.concurrent.ThreadFactory;

/**
 * @author no one
 * @version 1.0
 * @since 2019/06/11
 */
public class TestTCPNetty {

    public static void main(String[] args) {
        // 服务启动器
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //=============设置 boss 线程池
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
        // work 线程池，这样的申明方式，用于说明 netty 线程组如何工作
        ThreadFactory threadFactory = new DefaultThreadFactory("work thread pool");
        // cpu 个数
        int processorsNumber = Runtime.getRuntime().availableProcessors();
        EventLoopGroup workLoopGroup = new NioEventLoopGroup(processorsNumber);
        // 指定 Netty 的 boss 线程和 work 线程
        serverBootstrap.group(eventLoopGroup, workLoopGroup);
        // 设置服务通道类型
        serverBootstrap.channel(NioServerSocketChannel.class);


    }
}
