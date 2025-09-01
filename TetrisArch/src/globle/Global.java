package globle;

import View.AbstractGameView;

//定义全局变量
public class Global {
    //玩家得分
    private int sore;

    //界面长宽
    public static int wide = 800;
    public static int height = 1200;

    //当前所在的界面
    /*
        page包中创建的所有界面
     */
    public static AbstractGameView currentView;

    //改变当前所在的界面
    public static void changeCurrentView(AbstractGameView currentView) {};

    //获取当前界面
    public static void getCurrentView(AbstractGameView currentView) {};
}
