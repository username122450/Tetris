package controller;

import image.ImageControl;
import sound.MusicControl;
import view.page.MenuView;

public class GameCore {
    //程序运行流程
    public void gameStart() {
        Setup();
        
        // 直接启动主菜单
        MenuView menuView = new MenuView();
        menuView.start();

        //释放资源
        End();
    }

    //环境初始化：负责加载游戏必要的资源:音乐图片等：
    private void Setup (){
        //初始化音乐控制
        MusicControl musicControl = new MusicControl();
        //加载音乐
        musicControl.loadAllSounds();;
        //播放音乐
//        musicControl.playSound("",true);

        //初始化图片控制
//        ImageControl imageControl = new ImageControl();
//        //加载图片
//        imageControl.loadallImage();

        System.out.println("游戏资源加载完毕");
    }

    //游戏结束关闭资源：卸载资源
    private void End(){
        //卸载音乐
        MusicControl musicControl = new MusicControl();
        musicControl.unloadAllSounds();

        //卸载图片
//        ImageControl imageControl = new ImageControl();
//        imageControl.loadImage("","");

        System.out.println("游戏资源已释放");
    }
}
