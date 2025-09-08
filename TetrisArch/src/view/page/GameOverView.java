package view.page;

import globle.Global;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import sound.MusicControl;
import view.AbstractGameView;

public class GameOverView extends AbstractGameView {
    //protected boolean isReStart = false;
    private JLabel gameOverLabel;
    private JButton restartButton;
    private JButton menuButton;

    //初始化
    public GameOverView() {
    }

    /*@Override
    public void start() {
        if (isReStart) {
            System.out.println("结束界面已经创建，无法重复创建");
            return;
        }
        isReStart = true;
        super.start();
    }*/

    @Override
    protected void init() {
        setSize(800, 648);
        setBackground(Color.LIGHT_GRAY);
    }

    //绘制结束窗口
    @Override
    protected void draw() {
        //设置界面关闭模式
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置界面可视化
        this.setVisible(false);
        //标题
        this.setTitle("失败界面");

        /// 组件设置
        //创建提示标签
        gameOverLabel = new JLabel("很遗憾，方块到顶啦!!!");
        gameOverLabel.setBackground(Color.LIGHT_GRAY);
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER); // 【新增】让标签内的文字水平居中
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 【新增】让标签组件在容器中水平居中
        //设置标签字体属性为黑体、加粗、36px
        gameOverLabel.setFont(new Font("SimHei", Font.BOLD, 36));
        //重新开始按钮
        restartButton = new JButton("重新开始");
        //设置字体属性
        restartButton.setFont(new Font("SimHei", Font.BOLD, 28));
        //设置推荐大小
        restartButton.setPreferredSize(new Dimension(250, 70));
        //设置黄色背景
        restartButton.setBackground(Color.WHITE);
        //设置黑色字体
        restartButton.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        restartButton.setFocusPainted(false);


        // 菜单按钮
        menuButton = new JButton("返回菜单");
        //设置字体属性
        menuButton.setFont(new Font("SimHei", Font.BOLD, 28));
        //设置推荐大小
        menuButton.setPreferredSize(new Dimension(250, 70));
        //设置红色背景
        menuButton.setBackground(Color.WHITE);
        //设置黑色字体
        menuButton.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        menuButton.setFocusPainted(false);

        //将按钮放入临时容器panel,再放入jframe
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(100));
        panel.add(gameOverLabel);
        panel.add(Box.createVerticalStrut(80));
        panel.add(restartButton);
        panel.add(Box.createVerticalStrut(60));
        panel.add(menuButton);
        this.add(panel, BorderLayout.CENTER);
        //设置按钮水平居中
        menuButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        //设置按钮水平居中
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //设置界面居中
        this.setLocationRelativeTo(null);
        //界面可视
        this.setVisible(true);
    }

    //处理用户操作
    @Override
    protected void handleInput() {
        //添加鼠标监听
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 重新开始游戏
                MusicControl.playSound("button", false);
                MusicControl.playSound("background", true);
                GameView gameView = new GameView();
                gameView.start();
                //isReStart = false;
                // 关闭当前窗口
                dispose();
            }
        });
 
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicControl.playSound("button", false);
                MusicControl.playSound("title", true);
                // 返回主菜单
                MenuView menuView = new MenuView();
                menuView.start();
                //isReStart = false;
                // 关闭当前窗口
                dispose();
            }
        });

    }
}
