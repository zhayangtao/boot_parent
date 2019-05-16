package com.example.boot_akka;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/07
 */
public class MultiThreadEchoServer {
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private static class HandleMsg implements Runnable {
        Socket clientSocket;

        HandleMsg(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (PrintWriter os = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                // 从 InputStream 读取客户端发送的数据
                String inputLine = null;
                long b = System.currentTimeMillis();
                while ((inputLine = is.readLine()) != null) {
                    os.println(inputLine);
                }
                long e = System.currentTimeMillis();
                System.out.println("spend:" + (e - b) + "ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ServerSocket echoServer = null;
        try {
            echoServer = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                Socket clientSocket = echoServer.accept();
                System.out.println(clientSocket.getRemoteSocketAddress() + "connect!");
                executorService.execute(new HandleMsg(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
