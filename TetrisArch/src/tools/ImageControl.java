package tools;

import image.ImageResource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageControl {
    //存储图片，String存储简单名字
    public static Map<String, Image> images = new HashMap<>();

    /**
     * 从配置文件加载所有图片
     */
    public void loadAllImages() {
        ImageResource resource = new ImageResource();
        Map<String, String> imagePaths = resource.readToMap();
        for (Map.Entry<String, String> entry : imagePaths.entrySet()) {
            loadImage(entry.getValue(), entry.getKey());
        }
    }

    public void loadImage(String path, String name) {
        if (name == null || name.isEmpty()) {
            System.err.println("图片名称不能为空");
            return;
        }

        try {
            BufferedImage image = ImageIO.read(new FileInputStream(new File(path)));
            if (image != null) {
                images.put(name, image);
                System.out.println("图片加载成功: " + name);
            } else {
                System.err.println("无法读取图片文件: " + path);
            }
        } catch (IOException e) {
            System.err.println("图片加载失败: " + path + " - " + e.getMessage());
        }
    }

    //卸载图片
    /*
    path:图片路径
    name：简单名字
     */
    public void unloadImage(String name) {
        if (images.remove(name) != null) {
            System.out.println("图片已卸载: " + name);
        } else {
            System.err.println("图片不存在: " + name);
        }
    }
}
