package view;

import javax.swing.*;

// 抽象游戏视图基类：
public abstract class AbstractGameView extends JFrame {
    // 子类必须实现的方法：
    protected abstract void init();
    protected abstract void draw();
    protected abstract void handleInput();
    
    // 公共启动方法
    public void start() {
        init();
        draw();
        handleInput();
    }

}
