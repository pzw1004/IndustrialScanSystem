package com.industrialscansystem.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class SaveUploadFile {

    /**
     * 文件保存方法，多文件上传专用
     * @param file  MultipartFile多文件数组
     * @param destination
     * @throws IOException
     * @throws IllegalStateException
     */
    public void saveFile(MultipartFile file, String destination) throws IllegalStateException, IOException {
        // 获取上传的文件名称，并结合存放路径，构建新的文件名称
        String filename = file.getOriginalFilename();
        File tempFile =new File( filename.trim());
        filename = tempFile.getName();

        File filepath = new File(destination, filename);

        // 判断路径是否存在，不存在则新创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }

        // 将上传文件保存到目标文件目录
        file.transferTo(new File(destination + File.separator + filename));
    }


}
