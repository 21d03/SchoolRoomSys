package com.dl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 图片处理工具类
 */
@Slf4j
@Component
public class ImageUtil {

    @Value("${upload.imagePath}")
    private String imagePath;
    
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

    /**
     * 将图片路径字符串转换为Base64编码列表
     * @param imagePathsStr 图片路径字符串，多个路径用逗号分隔
     * @return Base64编码列表
     */
    public List<String> getBase64Images(String imagePathsStr) {
        List<String> base64Images = new ArrayList<>();
        
        if (imagePathsStr == null || imagePathsStr.trim().isEmpty()) {
            return base64Images;
        }
        
        String[] imagePaths = imagePathsStr.split(",");
        for (String path : imagePaths) {
            if (path.trim().isEmpty()) {
                continue;
            }
            
            try {
                String base64 = convertImageToBase64(path.trim());
                if (base64 != null) {
                    base64Images.add(base64);
                }
            } catch (Exception e) {
                log.error("转换图片到Base64失败: {}", path, e);
            }
        }
        
        return base64Images;
    }
    
    /**
     * 将单个图片路径转换为Base64编码
     * @param relativePath 图片相对路径
     * @return Base64编码的图片字符串
     */
    private String convertImageToBase64(String relativePath) {
        try {
            File file = new File(imagePath, relativePath);
            if (!file.exists() || !file.isFile()) {
                log.warn("图片文件不存在: {}", file.getAbsolutePath());
                return null;
            }
            
            byte[] fileContent = Files.readAllBytes(file.toPath());
            String mimeType = determineMimeType(relativePath);
            
            return "data:" + mimeType + ";base64," + Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            log.error("读取图片文件失败: {}", relativePath, e);
            return null;
        }
    }
    
    /**
     * 根据文件扩展名确定MIME类型
     * @param filePath 文件路径
     * @return MIME类型
     */
    private String determineMimeType(String filePath) {
        String extension = filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase();
        
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            case "bmp":
                return "image/bmp";
            default:
                return "application/octet-stream";
        }
    }
} 