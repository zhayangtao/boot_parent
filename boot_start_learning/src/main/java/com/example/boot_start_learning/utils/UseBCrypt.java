package com.example.boot_start_learning.utils;

import java.util.Scanner;

import static com.example.boot_start_learning.utils.BCrypt.checkpw;
import static com.example.boot_start_learning.utils.BCrypt.gensalt;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/10
 */
public class UseBCrypt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("密码：");
        String password = scanner.nextLine();
        System.out.println("待验证密码：");
        String candidate = scanner.nextLine();

        long start = System.currentTimeMillis();
        String hashed = BCrypt.hashpw(password, gensalt());//密文
        System.out.println("加密耗时：" + (System.currentTimeMillis() - start));
        String hashed2 = BCrypt.hashpw(password, gensalt(12));
        System.out.println(hashed);
        System.out.println(hashed2);

        hashed = "$2a$10$yYSu8VKLwZhW3nijfcdRbeRwZxJGPO5jtomjRN4Yabz6cBFPq/IWa";
        if (checkpw("1234567", hashed)) {
            System.out.println("It matches");
        } else {
            System.out.println("It does not match");
        }

    }
}
