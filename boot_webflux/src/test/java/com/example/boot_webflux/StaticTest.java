package com.example.boot_webflux;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @author zhayangtao
 * @version 1.0
 * @since 2018/05/03
 */
public class StaticTest {

    public static void main(String[] args) {
        new Leaf();
        System.out.println("--------------------");
        new Leaf();

        StaticTest test = null;
        test.test();
    }

    private static void test() {
        System.out.println("static test");
    }
}

class Root {
    static {
        System.out.println("Root 的静态初始化块");
    }

    {
        System.out.println("Root 的普通初始化块");
    }

    Root() {
        System.out.println("Root no constructor");
    }
}

class Mid extends Root {
    static {
        System.out.println("Mid 的静态初始化块");
    }

    {
        System.out.println("Mid 的普通初始化块");
    }

    public Mid() {
        System.out.println("Mid no constructor");
    }

    Mid(String msg) {
        System.out.println("Mid with param constructor");
    }
}

class Leaf extends Mid {
    static {
        System.out.println("Leaf 的静态初始化块");
    }

    {
        System.out.println("Leaf 的普通初始化块");
    }

    Leaf() {
        super("super");
        System.out.println("执行 Leaf 的构造器");
    }
}

class FinalVariableTest {
    final int a = 6;
    final String str;
    final int c;
    final static double d;

    {
        str = "Hello";
    }

    static {
        d = 5.6;
    }

    public FinalVariableTest() {
        c = 5;
    }
}

class FinalLocalVariableTest {
    public void test(final int a) {
//        a = 5;
    }

    public static void main(String[] args) {
        final String str = "hello";
        final double d;
        d = 5.6;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Person {
    private int age;
}

class FinalReferenceTest {
    public static void main(String[] args) {
        final int[] iArr = {5, 6, 12, 9};
        iArr[2] = -8;
        final Person person = new Person(45);
        person.setAge(23);
//        person = null;
    }
}

// 宏变量
class FinalReplaceTest {
    public static void main(String[] args) {
        final int a = 5 + 2;
        final double b = 1.2 / 3;
        final String str = "crazy" + "Java" + 99;
        final String str2 = "crazy" + "Java" + String.valueOf(99);
        System.out.println(str == "crazyJava99");
        System.out.println(str2 == "crazyJava99");
    }
}

/**
 * 设计一个缓存类
 */
class CacheImmutable {
    private static int MAX_SIZE = 10;
    private static CacheImmutable[] cache = new CacheImmutable[MAX_SIZE];
    private static int pos = 0;
    private final String name;

    private CacheImmutable(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CacheImmutable valueOf(String name) {
        // 遍历已经缓存的对象
        for (int i = 0; i < MAX_SIZE; i++) {
            if (cache[i] != null && cache[i].getName().equals(name)) {
                return cache[i];
            }
        }
        // 如果缓存池已满
        if (pos == MAX_SIZE) {
            // 把缓存的第一个对象覆盖
            cache[0] = new CacheImmutable(name);
            pos = 1;
        } else {
            // 把新创建的对象缓存起来，pos 加 1
            cache[pos++] = new CacheImmutable(name);
        }
        return cache[pos - 1];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheImmutable that = (CacheImmutable) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}