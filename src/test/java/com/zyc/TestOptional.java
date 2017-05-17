package com.zyc;

import com.zyc.java8.po.Person;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by zyc on 17/5/16.
 */
public class TestOptional {

    /**
     * optional 取代 null
     * java8 in action 中，215页 有详细的API
     */

    @Test
    public void test1(){
        Optional<Person> empty = Optional.empty();

        Optional<Person> laosan = Optional.of(new Person(1, "laosan", 2, new BigDecimal(22.33)));

        System.out.println(laosan.get().getAge());

        laosan.map(Person::getName);

        laosan.ifPresent((Person p) -> {
            System.out.println(p);
        });

        empty.ifPresent(System.out::print);

        Person laosi = empty.orElse(new Person(2, "laosi", 3, new BigDecimal(22.33)));

        Person laosan1 = laosan.orElse(new Person(2, "laosi", 3, new BigDecimal(22.33)));

        System.out.println(laosi);
        System.out.println(laosan1);

        empty.orElseGet(() -> new Person());
    }
}
