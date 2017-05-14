package com.zyc.java8.tactics;

import com.zyc.java8.po.Person;

/**
 * Created by zyc on 17/5/12.
 */
public interface PersonFilter {
    boolean filterPersonByAge(Person person);
}
