package com.example.boot_akka.design;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/23
 */
public abstract class Bridge {
    private Driver driver;

    public void connect() {
        driver.connect();
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
