package View.page;

import View.AbstractGameView;
import View.ViewStatus;
import controller.BlockManage;

public class ClassicModeGameView extends AbstractGameView {
    private BlockManage blockManage;
    private int score;

    @Override
    protected void draw() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void handleInput() {

    }

    //绘制游戏窗口
    public void drawWindows(){
        drawStaticBlocks();
        drawMoveBlocks();
        drawSore();
    };

    //绘制已经到无法移动的方块
    private void drawStaticBlocks(){};

    //绘制正在移动的方块
    private void drawMoveBlocks(){};

    //绘制分数
    private void drawSore(){};
}
