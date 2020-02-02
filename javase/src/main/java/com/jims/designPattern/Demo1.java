package com.jims.designPattern;

import org.junit.jupiter.api.Test;

/**
 *  饿汉式内部类创建单例
 *  好处：既可以实现延迟加载，也不会使用同步降低性能
 *  在内部类被加载和初始化时，菜创建instance实例对象
 *  静态内部类不会自动随着外部类的加载和初始化而初始化，它是单独去加载和初始化的
 * @author JIMS
 * @create 2020-01-30-11:19
 */
public class Demo1 {

    private Demo1() {
        System.out.println("Singleton is create");
    }
    //未实现延迟加载，外部类加载就初始化了
    //private static Demo1 demo1 = new Demo1();

    //静态内部类实现延迟加载
    private static class Singleton {
        private static Demo1 instance = new Demo1();
    }

    public static Demo1 getInstance() {
        return Singleton.instance;//创建单例
    }

}

class Test1 {
    @Test
    public void test2() {
//        Demo1.getInstance();加载类的时候会调用类public修饰的无参构造
        Demo1.getInstance();//创建对象的时候才会调用类中private修饰的无参构造
    }
}
