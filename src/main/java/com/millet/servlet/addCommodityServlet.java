package com.millet.servlet;

import com.millet.pojo.Classify;
import com.millet.pojo.Commodity;
import com.millet.service.commodity.CommodityService;
import com.millet.service.commodity.CommodityServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class addCommodityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //判断上传的文件是普通表单还是带文件的表单
        if(!ServletFileUpload.isMultipartContent(req)){
            return;//终止方法运行，说明这是一个普通的表单，直接返回
        }

        //创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件；
        String uploadPath = this.getServletContext().getRealPath("/img");
        File uploadFile = new File(uploadPath);
        if(!uploadFile.exists()){
            uploadFile.mkdir();//创建文件目录
        }

        //缓存，临时文件
        //临时路径，假如文件超过了预期大小，我们就把他放到一个临时文件中，过几天自动删除，或是提醒用户转成为永久
        String tmpPath = this.getServletContext().getRealPath("/img/tmp");
        File file = new File(tmpPath);
        if(!file.exists()){
            file.mkdir();//创建文件临时目录
        }

        /*
        ServletFileUpload负责处理上传的文件数据，并将表单中每一个输入项封装成一个FileItem对象；
        在使用ServletFileUpload对象解析请求时需要DiskFileItemFactory对象，
        所以，我们需要在进行解析工作前构造好DiskFileItemFactory对象，
        通过ServletFileUpload对象的构造方法或DiskFileItemFactory方法
        设置ServletFileUpload对象的fileItemFactory属性

         */
        /**
         * 处理上传的文件，一般需要通过流来获取，
         * 我们可以使用request.getInputStream(),原生态文件上传，十分麻烦
         *
         * 但是我们都建议使用Apache的文件上传组件来实现，common-fileupload，
         * 它需要使用依赖于commons-io组件；
         */

        //1、创建DiskFileItemFactory对象，处理文件上传路径或是大小限制的；
        DiskFileItemFactory factory = getDiskFileItemFactory(file);

        //2、获取ServletFileUpload
        ServletFileUpload upload = getServletFileUpload(factory);

        //3、处理上传文件
        try {
            String msg = uploadParseRequest(upload,req,uploadPath);


            req.setAttribute("msg",msg);
            req.getRequestDispatcher("info.jsp").forward(req,resp);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static DiskFileItemFactory getDiskFileItemFactory(File file){
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //通过这个工厂设置一个缓冲区，当上传的文件大于这个缓冲区的时候，将他放到临时文件中；
        factory.setSizeThreshold(1024*1024);//缓冲区大小为1M
        factory.setRepository(file);//临时目录的保存目录，需要一个File

        return factory;
    }
    public static  ServletFileUpload getServletFileUpload(DiskFileItemFactory factory){
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度；
        upload.setProgressListener(new ProgressListener() {
            //pBytesRead:已经读取到的文件大小
            //pContentLength: 文件大小
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小: "+pContentLength + "已上传: " + pBytesRead);
            }
        });
        //处理乱码问题
        upload.setHeaderEncoding("utf-8");
        //设置单个文件的最大值
        upload.setFileSizeMax(1024*1024*10);
        //设置总共能够上传文件的大小
        //1024=1Kb * 1024 = 1M * 10 = 10M
        upload.setSizeMax(1024 * 1024 * 10);


        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload,HttpServletRequest req ,String uploadPath) throws Exception{
        String msg="";
        //把前端请求解析，封装成一个FileItem对象,需要从ServletFileUpload对象中获取
        List<FileItem> fileItems =upload.parseRequest(req) ;

        //添加商品对象所需的类
        CommodityService comm=new CommodityServiceImpl();
        //添加商品类
        Commodity commodity=new Commodity();
        //fileItem 每一个表单对象
        for (FileItem fileItem : fileItems) {

            //判断上传的文件是普通表单还是带文件的表单
            if (fileItem.isFormField()) {
                //getFieldName指的是前端表单控件的name；
                String name = fileItem.getFieldName();
                String value = fileItem.getString("utf-8");//处理乱码
                System.out.println(name + ":" + value);

                if(name.equals("name")){
                    commodity.setName(value);
                }
                if (name.equals("classify")) {
                    commodity.setClassifyId(new Classify(Integer.parseInt(value),null,0));
                }
                if (name.equals("count")) {
                    commodity.setCount(Integer.parseInt(value));
                }
                if (name.equals("price")) {
                    commodity.setPrice(Double.parseDouble(value));
                }
                if (name.equals("context")) {
                    commodity.setContext(value);
                }






            } else {//文件
                //===============================处理文件====================================//

                //拿到文件名字
                String uploadFileName = fileItem.getName();
                System.out.println("上传文件的名字 :" + uploadFileName);
                //可能存在文件名不合法的情况
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }
                //获得上传的文件名  /images/girl/paojie.png
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                //获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
                    /*
                    如果文件后缀名 fileExtName 不是我们所需要的
                    就直接return ； 不处理 ，告诉用户文件类型不对。
                     */

                //可以使用UUID（唯一识别的通用码），保证文件名唯一；
                //UUID.randomUUID(),随机生一个唯一识别的通用码；

                //网络传输中的东西，都需要序列化
                //POJO，实体类，如果想要在多个电脑上运行，传输=====>需要把对象都序列化了
                //JNI =  Java   Native   Interface
                //implements Serializable : 标记接口 ，JVM----> 本地方法栈  native  --> C++

                String uuidPath = UUID.randomUUID().toString();
                //===============================存放地址====================================//

                //存到那？ uploadPath
                //文件真实存在的路径 realPath
                String realPath = uploadPath + "/" + uuidPath;
                //给每一个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdir();
                }
                System.out.println(realPath);
                //===============================文件传输====================================//
                //获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();
                //创建一个文件输出流
                //realPath = 真实的文件夹；
                //查了一个文件；加上输出文件的名字+ “/” +uuidFileName
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

               commodity.setImg(uuidPath+"/"+fileName);



//                System.out.println(comm.insert(commodity));


                //创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];

                //判断是否读取完毕
                int len = 0;
                //如果大于0说明还存在数据；
                while ((len = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                //关闭流
                fos.close();
                inputStream.close();

                msg = " 文件上传成功！";
                fileItem.delete();//上传成功，清除临时文件


            }


        }
        System.out.println(commodity);
        System.out.println(comm.insert(commodity));
        return msg;


    }
}
