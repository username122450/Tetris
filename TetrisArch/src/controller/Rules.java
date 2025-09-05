package controller;

// NEW: 规则判定（碰撞/边界/旋转），独立于渲染与输入
public final class Rules {
	private Rules() {}

	//判定在棋盘内且没有碰撞（碰撞判定）
	public static boolean isValid(Board board, BlockState state) {
		return true;
	}

	// 尝试移动，是否可以向右移动
	public static boolean canMove(Board board, BlockState state, int dx, int dy) {
		return true;
	}

	//尝试旋转，是否可以旋转
	public static boolean canRotate(Board board, BlockState state, int nextRotation) {
		return true;
	}
}


