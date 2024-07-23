package com.wy.demo.xielaoshi.xianchengchigongjulei;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
@Slf4j
public class ThreadPoolService {
    public ThreadPoolService() {
    }

    public static void execute(ThreadPoolExecuteTypeEnum type, List<Runnable> tasks) throws InterruptedException {
        executeTask(type, (List) tasks,(String) null );
    }

    public static void execute(ThreadPoolExecuteTypeEnum type, String name, List<Runnable> tasks) throws InterruptedException {
        executeTask(type, tasks, name);
    }

    public static void executeTask(ThreadPoolExecuteTypeEnum type,  List<Runnable> tasks,String name) throws InterruptedException {
        if ((tasks != null && tasks.size() > 0)) {
            if ((type == null || type == ThreadPoolExecuteTypeEnum.SYN)) {
                CountDownLatch ctl = new CountDownLatch(tasks.size());
                Iterator var4 = tasks.iterator();

                while (var4.hasNext()) {
                    Runnable task = (Runnable) var4.next();
                    ThreadPoolUtils.getInstance(name).execute(getRunnable(ctl, task));
                }
                ctl.await();
            }
            if ((type == null || type == ThreadPoolExecuteTypeEnum.ASY)) {
                Iterator var6 = tasks.iterator();

                while (var6.hasNext()) {
                    Runnable task = (Runnable) var6.next();
                    log.info(Thread.currentThread().getName()+ " 异步执行完毕");
                    ThreadPoolUtils.getInstance(name).execute(task);
                }

            }
        }
    }

    public static void execute(ThreadPoolExecuteTypeEnum type, Runnable... tasks) throws InterruptedException {
        executeTask(type, (String) null, (Runnable[]) tasks);
    }

    public static void execute(ThreadPoolExecuteTypeEnum type, String name, Runnable... tasks) throws InterruptedException {
        executeTask(type, name, tasks);
    }

    public static void executeTask(ThreadPoolExecuteTypeEnum type, String name, Runnable... tasks) throws InterruptedException {
        if ((tasks != null && tasks.length > 0)) {
            int var5;
            if ((type == null || type == ThreadPoolExecuteTypeEnum.SYN)) {
                CountDownLatch ctl = new CountDownLatch(tasks.length);
                Runnable[] var4 = tasks;
                var5 = tasks.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    Runnable task = var4[var6];
                    ThreadPoolUtils.getInstance(name).execute(getRunnable(ctl, task));
                }
                ctl.await();
            }
            if ((type == null || type == ThreadPoolExecuteTypeEnum.ASY)) {
                Runnable[] var8 = tasks;
                int var9 = tasks.length;

                for ( var5 = 0; var5 < var9; ++var5) {
                    Runnable task = var8[var5];
                    ThreadPoolUtils.getInstance(name).execute(task);
                }
            }

        }
    }
    private static Runnable getRunnable( CountDownLatch ctl,  Runnable task) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    log.info(Thread.currentThread().getName()+ " 执行完毕");
                    task.run();
                } finally {

                    if (null != ctl) {
                        ctl.countDown();
                    }
                }
            }

        };
    }}