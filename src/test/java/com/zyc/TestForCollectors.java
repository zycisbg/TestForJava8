package com.zyc;

import com.zyc.java8.po.Traders;
import com.zyc.java8.po.Transactions;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by zyc on 17/5/15.
 */
public class TestForCollectors {



    //初始化交易员和交易信息
    Traders Raoul = new Traders("Raoul", "Cambridge");
    Traders Mario = new Traders("Mario","MiLan");
    Traders alan = new Traders("alan","Cambridge");
    Traders brian = new Traders("brian","Cambridge");


    List<Transactions> list = Arrays.asList(new Transactions(brian, 2011, 300),
              new Transactions(Raoul, 2012, 1000),
              new Transactions(Raoul, 2011, 400),
              new Transactions(Mario, 2012, 710),
              new Transactions(Mario, 2012, 700),
              new Transactions(alan, 2012, 950));


    /**
     * java8 in action 128页 有详细的使用说明
     */
    /**
     * 分区，  以 true/false 来分组。
     *
     */


    /**
     * 获取当前服务器的 处理器，
     * 并行流的默认线程数量即处理器数量
     * 可以使用System.setProperty("java.util.concurrent.ForkJoinPool.Common.parallelism","12");
     * 来改变 线程的数量
     */
    @Test
    public void test8(){
        int i = Runtime.getRuntime().availableProcessors();
//        System.setProperty("java.util.concurrent.ForkJoinPool.Common.parallelism","12");
        System.out.println(i);
    }
    @Test
    public void test7(){
        Map<Boolean, List<Transactions>> collect = list.stream()
                  .collect(partitioningBy(t -> t.getYear() == 2011));

        System.out.println(collect.get(true).size());
        System.out.println(collect.get(false).size());
    }

    /**
     * 分组，返回一个map类型，
     * key:以什么分组。，
     * value:每个租得元素
     */
    @Test
    public void test6(){
        Map<Integer, List<Transactions>> collect = list.stream()
                  .collect(groupingBy(Transactions::getYear));

        System.out.println(collect.size());
        System.out.println(collect.get(2011).size());
        System.out.println(collect.get(2012).size());

    }

    /**
     * 连接字符串
     */
    @Test
    public void test5(){
        String collect = list.stream().map(t -> t.getTraders().getName()).distinct().collect(joining(","));
        System.out.println(collect);
    }

    /**
     * 大杂烩
     */
    @Test
    public void test4(){
        IntSummaryStatistics collect = list.stream()
                .collect(Collectors.summarizingInt(Transactions::getValue));

    }

    /**
     * 查询平均值
     */
    @Test
    public void test3(){
        Double collect = list.stream().collect(Collectors.averagingInt(Transactions::getValue));
        System.out.println(collect);
    }

    /**
     * 查询sum
     */

    @Test
    public void test2(){
        Integer collect = list.stream()
                  .collect(Collectors.summingInt(Transactions::getValue));
        System.out.println(collect);
    }

    /**
     * 查找最小值
     */
    @Test
    public void test1(){
        Optional<Transactions> collect = list.stream()
                  .distinct()
                  .collect(Collectors.minBy(Comparator.comparing(Transactions::getYear)));

        System.out.println(collect.get());
    }

    /**
     * 查找最大值。
     */
    @Test
    public void test(){
        Transactions transactions = list.stream()
                  .collect(Collectors.maxBy((Comparator.comparing(Transactions::getValue)))).get();

        Optional<Transactions> collect = list.stream().collect(maxBy(Comparator.comparing(Transactions::getValue)));
        Map map = new HashMap();
        collect.ifPresent(a->map.put("key",a));

        Object key = map.get("key");
        System.out.println(key);
        System.out.println(transactions.getValue());
    }
}
