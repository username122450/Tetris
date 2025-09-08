package view.page;

import view.AbstractGameView;

import javax.swing.*;
import java.awt.*;

public class PauseView extends AbstractGameView {
    /*
    界面设计{
        需要用到的组件直接创建对应的属性
        JPanel panel;
        JLabel label;
    }
     */
    JPanel mainPanel;
    JButton continueButton;
    JButton exitButton;
    private final GameView gameView;

    /** 公共启动方法 用于外部调用
     public void start() {
     init();
     draw();
     handleInput();
     }
     */
    @Override
    public void start(){
        init();
    }

    public PauseView(GameView gameView) {
        this.gameView = gameView;

        // 创建主面板，使用null布局实现绝对定位
        mainPanel = new JPanel(null);
        mainPanel.setBackground(new Color(200, 200, 200)); // 亮灰色背景
        this.add(mainPanel);

        continueButton = new JButton("Continue");
        exitButton = new JButton("Exit");

        // 设置按钮大小
        int buttonWidth = 120;
        int buttonHeight = 40;
        continueButton.setSize(buttonWidth, buttonHeight);
        exitButton.setSize(buttonWidth, buttonHeight);

        // 计算居中位置
        int panelWidth = 400; // 假设面板宽度
        int panelHeight = 300; // 假设面板高度
        int centerX = (panelWidth - buttonWidth) / 2;

        // 设置按钮位置（竖着排列）
        int buttonSpacing = 20; // 按钮间距
        continueButton.setLocation(centerX, 80);
        exitButton.setLocation(centerX, 100 + buttonHeight + buttonSpacing);

        // 设置按钮样式
        continueButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        continueButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);

        // 设置字体和居中显示
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        continueButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加到主面板
        mainPanel.add(continueButton);
        mainPanel.add(exitButton);

        // 设置面板大小
        mainPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

    }//不一定要用，如果直接在属性中创建（JFrame j = new JFrame()）就不需要需要

    @Override
    protected void draw() {
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400, 300); // 更合适的尺寸
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    @Override
    protected void init() {
        draw();
        handleInput();

    }

    @Override
    protected void handleInput() {
        continueButton.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
            if (gameView != null) {
                gameView.resumeGame();
            }
        });
        exitButton.addActionListener(e -> {
            // 返回主菜单
            this.setVisible(false);
            dispose();
            if (gameView != null) {
                gameView.dispose();
            }
            GameView.gameRunning = false;
            // 返回主菜單
            MenuView menuView = new MenuView();
            menuView.start();
        });
    }
}
