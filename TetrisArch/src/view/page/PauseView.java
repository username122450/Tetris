package view.page;

import globle.Global;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import sound.MusicControl;
import view.AbstractGameView;

public class PauseView extends AbstractGameView {
    /*
    界面设计{
        需要用到的组件直接创建对应的属性
        JPanel panel;
        JLabel label;
    }
     */
    private JPanel mainPanel;
    private JButton continueButton;
    private JButton exitButton;
    private JButton settingButton;
    private JButton helpButton;
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

        // 创建主面板
        mainPanel = new JPanel(new BorderLayout());
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new BoxLayout(ButtonPanel, BoxLayout.Y_AXIS));
        ButtonPanel.setBackground(new Color(200, 200, 200));

        continueButton = new JButton("Continue");
        exitButton = new JButton("Exit");
        settingButton = new JButton("Setting");
        helpButton = new JButton("Help");

    // 设置按钮大小 - 统一使用这种方法
        int buttonWidth = 300;
        int buttonHeight = 100;
        Dimension buttonSize = new Dimension(buttonWidth, buttonHeight);

    // 设置所有按钮的尺寸（这三行都要有！）
        continueButton.setPreferredSize(buttonSize);
        continueButton.setMaximumSize(buttonSize);
        continueButton.setMinimumSize(buttonSize);
        continueButton.setFocusPainted(false);

        exitButton.setPreferredSize(buttonSize);
        exitButton.setMaximumSize(buttonSize);
        exitButton.setMinimumSize(buttonSize);

        settingButton.setPreferredSize(buttonSize);
        settingButton.setMaximumSize(buttonSize);
        settingButton.setMinimumSize(buttonSize);

        helpButton.setPreferredSize(buttonSize);
        helpButton.setMaximumSize(buttonSize);
        helpButton.setMinimumSize(buttonSize);

        // 设置居中
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 设置按钮样式
        continueButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        exitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        settingButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        helpButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        continueButton.setBackground(Color.WHITE);
        exitButton.setBackground(Color.WHITE);
        settingButton.setBackground(Color.WHITE);
        helpButton.setBackground(Color.WHITE);

        // 设置字体
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        continueButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);
        settingButton.setFont(buttonFont);
        helpButton.setFont(buttonFont);

        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        exitButton.setHorizontalAlignment(SwingConstants.CENTER);
        settingButton.setHorizontalAlignment(SwingConstants.CENTER);
        helpButton.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加到面板
        ButtonPanel.add(Box.createVerticalGlue());
        ButtonPanel.add(continueButton);
        ButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        ButtonPanel.add(settingButton);
        ButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        ButtonPanel.add(helpButton);
        ButtonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        ButtonPanel.add(exitButton);
        ButtonPanel.add(Box.createVerticalGlue());

        mainPanel.add(ButtonPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        // 设置窗口大小
        this.setSize(500, 600);
    }//不一定要用，如果直接在属性中创建（JFrame j = new JFrame()）就不需要需要

    @Override
    protected void draw() {
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
    }

    @Override
    protected void init() {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                if (gameView != null) {
                    gameView.resumeGame();
                }
            }
        });
        draw();
        handleInput();
    }

    @Override
    protected void handleInput() {
        continueButton.addActionListener(e -> {
            MusicControl.playSound("button", false);
            this.setVisible(false);
            this.dispose();
            if (gameView != null) {
                gameView.resumeGame();
            }
        });
        settingButton.addActionListener(e -> {
            MusicControl.playSound("button", false);
            this.setVisible(false);
            SettingView.setCaller(this);
            Global.settingView.start();
        });

        helpButton.addActionListener(e -> {
            MusicControl.playSound("button", false);
           this.setVisible(false);
           HelpView.setCaller(this);
           HelpView helpView = new HelpView();
           helpView.start();
        });

        exitButton.addActionListener(e -> {
            MusicControl.pauseSound("background");
            MusicControl.playSound("button", false);
            MusicControl.playSound("title", true);
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
