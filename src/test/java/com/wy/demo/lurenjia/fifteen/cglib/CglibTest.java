package com.wy.demo.lurenjia.fifteen.cglib;

import org.junit.Test;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

public class CglibTest {
    @Test
    public void test1() {
//使用Enhancer来给某个类创建代理类，步骤
//1.创建Enhancer对象
        Enhancer enhancer = new Enhancer();
//2.通过setSuperclass来设置父类型，即需要给哪个类创建代理类
        enhancer.setSuperclass(Service1.class);
/*3.设置回调，需实现org.springframework.cglib.proxy.Callback接口，
此处我们使用的是org.springframework.cglib.proxy.MethodInterceptor，也是一个接
口，实现了Callback接口，
当调用代理对象的任何方法的时候，都会被MethodInterceptor接口的invoke方法处理*/
        enhancer.setCallback(new MethodInterceptor() {
            /**
             * 代理对象方法拦截器
             * @param o 代理对象
            上面代码中的注释很详细，列出了给指定的类创建代理的具体步骤，整个过程中主要用到了
            Enhancer类和 MethodInterceptor 接口。
            enhancer.setSuperclass 用来设置代理类的父类，即需要给哪个类创建代理类，此处是
            Service1
            enhancer.setCallback 传递的是 MethodInterceptor 接口类型的参数， MethodInterceptor
            接口有个 intercept 方法，这个方法会拦截代理对象所有的方法调用。
            还有一个重点是 Object result = methodProxy.invokeSuper(o, objects); 可以调用被代理
            类，也就是Service1类中的具体的方法，从方法名称的意思可以看出是调用父类，实际对某个类创
            建代理，cglib底层通过修改字节码的方式为Service1类创建了一个子类。
            运行输出：
            从输出中可以看出Service1中的2个方法都被 MethodInterceptor 中的 invoke 拦截处理了。
            案例2：拦截所有方法（MethodInterceptor）
            在创建一个类，如下：
             * @param method 被代理的类的方法，即Service1中的方法
             * @param objects 调用方法传递的参数
             * @param methodProxy 方法代理对象
             * @return
             * @throws Throwable
             */
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" + method);
//可以调用MethodProxy的invokeSuper调用被代理类的方法
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
//4.获取代理对象,调用enhancer.create方法获取代理对象，这个方法返回的是Object类型的，所以需要强转一下
        Service1 proxy = (Service1) enhancer.create();
//5.调用代理对象的方法
        proxy.m1();
        proxy.m2();
    }


    @Test
    public void test2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法:" + method);
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        Service2 proxy = (Service2) enhancer.create();
        proxy.m1(); //@1
    }


    @Test
    public void test3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "路人甲";
            }
        });
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());//@1
        System.out.println(proxy.m2()); //@2
        System.out.println(proxy.toString());//@3
    }


    @Test
    public void test6() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        enhancer.setCallback(NoOp.INSTANCE);
        Service3 proxy = (Service3) enhancer.create();
        System.out.println(proxy.m1());
        System.out.println(proxy.m2());
    }


    @Test
    public void test4() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service4.class);
//创建2个Callback
        Callback[] callbacks = {
//这个用来拦截所有insert开头的方法
                new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[]
                            objects, MethodProxy methodProxy) throws Throwable {
                        long starTime = System.nanoTime();
                        Object result = methodProxy.invokeSuper(o, objects);
                        long endTime = System.nanoTime();
                        System.out.println(method + "，耗时(纳秒):" + (endTime -
                                starTime));
                        return result;
                    }
                },
//下面这个用来拦截所有get开头的方法，返回固定值的
                new FixedValue() {
                    @Override
                    public Object loadObject() throws Exception {
                        return "路人甲Java";
                    }
                }
        };
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                return 0;
            }
        });
//调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbacks);
/**
 * 设置过滤器CallbackFilter
 * CallbackFilter用来判断调用方法的时候使用callbacks数组中的哪个Callback来处理当前方
 法
 * 返回的是callbacks数组的下标
 */
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
//获取当前调用的方法的名称
                String methodName = method.getName();
/**
 * 方法名称以insert开头，
 * 返回callbacks中的第1个Callback对象来处理当前方法，
 * 否则使用第二个Callback处理被调用的方法
 */
                return methodName.startsWith("insert") ? 0 : 1;
            }
        });
        Service4 proxy = (Service4) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }


    @Test
    public void test5() {
        Enhancer enhancer = new Enhancer();
//创建2个Callback
        Callback costTimeCallback = (MethodInterceptor) (Object o, Method method,
                                                         Object[] objects, MethodProxy methodProxy) -> {
            long starTime = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long endTime = System.nanoTime();
            System.out.println(method + "，耗时(纳秒):" + (endTime - starTime));
            return result;
        };
//下面这个用来拦截所有get开头的方法，返回固定值的
        Callback fixdValueCallback = (FixedValue) () -> "路人甲Java";
        CallbackHelper callbackHelper = new CallbackHelper(Service4.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback :
                        fixdValueCallback;
            }
        };
        enhancer.setSuperclass(Service4.class);
//调用enhancer的setCallbacks传递Callback数组
        enhancer.setCallbacks(callbackHelper.getCallbacks());
/**
 * 设置CallbackFilter,用来判断某个方法具体走哪个Callback
 运行输出：
 输出效果和案例4一模一样的，上面重点在于 CallbackHelper ，里面做了一些封装，有兴趣的可
 以去看一下源码，比较简单。
 案例6：实现通用的统计任意类方法耗时代理类
 直接上代码，比较简单，如下：
 */
        enhancer.setCallbackFilter(callbackHelper);
        Service4 proxy = (Service4) enhancer.create();
        System.out.println("---------------");
        proxy.insert1();
        System.out.println("---------------");
        proxy.insert2();
        System.out.println("---------------");
        System.out.println(proxy.get1());
        System.out.println("---------------");
        System.out.println(proxy.get2());
    }


    @Test
    public void test7() {
//创建Service1代理
        Service1 service1 = CostTimeProxy.createProxy(new Service1());
        service1.m1();
//创建Service3代理
        Service3 service3 = CostTimeProxy.createProxy(new Service3());
        System.out.println(service3.m1());
    }
}