package com.wy.demo.guava;

import com.google.common.collect.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class 双键Map_Table {
    public static void main(String[] args) {

//以前的模式
        Map<String,Map<String,Integer>> map=new HashMap<>();
//存放元素
        Map<String,Integer> workMap=new HashMap<>();
        workMap.put("Jan",20);
        workMap.put("Feb",28);
        map.put("Hydra",workMap);
//取出元素
        Integer dayCount = map.get("Hydra").get("Jan");
//1）table模式

        HashBasedTable<String, String, Integer> OO = HashBasedTable.create();
     OO.put("Hydra","Jan",20);
     OO.put("Hydra","Feb",28);
        OO.put("Brave","Jan",22);
        OO.put("Brave","Feb",23);
        Integer dayCount1 = map.get("Hydra").get("Jan");
//获取元素
        System.out.println(dayCount);
        System.out.println(dayCount1);
//分别输出3列的集合
        System.out.println(OO.rowKeySet());
        System.out.println(OO.columnKeySet());
        System.out.println(OO.values());
/*
 2）计算每个key对应的value

          Hydra: 48
          Brave: 45*/
        for (String key : OO.rowKeySet()) {
            Set<Map.Entry<String, Integer>> rows = OO.row(key).entrySet();
            int total = 0;
            for (Map.Entry<String, Integer> row : rows) {
                total += row.getValue();
            }
            System.out.println(key + ": " + total);
        }

        //3 行列互换

        Table<String, String, Integer> table2 = Tables.transpose(OO);
        Set<Table.Cell<String, String, Integer>> cells = table2.cellSet();
        cells.forEach(cell->
                System.out.println(cell.getRowKey()+","+cell.getColumnKey()+":"+cell.getValue())
        );
      //  BiMap - 双向Map ,可以根据key获取value  也可以根据value获取key
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("Hydra","Programmer");
        biMap.put("Tony","IronMan");
        biMap.put("Thanos","Titan");
//使用key获取value
        System.out.println(biMap.get("Tony"));

        BiMap<String, String> inverse = biMap.inverse();
//使用value获取key
        System.out.println(inverse.get("Titan"));

        //4multiMap  一个key对应多个value
        Multimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day",1);
        multimap.put("day",2);
        multimap.put("day",8);
        multimap.put("month",3);
        System.out.println(multimap);
        //rangeMap  不在用范围判断了
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0,60),"fail");
        rangeMap.put(Range.closed(60,90),"satisfactory");
        rangeMap.put(Range.openClosed(90,100),"excellent");

        System.out.println(rangeMap.get(59));
        System.out.println(rangeMap.get(60));
        System.out.println(rangeMap.get(90));
        System.out.println(rangeMap.get(91));
    }
}
