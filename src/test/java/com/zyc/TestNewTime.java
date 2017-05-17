package com.zyc;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Created by zyc on 17/5/16.
 */
public class TestNewTime {

    /**
     * java8 in action 251页有详细API
     */

    /**
     * 操作时间， 】
     * 相对的方式来操作时间
     */
    @Test
    public void test6(){
        LocalDate localDate = LocalDate.of(2008,3,23);
        localDate.plusMonths(1);
        localDate.minusYears(3);
        localDate.plus(6, ChronoUnit.MONTHS);
    }

    /**
     *  操作时间
     *  直观的方式操作时间
     */
    @Test
    public void test5(){
        LocalDate localDate = LocalDate.of(2008,3,23);

        localDate.withYear(2018);
        localDate.withDayOfMonth(28);
        localDate.with(ChronoField.MONTH_OF_YEAR,7);


    }

    @Test
    public void test4(){
        LocalDate nowDate = LocalDate.now();
        LocalTime nowTime = LocalTime.now();

        LocalDateTime localDateTime = LocalDateTime.of(nowDate, nowTime);

        nowDate.atTime(nowTime);
        nowTime.atDate(nowDate);


        localDateTime.toLocalDate();
        localDateTime.toLocalTime();
    }

    @Test
    public void test3(){
        LocalDate.parse("2014-01-01");
        LocalTime.parse("17:33");
    }
    @Test
    public void test1(){

        LocalDate date = LocalDate.of(2017, 5, 16);

        DayOfWeek dayOfWeek = date.getDayOfWeek();

        System.out.println(dayOfWeek);

        System.out.println(date.lengthOfMonth());

        System.out.println(date.lengthOfYear());

        System.out.println(date.now());

    }

    @Test
    public void test2(){
        LocalTime now = LocalTime.now();

        LocalTime of = LocalTime.of(12, 33);

        System.out.println(of.getSecond());
        System.out.println(of);

        System.out.println(now);

    }
}
