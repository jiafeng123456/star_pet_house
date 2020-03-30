package com.jf.api.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/27 0027 09:16
 */
public class OOSClientUtil {

    private static String ENDPOINT = "oss-cn-hangzhou.aliyuncs.com";
    private static String ACCESSKEYID = "LTAI936pZCimM9gV" ;
    private static String ACCESSKEYSECRET = "q100y3saqZRbvN5qRdiDHnp5cH9VmB" ;
    private static String BUCKET = "star-pet-house";

    public static String uploadImg(MultipartFile file) throws IOException {
        String imgFileName = "pet_store/";//保存到阿里云的文件夹名
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = OOSClientUtil.ENDPOINT;
        String accessKeyId = OOSClientUtil.ACCESSKEYID;
        String accessKeySecret = OOSClientUtil.ACCESSKEYSECRET;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = file.getInputStream();
        String originName = file.getOriginalFilename();//上传的文件名
        String fileName =  imgFileName + Calendar.getInstance().getTimeInMillis() + originName;//保存路径为文件夹+文件名
        ossClient.putObject(OOSClientUtil.BUCKET,fileName, inputStream);
        ossClient.shutdown();
        //前面的内容，阿里云文档上都有
        //上传成功后，获取文件路径+文件名；
        String urlName = URLEncoder.encode(fileName, "UTF-8");//防止中文和特殊字符乱码
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        URL imgUrl = ossClient.generatePresignedUrl(OOSClientUtil.BUCKET ,fileName ,expiration);//返回的图片路径，oss根目录加上阿里云的ENDPOINT地址，File.separator =\
        return imgUrl.toString();//将图片路径返回
    }

}
