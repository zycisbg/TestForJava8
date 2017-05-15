package com.zyc;

import com.zyc.java8.po.Currency;
import com.zyc.java8.po.Person;
import com.zyc.java8.po.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by zyc on 17/5/12.
 */
public class java8TestStreamAPI {


    List<Transaction> transactions = Arrays.asList(new Transaction(1,new Currency(1,"人民币"),22.22),
              new Transaction(2,new Currency(2,"美元"),22.22),
              new Transaction(3,new Currency(2,"美元"),22.22),
              new Transaction(3,new Currency(2,"美元"),18.22),
              new Transaction(5,new Currency(1,"人民币"),15.5));


    Map<Currency,List<Transaction>> map = new HashMap<>();

    /**
     * java in action 97页 有总结
     */
    /**
     * 求一共有多少个元素
     */
    @Test
    public void test13(){

        transactions.stream()
                  .map(d -> 1)
                  .reduce((a,b)->a+b)
                  .ifPresent(System.out::print);
        transactions.stream()
                  .count();
    }

    /**
     * 归纳，
     * 算和活着算积  等等。。
     * 如果没有初始值， 则返回Optional对象
     */
    @Test
    public void test12(){
        //求和
        transactions.stream()
                  .map(Transaction::getMoney)
//                  .reduce(0.0, (a, b) -> a + b);   有初始值的情况下， 返回 结果类型、
                    .reduce(Double::sum);
    }

    /**
     * findFirst:返回第一个元素 optional类型
     * findAny：返回一个元素
     * 区别在于 findAny在并行上做的更好。
     */
    @Test
    public void test11(){
        transactions.stream()
                  .filter(t -> t.getMoney()<20)
                  .findFirst()
                  .ifPresent(System.out::println);
        transactions.stream()
                  .filter(t -> t.getMoney()<20)
                  .findFirst()
                  .ifPresent(System.out::println);
    }

    /**
     * allMatch:是否全部匹配
     * anyMatch:是否最少有一个匹配
     * noneMatch:是否全部都不匹配
     */
    @Test
    public void test10(){
        boolean b = transactions.stream()
                  .allMatch(t -> t.getMoney()<20);
        System.out.println(b);

        boolean b1 = transactions.stream()
                  .anyMatch(t -> t.getMoney() < 20);
        System.out.println(b1);

        boolean b2 = transactions.stream()
                  .noneMatch(t -> t.getMoney() < 20);

        System.out.println(b2);
    }

    /**
     *
     */
    @Test
    public void test9(){
        String[] words = {"HELLO","WORLD"};

        List<String> list = Arrays.asList(words);

        list.stream()
                  .map(word -> word.split(""))
                  .distinct()
                  .collect(toList());


    }

    /**
     * map 方法， 创建一个新值
     * 如下。
     */
    @Test
    public void test8 (){
        List<Currency> collect = transactions.stream()
                  .map(Transaction::getCurrency)
                  .collect(toList());

        collect.forEach(System.out::println);

        transactions.stream()
                  .map(Transaction::getMoney)
                  .collect(toList());
    }

    /**
     * skip 与 limit 互补
     */
    @Test
    public void test7(){
        transactions.stream()
                  .filter(t -> t.getMoney()>20)
                  .skip(2)
                  .forEach(System.out::println);
    }

    /**
     * limit: 截断
     */
    @Test
    public void test6(){
        transactions.stream()
                  .filter(t -> t.getMoney()>20)
                  .limit(2)
                  .forEach(System.out::println);
    }


    /**
     * distinct:去重，
     * 在这里的去重，仅当 引用对象的 equals&&hashCode 都相等时 才去重。
     */
    @Test
    public void test5(){
        transactions.stream().filter(t -> t.getMoney()>20)
                  .distinct()
                  .forEach(System.out::println);
    }
    /**
     * 交易和币种。
     * 根据币种分组。
     * 初始化一个集合的交易
     */
    @Test
    public void test(){

        for(Transaction transaction:transactions){
            if(transaction.getMoney()>10){
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsByCurrency = map.get(currency);
                if(transactionsByCurrency==null || transactionsByCurrency.size()==0){
                    transactionsByCurrency = new ArrayList<>();
                    map.put(currency,transactionsByCurrency);
                }
                transactionsByCurrency.add(transaction);
            }
        }

        for(Transaction transaction : map.get(new Currency(2,"美元"))){
            System.out.println(transaction);
        }
    }

    @Test
    public void test4(){
        List<Person> persons = new LinkedList<>();
        for(int i =0;i<=10000;i++){
            persons.add(new Person(i,"laosan",22,new BigDecimal(22.23).setScale(2,BigDecimal.ROUND_HALF_UP)));
        }
        long time1 = System.currentTimeMillis();
        persons.stream().filter((Person person) -> person.getId()>5)
                  .collect(toList())
                  .forEach(System.out::println);
        long time2 = System.currentTimeMillis();

        long time3 = System.currentTimeMillis();
        persons.parallelStream().filter((Person person) -> person.getId()>5)
                  .collect(toList())
                  .forEach(System.out::println);
        long time4 = System.currentTimeMillis();

        System.out.println(time2-time1);//880

        System.out.println(time4-time3);//220
    }

    /**
     * stream API.并行过滤..
      */
    @Test
    public void test3(){
        transactions.parallelStream()
                  .filter((Transaction t) -> t.getMoney()>10)
                  .collect(toList())
                  .forEach(System.out::println);
    }

    /**
     * stream API。分组
     */
    @Test
    public void test2(){
        Map<Currency, List<Transaction>> collect = transactions.stream()
                  .filter((Transaction t) -> t.getMoney() > 20)
                  .collect(groupingBy(Transaction::getCurrency));

        collect.get(new Currency(2,"美元")).forEach(
                  System.out::println
        );
//        transactions.stream()
//                  .filter((Transaction t)->t.getMoney()>20)
//                  .collect(Collectors.groupingBy(Transaction::getCurrency));
    }
    /**
     * stream API。根据条件过滤....
     */
    @Test
    public void test1(){
        transactions.stream()
                  .filter((Transaction t)-> t.getMoney()>20)
                  .forEach(System.out::println);

    }

}
