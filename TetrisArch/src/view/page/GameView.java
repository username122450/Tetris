package view.page;

import controller.*;
import controller.blocks.*;
import globle.Global;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import sound.MusicControl;
import view.AbstractGameView;

public class GameView extends AbstractGameView {
    public static boolean gameRunning = false; // 防止重复创建游戏窗口
    private GameSession gameSession;
    final int BLOCK_SIZE = 20;
    private int score;
    private JLabel scoreLabel;
    private JPanel centerPanel ;
    private JPanel corePanel;
    private JPanel nextBlockPanel;

    //计时器
    private java.util.Timer autoDropTimer; // 计时器
    private  long DROP_INTERVAL = 1000; // 下落时间初始
    private final int INTERVAL_DECREMENT_PER_100_SCORE = 50; // 每个等级减少的间隔时间
    private final int MIN_DROP_INTERVAL = 100; // 最小下落间隔（最快速度）
    private long lastScheduledInterval = -1; // 當前定時器使用的間隔

    // 公共方法：启动游戏,启动游戏调用次方法
    public void startGame() {
        if (gameRunning) {
            System.out.println("游戏已经在运行中，无法重复启动");
            return;
        }
        gameRunning = true;
        init();
    }
    
    // 重写start方法
    @Override
    public void start() {
        if (gameRunning) {
            System.out.println("游戏已经在运行中，无法重复启动");
            return;
        }
        gameRunning = true;
        super.start();
    }


    //绘制地图
    private void setCenterPanel() {
        centerPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();//创建画笔

                int mapPanelWidth = gameSession.getBoard().getWidth() * BLOCK_SIZE;     //计算游戏窗口的宽度
                int mapPanelHeight = gameSession.getBoard().getHeight() * BLOCK_SIZE;   //计算游戏窗口的高度
                int offsetX = (getWidth() - mapPanelWidth) / 2;     //计算偏移量
                int offsetY = (getHeight() - mapPanelHeight) / 2;   //计算偏移量

                //绘制游戏背景
                g2d.setColor(Color.BLACK);
                g2d.fillRect(offsetX, offsetY, mapPanelWidth, mapPanelHeight);

                int[][] originalCells = gameSession.getBoard().getCells();//获取当前地图（临时地图）用来绘制不修改
                int[][] cells = new int[originalCells.length][originalCells[0].length];//复制地图
                for (int i = 0; i < originalCells.length; i++) {
                    cells[i] = Arrays.copyOf(originalCells[i], originalCells[i].length);
                }

                BlockState active = gameSession.getActive(); //获取初始活动方块

                // 检查活动方块是否存在
                if (active != null) {
                    for (int i = 0; i < 4; i++) {   //把当前方块写到数组中
                        for (int j = 0; j < 4; j++) {
                            if (active.shape.shade[active.rotation][i][j] == 2){    //修改一下block类为public属性或增加get（）方法
                                int newX = active.position.x + i;
                                int newY = active.position.y + j;
                                // 检查边界，防止数组越界
                                if (newX >= 0 && newX < gameSession.getBoard().getHeight() &&
                                        newY >= 0 && newY < gameSession.getBoard().getWidth()) {
                                    cells[newX][newY] = 3; // 使用3表示当前活动方块，避免与锁定的方块(2)冲突
                                }
                            }
                        }
                    }
                }

                for (int row = 0; row < gameSession.getBoard().getHeight(); row++){ //绘制整个棋盘
                    for (int col = 0; col < gameSession.getBoard().getWidth(); col++){
                        int px = offsetX + col * BLOCK_SIZE;    //根据偏移量计算位置
                        int py = offsetY + row * BLOCK_SIZE;

                        if (cells[row][col] == 2){
                            // 绘制已锁定的方块（灰色）
                            g2d.setColor(Color.GRAY);
                            g2d.fillRect(px, py, BLOCK_SIZE, BLOCK_SIZE);
                            
                            g2d.setColor(Color.DARK_GRAY);
                            g2d.fillRect(px + 2, py + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4);
                        } else if (cells[row][col] == 3){
                            // 绘制当前活动方块（黄色）
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
        //占用窗口的大小
        int mapPanelWidth = gameSession.getBoard().getWidth();
        int mapPanelHeight = gameSession.getBoard().getHeight();
        centerPanel.setPreferredSize(new Dimension(mapPanelWidth, mapPanelHeight));

        centerPanel.setBackground(Color.LIGHT_GRAY);//设置中心面板背景
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    };


    /// 绘制下一个方块
    private void setNextBlockPanel() {
        nextBlockPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();//创建画笔
                Block nextBlock = gameSession.getNext();
                if (nextBlock != null) {
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    int blockWidth = 4 * BLOCK_SIZE;
                    int blockHeight = 4 * BLOCK_SIZE;

                    int startX = (panelWidth - blockWidth) / 2;
                    int startY = (panelHeight - blockHeight) / 2;

                    // 绘制标题
                    g2d.setColor(Color.BLACK);
                    g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
                    g2d.drawString("下一个方块", 10, 20);

                    for (int row = 0; row < 4; row++) {
                        for (int col = 0; col < 4; col++) {
                            if (nextBlock.shade[0][row][col] == 2) {
                                int px = startX + col * BLOCK_SIZE;
                                int py = startY + row * BLOCK_SIZE + 30; // 留出标题空间

                                g2d.setColor(Color.yellow);
                                g2d.fillRect(px, py, BLOCK_SIZE, BLOCK_SIZE);

                                g2d.setColor(new Color(205, 170, 0));
                                g2d.fillRect(px + 2, py + 2, BLOCK_SIZE - 4, BLOCK_SIZE - 4);
                            }
                        }
                    }
                g2d.dispose();
                }
            }
        };
        nextBlockPanel.setPreferredSize(new Dimension(150, 120)); // 增大面板尺寸
        nextBlockPanel.setBackground(Color.LIGHT_GRAY);
    }


    @Override
    protected void draw() {
        // 重绘中心面板
        if (centerPanel != null) {
            centerPanel.repaint();
        }
        if (nextBlockPanel != null) {
            nextBlockPanel.repaint();
        }
    }

    @Override
    protected void init() {
        // 显示界面
        this.setVisible(true);
        this.setResizable(false);
        
        // 初始化游戏
        gameSession.startNewGame();
        
        // 设置键盘输入处理
        handleInput();
        
        // 启动定时器，定期更新界面
        startGameTimer();
        
        // 更新分数显示
        updateScoreDisplay();
        
        // 重绘界面
        centerPanel.repaint();
    }

    @Override
    protected void handleInput() {
        // 处理键盘输入 - 只在第一次调用时添加监听器
        if (this.getKeyListeners().length == 0) {
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (gameSession.isGameOver()) {
                        return; // 游戏结束时忽略输入
                    }
                    
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_A:
                        case KeyEvent.VK_LEFT:
                            gameSession.tryMoveLeft();
                            break;
                        case KeyEvent.VK_D:
                        case KeyEvent.VK_RIGHT:
                            gameSession.tryMoveRight();
                            break;
                        case KeyEvent.VK_S:
                        case KeyEvent.VK_DOWN:
                            gameSession.tryDrop();
                            break;
                        case KeyEvent.VK_W:
                        case KeyEvent.VK_UP:
                            gameSession.tryRotateCW();
                            break;
                        case KeyEvent.VK_P:
                        case KeyEvent.VK_ESCAPE:
                            MusicControl.pauseSound("background");
                            pauseGame();
                            break;
                        case KeyEvent.VK_SPACE:
                            // 快速下落
                            while (gameSession.tryDrop()) {
                                // 继续下落直到不能下落
                            }
                            break;
                    }
                    
                    // 更新分数显示
                    updateScoreDisplay();
                    
                    // 重绘界面
                    draw();
                }
            });
        }
        
        // 设置焦点以便接收键盘事件
        this.setFocusable(true);
        this.requestFocus();
    }
    
    // 更新分数显示
    private void updateScoreDisplay() {
        if (scoreLabel != null) {
            score = gameSession.getScore();
            
            // 根据分数计算新的下落间隔
            int level = score / 1000;  // 每 1000 分升一级
            DROP_INTERVAL = Math.max(MIN_DROP_INTERVAL, DROP_INTERVAL - (level * INTERVAL_DECREMENT_PER_100_SCORE));
            
            scoreLabel.setText("分数: " + score);

            if (nextBlockPanel != null) {
                nextBlockPanel.repaint();
            }

            // 若下落速度變更，重啟定時器以應用新間隔
            if (autoDropTimer != null && lastScheduledInterval != DROP_INTERVAL) {
                restartGameTimer();
            }
        }
    }
    
    // 游戏结束处理
    public void onGameOver() {
        // 停止定时器
        stopGameTimer();
        
        // 重置游戏运行标志
        gameRunning = false;
        
        // 隐藏并关闭当前窗口
        this.setVisible(false);
        this.dispose();
        gameRunning = false;
        // 显示游戏结束界面
        MusicControl.pauseSound("background");
        MusicControl.playSound("GameOver", false);
        GameOverView gameOverView = new GameOverView();
        gameOverView.start();
        
        System.out.println("游戏结束！最终分数: " + score);
    }

    
    //获取当前分数
    public int getCurrentScore() {
        return gameSession.getScore();
    }

    //构造函数
    public GameView(){
        /// 属性设置
        this.gameSession = new GameSession(new Board(20,32));//棋盘
        this.score = 0;//分数
        corePanel = new JPanel(new BorderLayout(5,5));
        this.DROP_INTERVAL = 500;

        /// 面板设置
        this.setTitle("俄罗斯方块游戏");
        this.setAlwaysOnTop(true);
        this.setVisible(false); // 初始隐藏界面
        this.setSize(Global.wide, Global.height);
        this.setLocationRelativeTo(null);//居中
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //中心面板设置
        setCenterPanel();
        corePanel.add(centerPanel, BorderLayout.CENTER);

        //上方的面板
        JPanel TopPanel = new JPanel();
        TopPanel.setBackground(Color.LIGHT_GRAY);
        TopPanel.setPreferredSize(new Dimension(0,145));//占用窗口的大小
        Label top = new Label("Tetris");
        TopPanel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        TopPanel.add(top);
        corePanel.add(TopPanel, BorderLayout.NORTH);

        //底部
        JPanel BottomPanel = new JPanel();
        BottomPanel.setPreferredSize(new Dimension(0,145));//占用窗口的大小
        BottomPanel.setBackground(Color.LIGHT_GRAY);//设置背景
        Label bottom = new Label("Other functions");
        bottom.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        BottomPanel.add(bottom);
        corePanel.add(BottomPanel, BorderLayout.SOUTH);

        //左边
        //左边
        JPanel LeftPanel = new JPanel(new BorderLayout());
        LeftPanel.setPreferredSize(new Dimension(190, 0));
        LeftPanel.setBackground(Color.LIGHT_GRAY);

// 创建标题
        Label left = new Label("HAPPY—GAME", Label.CENTER);
        left.setFont(new Font(Font.DIALOG, Font.BOLD, 20));

// 创建方块展示面板（4行2列）
        JPanel blocksPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        blocksPanel.setBackground(Color.LIGHT_GRAY);

// 创建所有方块面板并添加到网格中
        JPanel iBlockPanel = createBlockPanel(new I_Block(), "I");
        JPanel llBlockPanel = createBlockPanel(new LL_Block(), "L");
        JPanel lrBlockPanel = createBlockPanel(new LR_Block(), "J");
        JPanel oBlockPanel = createBlockPanel(new O_Block(), "O");
        JPanel tBlockPanel = createBlockPanel(new T_Block(), "T");
        JPanel zlBlockPanel = createBlockPanel(new ZL_Block(), "S");
        JPanel zrBlockPanel = createBlockPanel(new ZR_Block(), "Z");

// 添加到网格布局（4行2列）
        blocksPanel.add(iBlockPanel);
        blocksPanel.add(llBlockPanel);
        blocksPanel.add(lrBlockPanel);
        blocksPanel.add(oBlockPanel);
        blocksPanel.add(tBlockPanel);
        blocksPanel.add(zlBlockPanel);
        blocksPanel.add(zrBlockPanel);

// 添加一个空面板凑齐4x2
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.LIGHT_GRAY);
        blocksPanel.add(emptyPanel);

        LeftPanel.add(left, BorderLayout.NORTH);
        LeftPanel.add(blocksPanel, BorderLayout.CENTER);

        corePanel.add(LeftPanel, BorderLayout.WEST);


        //右边
        JPanel RightPanel = new JPanel();
        RightPanel.setLayout(new BoxLayout(RightPanel, BoxLayout.Y_AXIS));
        RightPanel.setPreferredSize(new Dimension(190,0));  //占用窗口的大小
        RightPanel.setBackground(Color.LIGHT_GRAY);//设置背景

        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        scoreLabel = new JLabel("Score: " + score); //标签
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 20));//设置字体
        scorePanel.add(scoreLabel);
        scorePanel.setBackground(Color.LIGHT_GRAY);

        Label right = new Label("Ranking",Label.CENTER);//设置右标签
        right.setFont(new Font(Font.DIALOG, Font.BOLD, 20));//设置字体

        RightPanel.add(right);
        RightPanel.add(Box.createRigidArea(new Dimension(0,10)));
        RightPanel.add(scorePanel);
        RightPanel.add(Box.createRigidArea(new Dimension(0,10)));

        //右侧绘制下一个方块面板
        setNextBlockPanel();
        RightPanel.add(nextBlockPanel);

        corePanel.add(RightPanel, BorderLayout.EAST);

        //添加到主面板
        this.add(corePanel);
    }

    // 启动游戏定时器
    private void startGameTimer() {
        autoDropTimer = new Timer();
        autoDropTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    // 检查游戏是否结束
                    if (gameSession.isGameOver()) {
                        onGameOver();
                        return;
                    }
                    
                    // 更新游戏状态（方块下落）
                    gameSession.updateTick();
                    
                    // 更新分数显示
                    updateScoreDisplay();
                    
                    // 重绘界面
                    draw();
                });
            }
        }, 0, DROP_INTERVAL);
        lastScheduledInterval = DROP_INTERVAL;
    }
    
    // 停止游戏定时器
    private void stopGameTimer() {
        if (autoDropTimer != null) {
            autoDropTimer.cancel();
            autoDropTimer.purge();
            autoDropTimer = null;
        }
    }

    // 重置定时器
    private void restartGameTimer() {
        stopGameTimer();
        startGameTimer();
    }

    // 暂停游戏
    private void pauseGame() {
        stopGameTimer();
        this.setAlwaysOnTop(false);
        PauseView pauseView = new PauseView(this);
        pauseView.start();
    }

    // 恢复
    public void resumeGame() {
        restartGameTimer();
        this.setAlwaysOnTop(true);
        this.requestFocus();
    }

    private JPanel createBlockPanel(Block block, String blockName) {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                // 绘制背景
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // 绘制方块名称
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
                g2d.drawString(blockName, 5, 15);

                // 绘制方块
                int BLOCK_MINI_SIZE = 15; // 小方块尺寸
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                // 计算起始位置（居中）
                int startX = centerX - (2 * BLOCK_MINI_SIZE);
                int startY = centerY - (2 * BLOCK_MINI_SIZE) + 10; // 向下偏移为名称留空间

                for (int row = 0; row < 4; row++) {
                    for (int col = 0; col < 4; col++) {
                        if (block.shade[0][row][col] == 2) {
                            int px = startX + col * BLOCK_MINI_SIZE;
                            int py = startY + row * BLOCK_MINI_SIZE;

                            // 使用黄色绘制方块
                            g2d.setColor(Color.YELLOW);
                            g2d.fillRect(px, py, BLOCK_MINI_SIZE, BLOCK_MINI_SIZE);

                            g2d.setColor(new Color(205, 170, 0));
                            g2d.fillRect(px + 2, py + 2, BLOCK_MINI_SIZE - 4, BLOCK_MINI_SIZE - 4);
                        }
                    }
                }
                g2d.dispose();
            }
        };

        panel.setPreferredSize(new Dimension(80, 80));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        return panel;
    }
}
