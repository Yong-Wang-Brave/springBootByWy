package com.wy.demo.启动时自动执行代码;

import org.springframework.stereotype.Component;

/*总结
        Spring应用启动过程中，肯定是要自动扫描有@Component注解的类，加载类并初始化对象进行自动注入。加载类时首先要执行static静态代码块中的代码，之后再初始化对象时会执行构造方法。

        在对象注入完成后，调用带有@PostConstruct注解的方法。当容器启动成功后，再根据@Order注解的顺序调用CommandLineRunner和ApplicationRunner接口类中的run方法。

        因此，加载顺序为static>constructer>@PostConstruct>CommandLineRunner和ApplicationRunner.*/
@Component
public class TestPostConstruct {

    static {
        System.out.println("static");
    }
    public TestPostConstruct() {
        System.out.println("constructer");
    }

/*    @PostConstruct
    public void init() {
        System.out.println("PostConstruct");

//启动10个线程
        IntStream.rangeClosed(1, 10).mapToObj(i -> new Thread(() -> {
            while (true) {
                //每一个线程都是一个死循环，休眠10秒，打印10M数据
                String payload = IntStream.rangeClosed(1, 1)
                        .mapToObj(__ -> "a")
                        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(payload.length());
            }
        })).forEach(Thread::start);


        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }*/
}