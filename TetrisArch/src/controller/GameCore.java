package controller;

import view.AbstractGameView;
import view.page.GameView;

public class GameCore {
    //当前所在的界面
    public static view.AbstractGameView currentView = null;

    //经典模式游戏流程控制
    public  void gameStart() {
        Setup();
        /*
        游戏核心逻辑：
        {
        设置第一个视图：
            do{
            获取当前视图的状态
            根据界面的不同状态执行不同的语句
            switch{
                。。。。。
                }
            }
        }
         */
        //释放资源
        End();
    }

    //环境初始化：负责加载游戏必要的资源:音乐图片等：
    private void Setup (){}

    //游戏结束关闭资源：卸载资源
    private void End(){}

    //视图切换
    /*
    调用旧视图 onExit退出旧的视图，
    设置新视图并 onEnter 进入新的视图；
     */
    public void changeView(view.AbstractGameView next) {
    }

    public static AbstractGameView getCurrentView() {
        return currentView;
    }

    //推进数据更新，页面切换等（只负责调用函数）
    //调用函数根据当前视图更新
    //调用函数更新游戏数据
    //调用函数更新画面
    public void tick() {

    }
}
