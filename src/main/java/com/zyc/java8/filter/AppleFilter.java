package com.zyc.java8.filter;

/**
 * Created by zyc on 17/5/12.
 */
@FunctionalInterface
public interface AppleFilter<T> {
    boolean test(T t);

}
