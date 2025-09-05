package controller;

import java.awt.*;

// NEW: 规则判定（碰撞/边界/旋转），独立于渲染与输入
public final class Rules {
	private Rules() {
	}

	//判定在棋盘内且没有碰撞（碰撞判定）
	public static boolean isValid(Board board, BlockState state) {
		int[][] cells = board.getCells();
		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();
		Point pos = state.position;
		int rotation = state.rotation & 3;
		int[][] shapeMatrix = state.shape.shade[rotation];
		for (int row = 0; row < 4; row++) {
			for (int col = 0; col < 4; col++) {
				if (shapeMatrix[row][col] == 2) {
					int x = pos.x + col;
					int y = pos.y + row;
					if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight) {
						return false;
					}
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
		Point newPos = new Point(state.position.x + dx, state.position.y + dy);
		BlockState newState = new BlockState(state.shape, newPos, state.rotation);
		return isValid(board, newState);
	}
	//尝试旋转，是否可以旋转
	public static boolean canRotate(Board board, BlockState state, int nextRotation) {
		int normalizedRotation = nextRotation & 3;
		BlockState newState = new BlockState(state.shape, state.position, normalizedRotation);
		return isValid(board, newState);
	}
}


