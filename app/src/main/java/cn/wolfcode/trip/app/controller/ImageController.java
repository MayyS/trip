package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.app.util.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ImageController {

    @PostMapping("/images")
    public Map<String,Object> uploadImage(MultipartFile file){
        Map<String ,Object>result=new HashMap<>();
        String uploadPath=UploadUtil.commonPath;
        uploadPath=UploadUtil.upload(file,uploadPath);
        if("".equals(uploadPath)){
            result.put("status",0);
            result.put("msg","fail");
        }
        result.put("status",1);
         result.put("url","/upload/");
        return result;
    }
}
