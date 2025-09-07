package view.page;

import sound.MusicControl;
import view.AbstractGameView;
import view.page.helper_classes.CustomFontLoader;
import view.page.helper_classes.RoundedBorder;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.View;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class SettingView extends AbstractGameView {
    /*
    界面设计{
        需要用到的组件直接创建对应的属性
        JPanel panel;
        JLabel label;
    }
     */
    private static boolean settingRunning = false;
    //开启音乐单选框
    JRadioButton open = new JRadioButton("开");

    //关闭音乐单选框
    JRadioButton close = new JRadioButton("关");
    //开启音乐关闭音乐按钮组
    ButtonGroup group = new ButtonGroup();
    //音量滑条
    JSlider slider = new JSlider(0,100,90);
    //返回按钮
    JButton back = new JButton("返回");

    /** 公共启动方法 用于外部调用
     public void start() {
     init();
     draw();
     handleInput();
     }
     */

    public SettingView() {}//不一定要用，如果直接在属性中创建（JFrame j = new JFrame()）就不需要需要

    @Override
    protected void draw() {
        //音乐开关容器
        JPanel panel = new JPanel();
        panel.setBorder(null);
        JLabel music = new JLabel("音乐开关:");
        music.setFont(new Font(music.getFont().getName(), Font.PLAIN, 40));
        panel.add(music);

        open.setBackground(Color.LIGHT_GRAY);
        open.setFocusable(false);
        open.setFont(new Font(open.getFont().getName(), Font.PLAIN, 24));
        close.setFocusable(false);
        close.setBackground(Color.LIGHT_GRAY);
        close.setFont(new Font(open.getFont().getName(), Font.PLAIN, 24));

        group.add(open);
        group.add(close);
        open.setSelected(true);
        panel.add(open);
        panel.add(close);
        panel.setBackground(Color.LIGHT_GRAY);

        //音量滑块容器
        JPanel panel1 = new JPanel();
        panel1.setBorder(null);
        JLabel label = new JLabel("音量:");
        label.setFont(new Font(label.getFont().getName(), Font.PLAIN, 40));
        panel1.add(label);
        slider.setBackground(Color.LIGHT_GRAY);
        panel1.add(slider);
        panel1.setBackground(Color.LIGHT_GRAY);

        //返回容器
        JPanel panel3 = new JPanel();
        panel3.setBorder(null); // 移除面板自身的边框
        panel3.setBackground(Color.LIGHT_GRAY);
        back.setFont(new Font(back.getFont().getName(), Font.PLAIN, 50));
        back.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        back.setBackground(Color.WHITE);
        back.setFocusable(false);
        panel3.add(back);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(Box.createVerticalStrut(70));
        panel2.add(panel);
        panel2.add(panel1);
        panel2.add(panel3);
        panel2.add(Box.createVerticalStrut(50));
        panel2.setBackground(Color.LIGHT_GRAY);

        this.add(panel2);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    protected void init() {
        setSize(800, 648);
        this.setTitle("设置");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.LIGHT_GRAY);
    }

    @Override
    protected void handleInput() {
        MusicControl m = new MusicControl();

        ActionListener listener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JRadioButton button = (JRadioButton) e.getSource();
                var names = MusicControl.clips.keySet();
                if (button == open) {
                    open.setSelected(true);
                    //开启音乐
                    for (String name : names) {
                        m.resumeSound(name);
                    }
                }
                else {
                    close.setSelected(true);
                    //关闭音乐
                    for (String name : names) {
                        m.pauseSound(name);
                    }
                }
            }
        };

        open.addActionListener(listener1);
        close.addActionListener(listener1);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = slider.getValue();
                //修改播放音乐的音量
                var names = MusicControl.clips.keySet();
                for (String name : names) {
                    m.setSoundVolume(name, value);
                }
            }
        });

        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicControl.playSound("button", false);
                MenuView menuView = new MenuView();
                menuView.start();
                settingRunning = false;
                dispose();
            }
        });
    }

    // 重写start方法
    @Override
    public void start() {
        if (settingRunning) {
            System.out.println("设置已经在运行中，无法重复启动");
            return;
        }
        settingRunning = true;
        super.start();
    }
}
