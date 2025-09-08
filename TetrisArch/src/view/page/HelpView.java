package view.page;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sound.MusicControl;
import view.AbstractGameView;
import view.page.helper_classes.CustomFontLoader;
import view.page.helper_classes.OnClickEventHelper;
import view.page.helper_classes.RoundedBorder;

public class HelpView extends AbstractGameView {
    /*
    界面设计{
        需要用到的组件直接创建对应的属性
        JPanel panel;
        JLabel label;
    }
     */

    /** 公共启动方法 用于外部调用
    public void start() {
        init();
        draw();
        handleInput();
    }
     */
    private JFrame frame;
    private JButton back;
    private JPanel panel;
    private JLabel text;
    private boolean isStarted = false;
    
    public HelpView() {//不一定要用，如果直接在属性中创建（JFrame j = new JFrame()）就不需要需要
        frame = new JFrame("Help");
        back = new JButton("back");
        panel = new JPanel();
        text = new JLabel("<html><div style='text-align: center; font-size: 16px;'>" +
                        "<strong>游戏控制说明</strong><br><br>" +
                        "• <b>W 键</b> 或 <b>↑键</b> - 旋转方块<br><br>" +
                        "• <b>A 键</b> 或 <b>←键</b> - 向左移动<br><br>" +
                        "• <b>D 键</b> 或 <b>→键</b> - 向右移动<br><br>" +
                        "• <b>S 键</b> 或 <b>↓键</b> - 加速下降<br><br>" +
                        "• <b>空格键</b> - 瞬间下落<br>" +
                        "</div></html>");
    }

    @Override
    protected void draw() {
        panel.removeAll();
        back.setBounds(50, 380, 200, 61);
        back.setBackground(Color.decode("#ffffff"));
        back.setForeground(Color.decode("#1b1b1b"));
        back.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 20));
        back.setBorder(new RoundedBorder(4, Color.decode("#626262"), 1));
        back.setFocusPainted(false);
        OnClickEventHelper.setOnClickColor(back, Color.decode("#c2c2c2"), Color.decode("#ffffff"));
        panel.add(back);

        text.setBounds(316, 17, 498, 359);
        text.setFont(CustomFontLoader.loadFont("./resources/fonts/Lexend.ttf", 25));
        text.setForeground(Color.decode("#1b1b1b"));
        panel.add(text);

        frame.add(panel);
    }

    @Override
    protected void init() {
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(926, 499);
        frame.setLocationRelativeTo(null);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#eeeeee"));
    }

    @Override
    protected void handleInput() {
        //返回
        if(!isStarted) {
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent e) {
                    MusicControl.playSound("button", false);
                    MenuView menuView = new MenuView();
                    menuView.start();
                    frame.dispose();
                }
            });
    }
    }
}
