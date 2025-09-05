package view.page;

import globle.Global;
import view.AbstractGameView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverView extends AbstractGameView {
    protected boolean isReStart;

    //初始化
    @Override
    protected void init() {
        //默认不重新开始游戏
        isReStart = false;
        jframe = new javax.swing.JFrame("失败界面");
        //设置当前所在视图为结束界面
        controller.GameCore.currentView = this;
    }

    //绘制结束窗口
    @Override
    protected void draw() {
        //设置界面大小
        jframe.setSize(Global.wide, Global.height);
        //设置界面关闭模式
        jframe.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        //对于输入操作响应
        handleInput();
        //创建提示标签
        javax.swing.JLabel gameOverLabel = new javax.swing.JLabel("很遗憾，方块到顶啦");
        //设置标签字体属性为黑体、加粗、36px
        gameOverLabel.setFont(new java.awt.Font("SimHei", java.awt.Font.BOLD, 36));
        //将提示标签加入界面
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        panel.add(javax.swing.Box.createVerticalStrut(30));
        panel.add(gameOverLabel);
        jframe.add(panel);
        jframe.pack();
        //设置界面居中
        jframe.setLocationRelativeTo(null);
        //设置界面可视化
        jframe.setVisible(true);
    }

    //处理用户操作
    @Override
    protected void handleInput() {
        //重新开始按钮
        javax.swing.JButton restartBtn = new javax.swing.JButton("重新开始");
        //设置字体属性
        restartBtn.setFont(new java.awt.Font("SimHei", java.awt.Font.BOLD, 28));
        //设置推荐大小
        restartBtn.setPreferredSize(new java.awt.Dimension(250, 70));
        //设置黄色背景
        restartBtn.setBackground(Color.YELLOW);
        //设置黑色字体
        restartBtn.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        restartBtn.setFocusPainted(false);
        //设置按钮水平居中
        restartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 菜单按钮
        javax.swing.JButton menuBtn = new javax.swing.JButton("返回菜单");
        //设置字体属性
        menuBtn.setFont(new java.awt.Font("SimHei", java.awt.Font.BOLD, 28));
        //设置推荐大小
        menuBtn.setPreferredSize(new java.awt.Dimension(250, 70));
        //设置红色背景
        menuBtn.setBackground(Color.RED);
        //设置黑色字体
        menuBtn.setForeground(Color.BLACK);
        //去除聚焦时出现的边框
        menuBtn.setFocusPainted(false);
        //设置按钮水平居中
        menuBtn.setAlignmentX(javax.swing.JComponent.CENTER_ALIGNMENT);

        //添加鼠标监听
        restartBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isReStart = true;
                onExit();
            }
        });

        menuBtn.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.GameCore.currentView = new MenuView();
                onExit();
            }
        });

        //将按钮放入临时容器panel,再放入jframe
        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
        panel.add(javax.swing.Box.createVerticalStrut(50));
        panel.add(restartBtn);
        panel.add(javax.swing.Box.createVerticalStrut(30));
        panel.add(menuBtn);
        jframe.add(panel, java.awt.BorderLayout.SOUTH);


    }

    //进入界面初始化
    @Override
    public void onEnter() {
        //初始化数据
        init();
        //绘制界面
        draw();
    }

    //数据更新方法
    @Override
    public void update() {
        super.update();
    }

    //退出方法;重写
    @Override
    public void onExit(){
        //销毁界面
        jframe.dispose();
    };

}
