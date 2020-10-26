package com.Data;

import com.millet.dao.impl.CommodityDaoImpl;
import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

public class CommodityPP {
    public static void main(String[] args) throws Exception {
        //获取请求  https://search.jd.com/Search?keyword=java
        //前提要联网
        /**
         *  https://list.jd.com/list.html?cat=9987%2C653%2C655&ev=exbrand_%E5%B0%8F%E7%B1%B3%EF%BC%88MI%EF%BC%89%5E&cid3=655  小米
         *  https://list.jd.com/list.html?cat=9987%2C653%2C655&ev=exbrand_%E5%B0%8F%E7%B1%B3%EF%BC%88MI%EF%BC%89%5E&page=3&s=61&click=0         类别2
         *  https://list.jd.com/list.html?cat=670%2C671%2C672&ev=exbrand_%E5%B0%8F%E7%B1%B3%EF%BC%88MI%EF%BC%89%5E&cid3=672    电脑
         *  https://search.jd.com/search?keyword=%E8%B7%AF%E7%94%B1%E5%99%A8&suggest=1.def.0.0&wq=%E8%B7%AF%E7%94%B1%E5%99%A8&ev=exbrand_%E5%B0%8F%E7%B1%B3%EF%BC%88MI%EF%BC%89%5E      路由器
         *
         *
         *
         */
        String url="https://search.jd.com/search?keyword=%E8%B7%AF%E7%94%B1%E5%99%A8&suggest=1.def.0.0&wq=%E8%B7%AF%E7%94%B1%E5%99%A8&ev=exbrand_%E5%B0%8F%E7%B1%B3%EF%BC%88MI%EF%BC%89%5E";
        //解析网页
        Document document = Jsoup.parse(new URL(url),30000);

        Element element = document.getElementById("J_goodsList");

        Elements elements = element.getElementsByTag("li");

//        System.out.println(elements);




        for (Element el : elements) {

            String img = el.getElementsByTag("img").attr("src");
            String price=el.getElementsByTag("strong").text();
            String title=el.getElementsByTag("em").text();
            System.out.println("=========================================");
            if (img != null && !img.equals("")) {
                price=price.substring(price.indexOf("￥")+1).trim();
                title=title.substring(title.indexOf("￥")+1).trim();
                img=img.substring(img.lastIndexOf("/")+1);

                if (substringtitles(title).equals("") || substringtitles(title) ==null) {
                    continue;
                }


                System.out.println(substringtitles(title));
                System.out.println(img);
                System.out.println(price);
                System.out.println(title.substring(substringtitles(title).length()+1));


                CommodityDaoImpl cmd=new CommodityDaoImpl();
                Commodity comm=new Commodity();
                //注意类别
                comm.setClassifyId(new Classify(9,null,0));
                comm.setImg(img);
                //注意方法
                comm.setName(substringtitles(title));
                comm.setPrice(Double.parseDouble(price));
                //注意方法
                comm.setContext(title.substring(substringtitles(title).length()+1));
                System.out.println(cmd.insert(comm));

            }else {
                continue;
            }
        }
    }

    //去除【京东商城】
    private static String substringtitles(String title) {
        String trim="";
        if (title.indexOf('市')!=-1) {
            trim = title.substring(title.indexOf('市') + 1).trim();
        }
        if (!trim.equals("") && trim!=null) {
            trim=trim.substring(0,trim.indexOf(' '));
        }

        return trim;
    }

    //获得商品名称
    private static String substringtitle(String title) {
        String substring = title.substring(0, title.indexOf(' '));

        String substring3 =title.substring(title.indexOf(" ")+1);
        String substring2=substring3.substring(0,substring3.indexOf(' '));
        if (substring.indexOf(' ')!=-1) {
             substring2 = substring.substring(0, substring.indexOf(' '));
        }
        String name=substring+" "+substring2;
        int start=-1;
        if ((start=name.indexOf("【"))>=0) {
            String substring1 = name.substring(name.indexOf('】')+1);
            return substring1;
        }
        return name;

    }


}
