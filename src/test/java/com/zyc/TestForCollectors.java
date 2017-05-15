package com.zyc;

import com.zyc.java8.po.Traders;
import com.zyc.java8.po.Transactions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

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
     * 连接字符串
     */
    @Test
    public void test5(){
        String collect = list.stream()
                  .map(t -> t.getTraders().getName())
                  .distinct()
                  .collect(joining(","));
        System.out.println(collect);
    }

    /**
     * 大杂烩
     */
    @Test
    public void test4(){
        list.stream()
                  .collect(Collectors.summarizingInt(Transactions::getValue));
    }

    /**
     * 查询平均值
     */
    @Test
    public void test3(){
        Double collect = list.stream()
                  .collect(Collectors.averagingInt(Transactions::getValue));
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

        System.out.println(transactions.getValue());
    }
}
