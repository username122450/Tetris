package view.page;

import View.AbstractGameView;

public class GameView extends AbstractGameView {

    //绘制地图
    private void map(){};

    //绘制正在移动的方块
    private void drawMoveBlocks(){};

    //绘制分数
    private void drawSore(){};

    //用于最后绘制窗口和游戏内容
    /*
        调用函数：
            初始化窗口
            绘制边界
            绘制窗口
            绘制地图
     */
    @Override
    protected void draw() {
    }


    //初始化游戏数据
    @Override
    protected void init() {
    }

    //用户输入
    /*
    w：旋转
    s：向下加速
    a：左移一格
    d：右移一格
     */
    @Override
    protected void handleInput() {
    }

    //重写方法：
    /*
    当进入游戏页面时调用初始化：
    inite
     */
    @Override
    public void onEnter() {
        super.onEnter();
    }

    //游戏结束后：
    /*
    1.返回开始菜单
    2.重新开始
     */
    @Override
    public void onExit() {
        super.onExit();
    }

    //更新游戏数据
    /*
    收集玩家的操作
    更新地图
    移动方块位置
    玩家分数
    绘制地图
     */
    @Override
    public void update() {
        super.update();
    }
}
