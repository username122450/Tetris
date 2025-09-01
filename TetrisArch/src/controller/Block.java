package controller;

public class Block {
    protected int[][][] shade = new int[4][4][4];

    //判断方块向下移动碰撞
    public boolean ifMoveDown(){
        return false;
    }

    //判断方块的左右移动碰撞
    public boolean ifMoveCrash(){
        return false;
    }

    //判断方块的旋转碰撞
    public boolean ifRotateCrash(){
        return false;
    }

    //方块向下移动
    public void MoveDown(){}

    //方块左右移动
    public void blockMove(){}

    //方块旋转
    public void blockRotate(){}

    //生成方块
    public Block createBlock(){
        return null;
    }
}
