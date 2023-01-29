package com.wy.demo.Test;

import java.util.ArrayList;
import java.util.List;

public class batchInsert {
    public static void main(String[] args) {
        int num =3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        int round = (arrayList.size()-1) /num;

        for(int i = 0; i<= round ; i++){
            int fromIndex = i * num;
             int toIndex = (i + 1) * num;
             //若是最后一个批次则不能越界
            if(i ==round ){
               toIndex = arrayList.size();
            }
            List list = arrayList.subList(fromIndex, toIndex);
            System.out.println(list);
        }

    }
}
