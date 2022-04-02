package com.wy.demo.线程池的实际使用;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TestPool {
    public static void main(String[] args) {
        System.out.println(test());

    }

   static List<Stu>  test(){
       ExecutorService threadPool = Executors.newFixedThreadPool(100);
       List<Stu> stusOld=new ArrayList<>();

       try {
           List<Stu> stus = Arrays.asList(new Stu("one", 1), new Stu("two", 2));
           List<CompletableFuture<Stu>>  futures =stus.stream().map(o->
                   CompletableFuture.supplyAsync(()->changeAge(o),threadPool)).collect(Collectors.toList());
//进行异步组装
           futures.forEach(f->{
               try {
                   if (f.get()!=null) {
                       stusOld.add(f.get());
                   }
               } catch (InterruptedException |ExecutionException e) {
                   e.printStackTrace();
               }
           });
           return stusOld;
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           //关闭线程池
           threadPool.shutdown();
       }

       return stusOld;
   }

    private static Stu changeAge(Stu stu) {
        stu.setAge(22);
        return stu;
    }
}
