package com.example.tbwork.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtils {
    public static String imageUpload(MultipartFile file) throws Exception {//返回图片文件url
        String folder = "C:/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8085/img/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
