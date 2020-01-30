package com.jims.designPattern;

import org.junit.jupiter.api.Test;

/**
 * 懒汉式创建单例
 * @author JIMS
 * @create 2020-01-30-13:02
 */
public class Demo2 {

    //volatile 值发生了变更，该线程会把实例化对象写入主内存，其他线程立马可见，避免出现脏读的现象
    private static volatile Demo2 demo2 = null;

    private Demo2() {
        System.out.println(Thread.currentThread().getName());
    }
    //方法上加synchronized，效率低，只需要对实例化进行同步
    /*public static synchronized Demo2 getInstance() {
        if (demo2 == null) {
            demo2 = new Demo2();
        }
        return demo2;
    }*/

    public static Demo2 getInstance() {
        if (demo2 == null) {
            synchronized (Demo2.class) {
                if (demo2 == null) {//二次判断，防止已经实例化的线程再次进入同步方法内
                    demo2 = new Demo2();
                }
            }
        }
        return demo2;
    }
}

class Test3 {

    @Test
    public void test4() {
        /*for (int i = 0; i <3; i++) {
            new Thread() {
                public void run() {
                    System.out.println(Demo2.getInstance());
                }
            }.start();
        }*/
        //lambda表达式改进
        for (int i = 0; i < 3; i++) {
            new Thread(()->
                System.out.println(Demo2.getInstance())
            ).start();

        }
    }
}