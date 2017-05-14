package com.zyc.java8.filter;

/**
 * Created by zyc on 17/5/13.
 */
@FunctionalInterface
public interface ObjectFilter<T> {
    boolean filter(T t);
}
