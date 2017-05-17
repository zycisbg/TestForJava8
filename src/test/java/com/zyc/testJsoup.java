package com.zyc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zyc on 17/5/17.
 */
public class testJsoup {
    //frs-author-name j_user_card

    @Test
    public void test1() throws IOException{
        //得到一个 href的 集合
        List<String> list = new ArrayList<>(3000);
        int page = 5;
        long l = System.currentTimeMillis();
        //获取智联招聘的前5页关于java的招聘信息
        for(int i=0;i<page;i++){

            Document document = Jsoup.connect("http://sou.zhaopin.com/jobs/searchresult.ashx?jl=北京&kw=java&sm=0&sg=f1885848ab234fe699d6de0c6c4bda43&p="+i).get();
            Elements links = document.select("a[href]");
            for(Element element:links ){
                list.add(element.attr("href"));
            }
        }

        //得到一个 全部是 java 招聘URL 的集合
        List<String> javaList = list.stream()
                  .filter(s -> s.contains("jobs.zhaopin.com") && s.contains(".htm"))
                  .collect(Collectors.toList());


        long l1 = System.currentTimeMillis();

        //class="tab-inner-cont"   为每个页面的 职位描述

        List<String> java8List = new ArrayList(300);

        //从javaList中获取包含java8的信息（职位描述中）
        for(String javaURL:javaList){
            Document document = Jsoup.connect(javaURL).get();
            Elements elementsByClass = document.getElementsByClass("tab-inner-cont");
            for (Element element:elementsByClass){
                System.out.println("---------------");
                System.out.println(element.text());
                System.out.println("---------------");
                if (element.text().contains("java8") ||element.text().contains("JDK1.8")){
                    java8List.add(javaURL);
                }
            }
        }
        System.out.println("*********共获取到"+javaList.size()+"条javaURL*********");
        System.out.println("获取智联前"+page+"页的java的RUL,耗时："+(l1-l));
        System.out.println("*********共获取到"+java8List.size()+"条java1.8URL*********");
        System.out.println("获取智联前"+page+"页的包含java8的URL，耗时："+(System.currentTimeMillis()-l1));

    }

    @Test
    public void test2() throws IOException {

        for(int i=0;i<15;i++){
            Document document = Jsoup.connect("http://tieba.baidu.com/f?kw=%E5%B0%9A%E7%A1%85%E8%B0%B7&ie=utf-8&pn="+(i*50)).get();

//            Elements elementsByClass = document.getElementsByClass("j_th_tit");

			Elements elementsByClass2 = document.getElementsByClass("frs-author-name j_user_card ");

			for (Element element : elementsByClass2) {
				String text = element.text();

				System.out.println(text);
			}


        }
    }


}
