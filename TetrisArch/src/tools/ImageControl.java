package tools;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageControl {
    //存储图片，String存储简单名字
    public static Map<String, Image> images = new HashMap<String, Image>();

    //加载图片
    /*
    path:图片路径
    String：简单名字
     */
    public void loadImage(String Path){}

    //卸载图片
    /*
    path:图片路径
    String：简单名字
     */
    public void unloadImage(String path){}
}
