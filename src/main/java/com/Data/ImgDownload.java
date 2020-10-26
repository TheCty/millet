package com.Data;

import com.millet.dao.impl.CommodityDaoImpl;
import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class ImgDownload {
    public static void main(String[] args) throws IOException {

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

        for (Element el : elements) {
            String img = el.getElementsByTag("img").attr("src");
            System.out.println("=========================================");
            if (img != null && !img.equals("")) {
                downloadPicture("https:"+img);
                System.out.println(img);


            }
        }

    }

    //截取图片名称
    private static String UrlSubstring(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }


    //链接url下载图片
    private static void downloadPicture(String urlList)  {
        URL url = null;
        int imageNumber = 0;
        DataInputStream dataInputStream=null;
        FileOutputStream fileOutputStream=null;
        try {
            url = new URL(urlList);
            dataInputStream = new DataInputStream(url.openStream());
            //注意下载地址
            String imageName =  "D:\\Practice\\project\\millet\\web\\img\\"+UrlSubstring(urlList);

            fileOutputStream = new FileOutputStream(new File(imageName));
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            byte[] context=output.toByteArray();
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
