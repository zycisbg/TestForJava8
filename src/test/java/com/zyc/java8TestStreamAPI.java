package com.zyc;

import com.zyc.java8.po.Currency;
import com.zyc.java8.po.Person;
import com.zyc.java8.po.Transaction;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import static java.util.stream.Collectors.toList;

import static java.util.stream.Collectors.groupingBy;
/**
 * Created by zyc on 17/5/12.
 */
public class java8TestStreamAPI {


    List<Transaction> transactions = Arrays.asList(new Transaction(1,new Currency(1,"人民币"),22.22),
              new Transaction(2,new Currency(2,"美元"),22.22),
              new Transaction(3,new Currency(2,"美元"),22.22),
              new Transaction(4,new Currency(2,"美元"),22.22),
              new Transaction(5,new Currency(1,"人民币"),15.5));


    Map<Currency,List<Transaction>> map = new HashMap<>();


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
     * stream API.并行过滤
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
