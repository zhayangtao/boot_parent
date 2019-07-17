package com.example.boot_akka.design;

import java.util.Collections;
import java.util.Map;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2019/05/29
 * 职责链模式
 * 定义：为了避免请求的发送者和接收者之间的耦合关系，使多个接受对象都有机会处理请求。
 * 将这些对象连成一条链，并沿着这条链传递该请求，直到有一个对象处理它为止。
 */
public class Order {
    private int x;
    private int y;
    private Map<String, Integer> order;

    public Order(int x, int y, Map<String, Integer> order) {
        this.x = x;
        this.y = y;
        this.order = order;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Integer> order) {
        this.order = order;
    }

    interface Subbranch {
        void setSuccessor(Subbranch successor);

        boolean handleOrder(Order order);
    }

    static class McSubbranch implements Subbranch {
        public static final int MIN_DISTANCE  = 500;//假设是500米以内送餐
        private static int count;//类计数
        private final int number;//分店数
        private int x;//横坐标
        private int y;//纵坐标
        private Map<String, Integer> menu;//菜单
        private Subbranch nextSubbranch;//下一家分店

        public McSubbranch(int x, int y, Map<String, Integer> menu) {
            super();
            this.x = x;
            this.y = y;
            this.menu = menu;
            number = ++count;
        }

        /**
         * 设置下一家分店
         * @param successor
         */
        @Override
        public void setSuccessor(Subbranch successor) {
            this.nextSubbranch = successor;
        }

        /**
         * 按照职责链处理订单
         * @param order
         * @return
         */
        @Override
        public boolean handleOrder(Order order) {
            if (CommonUtils.getDistance(order.getX(), order.getY(), this.x, this.y)
                    < MIN_DISTANCE && !CommonUtils.outOfStock(menu, order.getOrder())) {
                for (String name : order.getOrder().keySet()) {
                    menu.put(name, menu.get(name) - order.getOrder().get(name));
                }
                System.out.println("订餐成功，接受订单的分店是：" + this);
                return true;
            }
            if (nextSubbranch == null) {
                return false;
            }
            return nextSubbranch.handleOrder(order);
        }

        public Map<String, Integer> getMenu() {
            return Collections.unmodifiableMap(menu);
        }

        public Subbranch getNextSubbranch() {
            return nextSubbranch;
        }

        public String toString() {
            return "麦当劳分店第" + number + "个";
        }
    }


    public static void main(String[] args) {
        test2();
        test1();
    }

    public static void test1(){
        long startTIme = System.nanoTime();
        int num = 0b11111111111111111111111111111111;
        int count = Integer.toBinaryString(num).replace("0","").length();
        long endTime = System.nanoTime();
        System.out.println(count);
        System.out.println(endTime-startTIme);  // 23894(ns)

    }

    public static void test2() {
        long startTime = System.nanoTime();
        int num = 0b11111111111111111111111111111111;
        int count = 0;
        while(num!=0){
            num = num & (num-1);
            count ++;
        }
        long endTime = System.nanoTime();
        System.out.println(count);
        System.out.println(endTime-startTime);  // 931(ns)
    }
}
