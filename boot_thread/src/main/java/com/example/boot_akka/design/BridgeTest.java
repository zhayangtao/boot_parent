package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public class BridgeTest {
    public static void main(String[] args) {
        Bridge mysqlBridge = new BridgeImpl();

        Driver mysqlDriver = new MysqlDriver();
        mysqlBridge.setDriver(mysqlDriver);
        mysqlBridge.connect();
    }
}
