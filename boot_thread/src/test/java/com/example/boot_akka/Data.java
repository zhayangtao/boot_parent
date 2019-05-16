package com.example.boot_akka;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/04/03
 */
public interface Data {
    String getResult();

    static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        System.out.println("数据=" + data.getResult());
    }

}

class FutureData implements Data {
    private RealData realData = null;
    private boolean isReady = false;

    void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        return realData.getResult();
    }
}

class RealData implements Data {
    private final String result;

    RealData(String result) {
        StringBuilder stringBuffer = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuffer.append(result);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        this.result = stringBuffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}

class Client {
    Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread(() -> {
            RealData realData = new RealData(queryStr);
            futureData.setRealData(realData);
        }).start();
        return futureData;
    }
}