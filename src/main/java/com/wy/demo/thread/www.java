package com.wy.demo.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class www  implements Runnable{
    int name;
  public static  int ii=0;

    public www(int i) {
        this.name=i;
    }

    @Override
    public void run() {
        System.out.println("线程:    "+name+"    " +"    "+Thread.currentThread().getName());
        System.out.println(ii++);
    }
}
