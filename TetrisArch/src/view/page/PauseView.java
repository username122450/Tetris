package view.page;

import view.AbstractGameView;

import javax.swing.text.View;

public class PauseView extends AbstractGameView {
    /*
    界面设计{
        需要用到的组件直接创建对应的属性
        JPanel panel;
        JLabel label;
    }
     */

    /** 公共启动方法 用于外部调用
     public void start() {
     init();
     draw();
     handleInput();
     }
     */

    public PauseView() {}//不一定要用，如果直接在属性中创建（JFrame j = new JFrame()）就不需要需要

    @Override
    protected void draw() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void handleInput() {

    }
}
