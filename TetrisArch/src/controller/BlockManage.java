package controller;

import controller.blocks.*;

import java.util.Random;

//用于创建随机方块
public class BlockManage {
    private final Random rng;
    private String[] type = new String[] {"I","O","LL","LR","T","ZL","ZR"};

    public BlockManage(Random rng) {
        this.rng = rng;
    }

    //在type中随机生成一个类型
    public static String radomType(){
        return "";
    };

    //根据类型随机生成一个方块(7个中的一个)
    public static Block nextRadomBlock(String type){
        return new Block();
    }

    //构造函数（应该用不到）
    public String[] getType() {
        return type;
    }
    public void setType(String[] type) {
        this.type = type;
    }
}
