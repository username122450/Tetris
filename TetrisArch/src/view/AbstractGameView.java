package view;

// 抽象游戏视图基类：
public abstract class AbstractGameView {
    // 获取不同 View 的窗口
    protected javax.swing.JFrame jframe;
    // 当前视图状态，默认进入状态
    protected ViewStatus status = ViewStatus.ON_ENTER;

    // CHANGED: 进入：默认调用 init，然后切到 ON_UPDATE
    public void onEnter() {
        // NEW: 建议由子类调用 init 并切换状态
    }

    // 更新：默认先处理输入，再渲染
    public void update() {
        // NEW: 建议：先 handleInput，再 draw
    }

    // 退出：默认置为 NONE
    public void onExit() {
        this.status = ViewStatus.NONE;
    }

    // 获取当前状态
    public ViewStatus getStatus() {
        return this.status;
    }

    // 子类必须实现的方法：
    protected abstract void init();
    protected abstract void draw();
    protected abstract void handleInput();

}
