package controller;

import java.awt.*;
import java.util.Random;

// 对局的数据与更新
public final class GameSession {
	//地图
	private final Board board;
	//活动方块（当前）
	private BlockState active;
	//活动方块（下一个）（感觉不要也可以）
	private Block next;
	//得分
	private int score;
	private boolean isGameOver = false;
	//方块下落的间隔时间
	private long fallIntervalMs = 500;

	public GameSession(Board board) {
		this.board = board;
	}

	//生成首个 active方块 与 next下一个方块
	public void startNewGame() {
		isGameOver = false;
		score = 0;
		Block block = BlockManage.nextRadomBlock(BlockManage.radomType());
		int startX = (board.getWidth() - 4) / 2;
		active = new BlockState(block, new Point(startX, 0), 0);
		next = BlockManage.nextRadomBlock(BlockManage.radomType());
	}
	//更新方块并且执行语句
	/*
	定时下落
	无法下落：
		把方块锁定到棋盘中
	消除行并且得到消除的数据
	计算分数
	生成下一个活动方块：
		如果生成就碰撞：游戏结束
		游戏继续
	 */
	public void updateTick() {
		if (isGameOver) return;

		if (Rules.canMove(board, active, 0, 1)) {
			active = active.withPosition(active.position.x, active.position.y + 1);
		} else {
			board.lock(active);
			int clearedLines = board.clearFullLines();
			score += Scoring.scoreForClears(clearedLines);

			active = new BlockState(next, new Point((board.getWidth() - 4)/2, 0), 0);
			next = BlockManage.nextRadomBlock(BlockManage.radomType());

			if (!Rules.isValid(board, active)) {
				isGameOver = true;
			}
		}
	}

	public boolean isGameOver() { return false; }

	// 向左移动
	/*
	调用函数判断是否可以向左移动（根据变量private BlockState active;）
	1.移动，调用函数修改坐标
	 */
	public void tryMoveLeft() {
		if (!isGameOver && Rules.canMove(board, active, -1, 0)) {
		active = active.withPosition(active.position.x - 1, active.position.y);
	}
	}

	// 向右移动
	/*
	调用函数判断是否可以向左移动（根据变量private BlockState active;）
	1.移动，修改坐标
	 */
	public void tryMoveRight() {
		if (!isGameOver && Rules.canMove(board, active, 1, 0)) {
			active = active.withPosition(active.position.x + 1, active.position.y);
		}
	}

	//向下坠落
	/*
	每每隔一段时间方块就向下移动一格
	 */
	public void tryDrop() {
		if (!isGameOver && Rules.canMove(board, active, 0, 1)) {
			active = active.withPosition(active.position.x, active.position.y + 1);
		}
	}

	//旋转方块
	/*
	先canRotate判断是否可以旋转
	1.可以旋转就改变旋转度数
	2.不行就不变
	 */
	public void tryRotateCW() {
		if (!isGameOver) {
			int nextRotation = active.rotation + 1;
			if (Rules.canRotate(board, active, nextRotation)) {
				active = active.withRotation(nextRotation);
			}
		}
	}

	// 通过函数获取私有数据
	public Board getBoard() { return board; }
	public BlockState getActive() { return active; }
	public Block getNext() { return next; }
	public int getScore() { return score; }
}


