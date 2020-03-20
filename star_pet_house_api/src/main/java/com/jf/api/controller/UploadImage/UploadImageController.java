package com.jf.api.controller.UploadImage;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/*
 *@description:
 *@author jiafeng
 *@date 2020/3/20 0020 14:39
 */
@RestController
public class UploadImageController {

    @RequestMapping("/uploadImage")
    @CrossOrigin
    public Map<String,Object> uploadImage(@RequestPart("image")MultipartFile imageFile){
        Map<String,Object> map = new HashMap<>();
        return map;
    }
}
