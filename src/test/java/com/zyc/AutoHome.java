package com.zyc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyc on 17/5/19.
 */
public class AutoHome {

    @Test
    public void test() throws Exception{
        //http://www.autohome.com.cn/car/
        Document document = Jsoup.connect("http://www.autohome.com.cn/car/").get();
        Elements select = document.select("a[href]");
        List<String> carUrlList = new ArrayList();
        for(Element element:select){
            String url = element.attr("href");
            if(url.contains("www.autohome.com.cn") && url.contains("levelsource=")){
                carUrlList.add(element.attr("href"));
                System.out.println(element.attr("href"));
            }
        }
        List<String> nameAndPriceList = new ArrayList();
        for(String url :carUrlList){
            Document carDocument = Jsoup.connect(url).timeout(15000).get();
            Elements red = carDocument.select("a[href].red");

            for (Element element:red){
                if(element.attr("href").contains("price.htm")){
                    Elements carName = carDocument.select("h1");
                    System.out.println("车型："+carName.first().text()+"。报价："+element.text());
                    nameAndPriceList.add("车型："+carName.first().text()+"。报价："+element.text());
                }
            }
        }

    }

    @Test
    public void test2() throws Exception{
        Document carDocument = Jsoup.connect("http://www.autohome.com.cn/4208/#levelsource=000000000_0&pvareaid=101594").timeout(10000).get();
        Elements red = carDocument.select("a[href].red");

        for (Element element:red){
            if(element.attr("href").contains("price.htm")){
                Elements h1 = carDocument.select("h1");
                System.out.println(h1.first().text());
                System.out.println(element.text());
            }
        }
    }
}
