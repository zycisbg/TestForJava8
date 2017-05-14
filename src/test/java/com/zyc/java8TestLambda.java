package com.zyc;

import com.zyc.java8.filter.AppleFilter;
import com.zyc.java8.po.Apple;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by zyc on 17/5/12.
 */
public class java8TestLambda {


    /**
     * java8 in action 中。
     * 第36页详细的介绍了 lambda 表达式的使用语法。
     *
     * lambda表达式 ，主要配合函数式接口使用
     * 函数式接口即接口只有一个抽象方法，（可以有多个默认方法，但是抽象方法只能有一个）
     *
     * '@FunctionalInterface'  这个注解用来表示一个接口是不是函数式接口。
     */
    List<Apple> list = Arrays.asList(new Apple("red",11.2,9.0)
                ,new Apple("blue",2.2,3.3)
                ,new Apple("green",2.9,3.0)
                ,new Apple("yellow",5.5,9.2));

    /**
     * 构造函数
     */
    @Test
    public void test10(){
        Supplier<Apple> supplier = Apple::new;
        Apple apple = supplier.get();


        Supplier<Apple> supploer = () -> new Apple();
        Apple apple1 = supplier.get();
    }

    @Test
    public void test9(){
        list.sort((Apple a1,Apple a2) -> a1.getPrice().compareTo(a2.getPrice()));
        list.forEach(System.out::println);
        //倒序
        list.sort(Comparator.comparing(Apple::getPrice).reversed());
        list.forEach(System.out::println);

        //当价格一样得时候，按重量排序
        list.sort(Comparator.comparing(Apple::getPrice).thenComparing(Apple::getWeight));

    }


    /**
     * 以下三个FunctionalInterface （Function,Consumer,Predicate都有对应的基本类型接口 如intConsumer等。）
     * java8 in action 中。 46页中有详细介绍
     */
    /**
     * java8 提供的Function函数式接口使用，
     * apply方法，传入一个类型，返回令一个类型，
     * 一般为了转换。
     * lambda 表达式的主体  相当于执行了apply方法的内容..
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public <T,R> List<R> function1(List<T> list,Function<T,R> function){
        List<R> result = new ArrayList<>();
        for (T t:list){
            result.add(function.apply(t));
        }
        return result;
    }

    @Test
    public void test81(){
        List<String> strings = this.function1(list, (Apple apple) -> apple.getColor());

        strings.forEach(System.out::println);
    }

    public String function(Apple apple, Function<Apple,String> function){
        return function.apply(apple);
    }



    @Test
    public void test8(){
        String blue = function(new Apple("blue", 2.2, 3.3), (Apple apple) -> apple.getColor());

        System.out.println(blue);
    }

    /**
     * java8 提供的Consumer函数式接口使用，
     * accept方法，返回空，
     * lambda 表达式的主体  相当于执行了accept方法的内容
     * @param list
     * @param consumer
     */
    public void consumer(List<Apple> list, Consumer<Apple> consumer){
        list.forEach(consumer);
    }


    @Test
    public void test7(){
        this.consumer(list,(Apple apple)-> System.out.println(apple));
    }

    /**
     * java8 提供的Predicate函数式接口使用 ，
     * test方法，  返回boolean
     * lambda 表达式的主体  相当于执行了test方法的内容
     * @param list
     * @param predicate
     * @return
     */
    public List<Apple> getAppleByParamAndPredicate(List<Apple> list, Predicate<Apple> predicate){
        List<Apple> returnList = new ArrayList();
        for(Apple apple:list){

            if(predicate.test(apple)){
                returnList.add(apple);
            }
        }
        return returnList;
    }
    @Test
    public void test6(){
        this.getAppleByParamAndPredicate(list,(Apple apple) -> apple.getPrice()>5&&apple.getWeight()>3);

        
    }

    /**
     * lambda 创建线程
     */

    @Test
    public void test5(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是一个线程");
            }
        });

        Thread thread1 = new Thread(()-> System.out.println("这也是一个线程"));
    }
    /**
     *  使用 方法传递
     */
    @Test
    public void test1(){

        List<Apple> appleByParam = getAppleByParam(list, Apple::isRed);

        appleByParam.forEach(System.out::println);
    }

    @Test
    public void test2(){
        getAppleByParam(list,(apple)->"red".equals(apple.getColor())).forEach(
                  System.out::println
        );
    }

    /**
     * 排序 (非lambda)
     */
    @Test
    public void test3(){
        list.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o2.getPrice().compareTo(o1.getPrice());
            }
        });
        list.forEach(System.out::println);


    }

    /**
     * 排序 利用lambda
     */
    @Test
    public void test4(){
        list.sort((Apple o1,Apple o2)->o1.getPrice().compareTo(o2.getPrice()));
        list.forEach(System.out::println);
    }
    /**
     * test1,test2使用
     * @param list
     * @param filter
     * @return
     */
    public List<Apple> getAppleByParam(List<Apple> list, AppleFilter<Apple> filter ){
        List<Apple> returnList = new ArrayList();
        for(Apple apple:list){

            if(filter.test(apple)){
                returnList.add(apple);
            }
        }
        return returnList;
    }


}
