package controller;

import java.awt.*;

// NEW: 规则判定（碰撞/边界/旋转），独立于渲染与输入
public final class Rules {
	private Rules() {}

	//判定在棋盘内且没有碰撞（碰撞判定）
	public static boolean isValid(Board board, BlockState state) {

		int[][] cells = board.getCells();
		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();
		Point pos = state.position;
		int rotation = state.rotation & 3; // 确保旋转值在0-3范围

		// 获取当前旋转状态下方块的形状矩阵（4x4）
		int[][] shapeMatrix = state.shape.shade[rotation];

		// 遍历方块的每个格子
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				// 只处理方块的有效部分（值为2的格子，参考O_Block/I_Block的定义）
				if (shapeMatrix[row][col] == 2) {
					int x = pos.x + col; // 计算在棋盘上的x坐标
					int y = pos.y + row; // 计算在棋盘上的y坐标

					// 检查是否超出棋盘边界
					if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
						return false;
					}

					// 检查是否与已锁定的方块碰撞
					if (cells[y][x] != 0) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// 尝试移动，是否可以向右移动
	public static boolean canMove(Board board, BlockState state, int dx, int dy) {
		// 计算移动后的新位置
		Point newPos = new Point(state.position.x + dx, state.position.y + dy);
		// 创建移动后的新状态
		BlockState newState = new BlockState(state.shape, newPos, state.rotation);
		// 判定新状态是否有效
		return isValid(board, newState);
	}

	//尝试旋转，是否可以旋转
	public static boolean canRotate(Board board, BlockState state, int nextRotation) {
		// 计算规范化后的旋转值（0-3）
		int normalizedRotation = nextRotation & 3;
		// 创建旋转后的新状态
		BlockState newState = new BlockState(state.shape, state.position, normalizedRotation);
		// 判定新状态是否有效
		return isValid(board, newState);
	}
}


