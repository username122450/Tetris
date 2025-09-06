package controller;

import controller.blocks.*;

import java.util.Random;

//用于创建随机方块
public class BlockManage {
    private static Random rng = new Random();
    private static String[] type = new String[] {"I","O","LL","LR","T","ZL","ZR"};

    public BlockManage(Random rng) {
        this.rng = rng;
    }

    //在type中随机生成一个类型
    public static String radomType(){
        int index=rng.nextInt(7);
        return type[index];
    };

    //根据类型随机生成一个方块(7个中的一个)
    public static Block nextRadomBlock(String type){
        if(type.equals("I")){
            return new I_Block();
        } else if (type.equals("O")) {
            return new O_Block();
        }else if (type.equals("LL")) {
            return new LL_Block();
        }else if (type.equals("LR")) {
            return new LR_Block();
        }else if (type.equals("T")) {
            return new T_Block();
        }else if (type.equals("ZL")) {
            return new ZL_Block();
        }else{
            return new ZR_Block();
        }
    }

    //构造函数（应该用不到）
    public String[] getType() {
        return type;
    }
    public void setType(String[] type) {
        this.type = type;
    }
}
