package com.Data;

import com.millet.dao.impl.ClassifyDaoImpl;
import com.millet.pojo.Classify;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.List;
//添加类别到数据库
public class ClassifyPP {
    public static void main(String[] args) throws IOException {
        ClassifyDaoImpl cif  = new ClassifyDaoImpl();
        //获取请求  https://search.jd.com/Search?keyword=java
        //前提要联网
        String url = "https://www.mi.com/?client_id=180100041080&masid=2110.0001&gsadid=gad_472_jnrx4c1y&utm_Account=Default&utm_Adgroup=%e5%b0%8f%e7%b1%b3&utm_Campaign=2020%e5%b9%b4%e5%85%a8%e5%b9%b4%e7%99%be%e5%ba%a6%e6%8a%95%e6%94%be%e9%a1%b9%e7%9b%ae&utm_Medium=Display&utm_Channel=%e7%99%be%e5%ba%a6PC&utm_Source=%e7%99%be%e5%ba%a6%e5%93%81%e4%b8%93&utm_Term=%e7%99%be%e5%ba%a6PC";
        //解析网页
        Document document = Jsoup.parse(new URL(url), 30000);


        Element element = document.getElementById("J_navCategory");

        Elements element1 = element.siblingElements();
        Classify classify = null;
        for (Element el : element1) {
            String text = el.getElementsByClass("text").text();
            System.out.println("============================");
            classify=new Classify();
            classify.setName(text);
            System.out.println(cif.insert(classify));
            System.out.println(text);
        }





    }

@Test
    public void text(){
    ClassifyDaoImpl cif  = new ClassifyDaoImpl();
//    Classify classify =  new Classify();
//        classify.setName("111111111111");
//    System.out.println(cif.insert(classify));


    List<Classify> list = cif.list(null);
    for (Classify classify : list) {
        System.out.println(classify);
    }
}

}
