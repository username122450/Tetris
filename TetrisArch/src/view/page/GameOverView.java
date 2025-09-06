package view.page;

import globle.Global;
import view.AbstractGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverView extends AbstractGameView {
    protected boolean isReStart;
    private JPanel gameOverPanel;
    private JLabel gameOverLabel;
    private JButton restartButton;
    private JButton menuButton;


    //游戏结束界面调用;
    public void gameOver() {
        init();
    }


    //初始化
    public GameOverView() {
        //设置界面大小
        this.setSize(Global.wide, Global.height);
        //设置界面关闭模式
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置界面可视化
        this.setVisible(false);
        //标题
        this.setTitle("失败界面");

        /// 组件设置
        //创建提示标签
        gameOverLabel = new JLabel("很遗憾，方块到顶啦");
        //设置标签字体属性为黑体、加粗、36px
        gameOverLabel.setFont(new Font("SimHei", Font.BOLD, 36));
        //将提示标签加入界面
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
        gameOverPanel.add(Box.createVerticalStrut(30));
        gameOverPanel.add(gameOverLabel);

        //重新开始按钮
        restartButton = new JButton("重新开始");
        //设置字体属性
        restartButton.setFont(new Font("SimHei", Font.BOLD, 28));
        //设置推荐大小
        restartButton.setPreferredSize(new Dimension(250, 70));
        //设置黄色背景
        restartButton.setBackground(Color.YELLOW);
        //设置黑色字体
        restartButton.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        restartButton.setFocusPainted(false);
        //设置按钮水平居中
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 菜单按钮
        menuButton = new JButton("返回菜单");
        //设置字体属性
        menuButton.setFont(new Font("SimHei", Font.BOLD, 28));
        //设置推荐大小
        menuButton.setPreferredSize(new Dimension(250, 70));
        //设置红色背景
        menuButton.setBackground(Color.RED);
        //设置黑色字体
        menuButton.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        menuButton.setFocusPainted(false);
        //设置按钮水平居中
        menuButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        //将按钮放入临时容器panel,再放入jframe
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(50));
        panel.add(restartButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(menuButton);
        this.add(panel, BorderLayout.SOUTH);

        this.add(gameOverLabel);
        this.pack();

    }

    @Override
    protected void init() {
        //界面可视
        this.setVisible(true);

        //对于输入操作响应
        handleInput();
    }

    //绘制结束窗口
    @Override
    protected void draw() {
    }

    //处理用户操作
    @Override
    protected void handleInput() {
        //添加鼠标监听
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 重新开始游戏
                GameView gameView = new GameView();
                gameView.start();
                // 关闭当前窗口
                dispose();
            }
        });

        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 返回主菜单
                MenuView menuView = new MenuView();
                menuView.start();
                // 关闭当前窗口
                dispose();
            }
        });
    }
}
