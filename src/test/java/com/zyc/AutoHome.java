package com.zyc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * http://www.autohome.com.cn/2488/#levelsource=000000000_0&pvareaid=101594
 * http://www.autohome.com.cn/3480/#levelsource=000000000_0&pvareaid=101594
 http://www.autohome.com.cn/2885/#levelsource=000000000_0&pvareaid=101594
 http://www.autohome.com.cn/2599/#levelsource=000000000_0&pvareaid=101594
 http://www.autohome.com.cn/2571/#levelsource=000000000_0&pvareaid=101594
 */

/**
 * Created by zyc on 17/5/19.
 */
public class AutoHome {

    /**
     * 查询汽车之家 所有在售车型的价格（如果有价格）
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        //http://www.autohome.com.cn/car/
        Document document = Jsoup.connect("http://www.autohome.com.cn/car/").maxBodySize(0).get();
        Elements select = document.body().select("h4").select("a");
        List<String> carUrlList = new ArrayList();
        for(Element element:select){
            String url = element.attr("href");
            //http://www.autohome.com.cn/2568/#levelsource=000000000_0&pvareaid=101594
            if(url.contains("www.autohome.com.cn") && url.contains("levelsource=")){
                carUrlList.add(url);
            }
        }
        FileOutputStream os = new FileOutputStream(new File("/myapp/txt/output.txt"));
        PrintStream p = new PrintStream(os);
        p.println("共找到在售车型："+carUrlList.size());
        int count = 0;
        for(String url :carUrlList){
            Document carDocument = Jsoup.connect(url).timeout(15000).get();
            Elements red = carDocument.select("a[href].red");
            for (Element element:red){
                if(element.attr("href").contains("price.htm")){
                    Elements carName = carDocument.select("h1");
                    p.println(++count +":车型："+carName.first().text()+"。报价："+element.text());
                }
            }
        }
        p.println("实际找到车型个数："+count);

        p.close();


    }

    @Test
    public void test2() throws Exception{
        Document carDocument = Jsoup.connect("http://www.autohome.com.cn/2568/#levelsource=000000000_0&pvareaid=101594").timeout(15000).get();
        Elements red = carDocument.select("a[href].red");

        for (Element element:red){
            if(element.attr("href").contains("price.htm")){
                Elements carName = carDocument.select("h1");
                System.out.println(":车型："+carName.first().text()+"。报价："+element.text());
            }
        }
    }
}
