import controller.GameCore;
import view.page.GameView;

public class Main {
    public static void main(String[] args) {
        //程序启动入口;
        GameCore gameCore = new GameCore();
        gameCore.gameStart();
    }
}