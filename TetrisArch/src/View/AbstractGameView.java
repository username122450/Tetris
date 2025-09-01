package View;

// 抽象游戏视图基类：
public abstract class AbstractGameView {

    // 当前视图状态，默认进入状态
    protected ViewStatus status = ViewStatus.ON_ENTER;

    // 进入：默认调用 init，然后切到 ON_UPDATE
    public void onEnter() {
        init();
        this.status = ViewStatus.ON_UPDATE;
    }

    // 更新：默认先处理输入，再渲染
    public void update() {
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
