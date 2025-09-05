package view;


// 视图状态枚举：用于状态机管理
public enum ViewStatus {
    NONE,       // 无状态
    ON_ENTER,   // 进入状态（刚创建或切换到）
    ON_UPDATE,  // 更新状态（运行中）
    ON_EXIT     // 退出状态（将被销毁或切换）
}

