package view.page;

import controller.*;
import globle.Global;
import view.AbstractGameView;
import view.ViewStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class GameView extends AbstractGameView {
    private GameSession gameSession;
    final int BLOCK_SIZE = 20;
    private JPanel centerPanel;
    private Label soreLabel;
    //绘制地图
    private void map(){
        centerPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();//创建画笔

                int mapPanelWidth = gameSession.getBoard().getWidth() * BLOCK_SIZE;     //计算游戏窗口的宽度
                int mapPanelHeight = gameSession.getBoard().getHeight() * BLOCK_SIZE;   //计算游戏窗口的高度
                int offsetX = (getWidth() - mapPanelWidth) / 2;     //计算偏移量
                int offsetY = (getHeight() - mapPanelHeight) / 2;   //计算偏移量

                int[][] originalCells = gameSession.getBoard().getCells();//获取当前地图（临时地图）
                int[][] cells = new int[originalCells.length][originalCells[0].length];
                for (int i = 0; i < originalCells.length; i++) {
                    cells[i] = Arrays.copyOf(originalCells[i], originalCells[i].length);
                }

                BlockState active = gameSession.getActive(); //获取初始活动方块

                for (int i = 0; i < 4; i++) {   //把当前方块谢到数组中
                    for (int j = 0; j < 4; j++) {
                        if (active.shape.shade[active.rotation][i][j] == 2){    //修改一下block类为public属性或增加get（）方法
                            cells[active.position.x + i][active.position.y + j] = 2;
                        }
                    }
                }

                for (int row = 0; row < gameSession.getBoard().getHeight(); row++){ //绘制整个棋盘
                    for (int col = 0; col < gameSession.getBoard().getWidth(); col++){
                        if (cells[row][col] == 2){
                            int px = offsetX + col * BLOCK_SIZE;    //根据偏移量计算位置
                            int py = offsetY + row * BLOCK_SIZE;

                            g2d.setColor(Color.yellow);
                            g2d.fillRect(px, py, BLOCK_SIZE, BLOCK_SIZE);

                            g2d.setColor(new Color(205, 170, 0));
                            g2d.fillRect(px + 2, py + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4);
                        }
                    }
                }
                g2d.dispose();
            }
        };
        centerPanel.setPreferredSize(new Dimension(400, 600));  //设置界面大小
        centerPanel.setBackground(Color.BLACK); //放到整个窗口中

    };

    //绘制正在移动的方块
    private void drawMoveBlocks(){};//合并到地图绘制中

    //绘制分数
    private void drawSore(){
        soreLabel.setText("Sore:" + (gameSession.getScore()));
    };

    //用于最后绘制窗口和游戏内容
    @Override
    protected void draw() {
        map();
        drawSore();
    }


    //初始化游戏数据
    @Override
    protected void init() {
        //初始化地图
        Board board = new Board(20,30);
        for (int row = 0; row < board.getHeight(); row++){
            for (int col = 0; col < board.getWidth(); col++){
                board.getCells()[row][col] = 1;
            }
        }
        this.gameSession = new GameSession(board);
        gameSession.startNewGame();

        this.jframe = new JFrame();
        this.jframe.setTitle("Tetris"); //标题
        this.jframe.setSize(800,1200); //窗口大小
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点叉号直接关闭
        this.jframe.setVisible(true);//是否可见
        this.jframe.setAlwaysOnTop(true);//一直在上层

        JPanel panel = new JPanel(new BorderLayout(5,5));//参数：组件之间的边距
        JPanel topPanel = new JPanel();
        JPanel LeftPanel = new JPanel(new GridLayout(4,1));
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        JPanel bottomPanel = new JPanel();

        //顶部的标签设置
        topPanel.setPreferredSize(new Dimension(0,160));
        topPanel.setBackground(Color.LIGHT_GRAY);
        Label north = new Label("Tetris");
        north.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        topPanel.add(north);

        //底部的标签设置
        bottomPanel.setPreferredSize(new Dimension(0,160));
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        Label south = new Label("Other functions");
        south.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        bottomPanel.add(south);

        //左边的标签设置
        LeftPanel.setPreferredSize(new Dimension(180,0));
        LeftPanel.setBackground(Color.LIGHT_GRAY);
        Label west = new Label("HAPPY—GAME",Label.CENTER);
        west.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        LeftPanel.add(west);


        //右边边的标签设置
        rightPanel.setPreferredSize(new Dimension(180,0));
        rightPanel.setBackground(Color.LIGHT_GRAY);
        Label east = new Label("Ranking",Label.CENTER);
        soreLabel = new Label("Sore:" + gameSession.getScore(),Label.CENTER);
        east.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        soreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        rightPanel.add(east);
        rightPanel.add(soreLabel);


        //添加到主面板
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(rightPanel, BorderLayout.EAST);
        panel.add(LeftPanel, BorderLayout.WEST);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        map();
        panel.add(centerPanel, BorderLayout.CENTER);
        this.jframe.add(panel);
        handleInput();

        this.jframe.revalidate();
        this.jframe.repaint();
    }

    //用户输入
    /*
    w：旋转
    s：向下加速
    a：左移一格
    d：右移一格
     */
    @Override
    protected void handleInput() {
        jframe.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameSession == null) return;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        gameSession.tryRotateCW(); // 旋转方块
                        break;
                    case KeyEvent.VK_A:
                        gameSession.tryMoveLeft(); // 左移
                        break;
                    case KeyEvent.VK_D:
                        gameSession.tryMoveRight(); // 右移
                        break;
                }

                // 更新界面
                draw();
                jframe.repaint();
            }
        });

        // 确保窗口能接收键盘事件
        jframe.setFocusable(true);
        jframe.requestFocusInWindow();
    }

    //重写方法：
    /*
    当进入游戏页面时调用初始化：
    inite
     */
    @Override
    public void onEnter() {
        init();
    }

    //游戏结束后：
    /*
    1.返回开始菜单
    2.重新开始
     */
    @Override
    public void onExit() {
        GameCore.changeView(new GameOverView());
        GameCore.getCurrentView().onEnter();
    }

    //更新游戏数据
    /*
    收集玩家的操作
    更新地图
    移动方块位置
    玩家分数
    绘制地图
     */
    @Override
    public void update() {
        status = ViewStatus.ON_EXIT;
        if (gameSession == null) return;

        if (!gameSession.tryDrop()){ //可能需要修改对应方法的返回值
            gameSession.getBoard().lock(gameSession.getActive());
            gameSession.startNewGame();
        }
        if (!gameSession.tryDrop()){
            onExit();
        }
        draw();
        jframe.repaint();
    }
}
