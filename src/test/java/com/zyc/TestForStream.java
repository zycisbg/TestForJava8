package com.zyc;

import com.zyc.java8.po.Traders;
import com.zyc.java8.po.Transactions;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * Created by zyc on 17/5/15.
 * java8 in action 98页关于stream 的测试
 * 123
 */
public class TestForStream {



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
     * 所有交易中，最高的交易额是多少
     */
    @Test
    public void test6(){
        Optional<Integer> reduce = list.stream()
                  .map(t -> t.getValue())
                  .reduce(Integer::max);
        if(reduce.isPresent()){
            System.out.println(reduce.get());
        }
    }

    /**
     * 打印所有在剑桥的交易员的所有交易额
     */
    @Test
    public void test5(){
        list.stream()
                  .filter(t -> "Cambridge".equals(t.getTraders().getCity()))
                  .map(t -> t.getValue())
                  .forEach(System.out::println);
    }

    /**
     * 有没有员工在米兰工作
     */
    public void test4(){
        list.stream()
                  .anyMatch(t -> "MiLan".equals(t.getTraders().getCity()));
    }


    /**
     * 返回所有交易员的姓名字符串，按字母排序
     */
    @Test
    public void test3(){
        String reduce = list.stream()
                  .map(t -> t.getTraders().getName())
                  .distinct()
                  .sorted()
                  .reduce("", (a, b) -> a + b);
        System.out.println(reduce);
    }

    /**
     * 查询所有来自剑桥的交易员
     * 并且按照名称排序
     */
    @Test
    public void test2(){
        list.stream()
                  .map(Transactions::getTraders)
                  .filter(t -> "Cambridge".equals(t.getCity()))
                  .distinct()
                  .sorted(Comparator.comparing(Traders::getName));
    }

    /**
     * 交易员都在哪些不同得城市工作过
     */
    @Test
    public void test1(){
        list.stream()
                  .map(t -> t.getTraders().getCity())
                  .distinct()
                  .forEach(System.out::println);
    }

    /**
     * 2011年所有得交易，并且排序
     */
    @Test
    public void test(){
        list.stream()
                  .filter(t -> t.getYear()==2011)
                  .sorted(Comparator.comparing(Transactions::getValue))
                  .forEach(System.out::println);
    }
}
