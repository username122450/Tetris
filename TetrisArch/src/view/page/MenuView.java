package view.page;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.AbstractGameView;
import view.page.helper_classes.CustomFontLoader;
import view.page.helper_classes.OnClickEventHelper;
import view.page.helper_classes.RoundedBorder;

public class MenuView extends AbstractGameView {
    private static boolean menuRunning = false; // 防止重复创建菜单窗口
    private JFrame frame = new JFrame("Tetris");
    private JPanel panel = new JPanel();

    private JLabel title = new JLabel("Tetris");

    private JButton start = new JButton("Start");
    private JButton settings = new JButton("Settings");
    private JButton help = new JButton("Help");
    private JButton exit = new JButton("Exit");
    
    

    @Override
    public void start() {
        //防止一直创建菜单窗口
        if (menuRunning) {
            System.out.println("菜单已存在");
            return;
        }
        menuRunning = true;
        super.start();
    }
    
    //初始化主菜单
    @Override
    protected void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(941, 648);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));
        frame.setVisible(true);
    }

    //绘制主菜单界面
    /*
    开始游戏
    设置（暂时没有实际功能）
    排行榜（暂时没有实际功能）
    退出游戏
     */
    @Override
    protected void draw() {
        JLabel title = new JLabel("Tetris");
        title.setBounds(338, 10, 460, 148);
        title.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 100));
        title.setForeground(Color.decode("#1b1b1b"));
        panel.add(title);

        start.setBounds(360, 200, 200, 60);
        start.setBackground(Color.decode("#ffffff"));
        start.setForeground(Color.decode("#1b1b1b"));
        start.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        start.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        start.setFocusPainted(false);
        //设置点击按钮后的背景颜色
        OnClickEventHelper.setOnClickColor(start, Color.decode("#c2c2c2"), Color.decode("#ffffff"));

        panel.add(start);
        
        settings.setBounds(360, 300, 200, 60);
        settings.setBackground(Color.decode("#ffffff"));
        settings.setForeground(Color.decode("#1b1b1b"));
        settings.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        settings.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        settings.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(settings, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
        panel.add(settings);

        help.setBounds(360, 400, 200, 60);
        help.setBackground(Color.decode("#ffffff"));
        help.setForeground(Color.decode("#1b1b1b"));
        help.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        help.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        help.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(help, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
        panel.add(help);

        exit.setBounds(360, 500, 200, 60);
        exit.setBackground(Color.decode("#ffffff"));
        exit.setForeground(Color.decode("#1b1b1b"));
        exit.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        exit.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        exit.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(exit, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        handleInput();
    }

    //处理用户操作
    /*
    用户选择的功能
     */
    @Override
    protected void handleInput() {
        //开始游戏
        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                menuRunning = false;
                // 直接创建并启动游戏
                GameView gameView = new GameView();
                gameView.start();
                // 关闭当前菜单窗口
                frame.dispose();
            }
        });
        //退出游戏
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                menuRunning = false;
                onExit();
            }
        });
    }

    //退出游戏时调用
    public void onExit() {
        frame.dispose();
    }
}
