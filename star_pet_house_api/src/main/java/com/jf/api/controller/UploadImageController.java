package com.jf.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jf.api.utils.OOSClientUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/20 0020 14:39
 */
@RestController
@RequestMapping("upload")
public class UploadImageController {

    @CrossOrigin
    @RequestMapping("/uploadImage")
    public Map<String,Object> upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> result = new HashMap<>();
        String imageUrl = OOSClientUtil.uploadImg(file);
        result.put("src",imageUrl);
        map.put("code","0");
        map.put("msg","success");
        map.put("data",result);
        return map;
    }
}
