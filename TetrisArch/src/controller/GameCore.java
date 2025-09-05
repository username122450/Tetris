package controller;

import tools.ImageControl;
import tools.MusicControl;
import view.AbstractGameView;
import view.ViewStatus;
import view.page.MenuView;

public class GameCore {
    //当前所在的界面
    public static view.AbstractGameView currentView = null;

    //程序运行流程
    public  void gameStart() {
        Setup();

        //设置第一个视图，主菜单
        changeView(new MenuView());

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
        do{
            //获取当前视图的状态
            ViewStatus status = currentView.getStatus();

            //根据界面的不同状态执行不同的语句
            switch(status){
                case NONE:
                    break;
                case ON_EXIT:
                    break;
                case ON_ENTER:
                    break;
                case ON_UPDATE:
                    currentView.update();
                    break;
                default:
                    break;
            }

        }while(currentView != null);

        //释放资源
        End();
    }

    //环境初始化：负责加载游戏必要的资源:音乐图片等：
    private void Setup (){
        //初始化音乐控制
        MusicControl musicControl = new MusicControl();
        //加载音乐
        musicControl.loadSound("","");
        //播放音乐
        musicControl.playSound("",true);

        //初始化图片控制
        ImageControl imageControl = new ImageControl();
        //加载图片
        imageControl.loadImage("");

        System.out.println("游戏资源加载完毕");
    }

    //游戏结束关闭资源：卸载资源
    private void End(){
        if(currentView != null){
            currentView.onExit();
        }

        //卸载音乐
        MusicControl musicControl = new MusicControl();
        musicControl.unloadSound("");

        //卸载图片
        ImageControl imageControl = new ImageControl();
        imageControl.loadImage("");

        System.out.println("游戏资源已释放");
    }

    //视图切换
    /*
    调用旧视图 onExit退出旧的视图，
    设置新视图并 onEnter 进入新的视图；
     */
    public void changeView(view.AbstractGameView next) {
        if(currentView != null){
            currentView.onExit();
        }
        currentView = next;
        if(currentView != null){
            currentView.onEnter();
        }
    }

    public static AbstractGameView getCurrentView() {
        return currentView;
    }
    //游戏流程
    //推进数据更新，页面切换等（只负责调用函数）
    //调用函数根据当前视图更新
    //调用函数更新游戏数据
    //调用函数更新画面
    /*public void tick() {

    }*/
}
