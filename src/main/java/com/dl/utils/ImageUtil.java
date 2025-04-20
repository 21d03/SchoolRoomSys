package com.dl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * 图片处理工具类
 */
@Slf4j
public class ImageUtil {

    /**
     * 将图片文件转换为Base64编码
     * @param imagePath 图片完整路径
     * @return Base64编码字符串，如果转换失败则返回null
     */
    public static String imageToBase64(String imagePath) {
        if (!StringUtils.hasText(imagePath)) {
            return null;
        }
        
        File file = new File(imagePath);
        if (!file.exists() || !file.isFile()) {
            log.error("图片文件不存在: {}", imagePath);
            return null;
        }
        
        try (FileInputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            inputStream.read(bytes);
            
            // 获取文件类型
            String contentType = getContentType(file);
            if (contentType == null) {
                log.error("无法确定图片类型: {}", imagePath);
                return null;
            }
            
            // 构建Base64字符串，格式为: data:image/jpeg;base64,/9j/4AAQSkZ...
            String base64Prefix = "data:" + contentType + ";base64,";
            String base64Data = Base64.getEncoder().encodeToString(bytes);
            
            return base64Prefix + base64Data;
            
        } catch (IOException e) {
            log.error("图片转Base64失败", e);
            return null;
        }
    }
    
    /**
     * 获取文件的MIME类型
     * @param file 文件
     * @return MIME类型，例如"image/jpeg"
     */
    private static String getContentType(File file) {
        try {
            Path path = Paths.get(file.getAbsolutePath());
            return Files.probeContentType(path);
        } catch (IOException e) {
            log.error("获取文件类型失败", e);
            // 根据扩展名判断常见图片类型
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
                return "image/jpeg";
            } else if (fileName.endsWith(".png")) {
                return "image/png";
            } else if (fileName.endsWith(".gif")) {
                return "image/gif";
            } else if (fileName.endsWith(".bmp")) {
                return "image/bmp";
            } else if (fileName.endsWith(".webp")) {
                return "image/webp";
            }
            return null;
        }
    }
} 