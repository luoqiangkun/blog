package com.luospace.blog.controller.commom;

import com.luospace.blog.common.Constants;
import com.luospace.blog.common.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class UploadController {
    @RequestMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return Result.failed("无上传文件");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateNowStr = sdf.format(d);
        String dirPath = Constants.FILE_UPLOAD_DIC + dateNowStr;
        File dir = new File(dirPath);
        try {
            if (!dir.exists()) {
                if (!dir.mkdir()) {
                    throw new IOException("文件夹创建失败");
                }
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            StringBuilder fileBuilder = new StringBuilder();
            fileBuilder.append(dirPath).append("/").append(UUID.randomUUID()).append(".").append(suffixName);
            String filePath = fileBuilder.toString();
            File dest = new File(filePath);
            file.transferTo(dest);
            Map<String,String> data = new HashMap<String,String>();
            data.put("fileName",fileName);
            data.put("suffixName",suffixName);
            data.put("filePath",filePath);
            return Result.success(data);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.failed("文件上传失败");
        }
    }

}
