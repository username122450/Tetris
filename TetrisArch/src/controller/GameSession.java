package controller;

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

	//方块下落的间隔时间
	private long fallIntervalMs = 500;

	public GameSession(Board board) {
		this.board = board;
	}

	//生成首个 active方块 与 next下一个方块
	public void startNewGame() {

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

	}

	public boolean isGameOver() { return false; }

	// 向左移动
	/*
	调用函数判断是否可以向左移动（根据变量private BlockState active;）
	1.移动，修改坐标
	2.不更改坐标
	 */
	public void tryMoveLeft() {}

	// 向右移动
	/*
	调用函数判断是否可以向左移动（根据变量private BlockState active;）
	1.移动，修改坐标
	2.不更改坐标
	 */
	public void tryMoveRight() {}

	//向下坠落
	/*
	每每隔一段时间方块就向下移动一格
	 */
	public boolean tryDrop() {
		return false;
	}

	//旋转方块
	/*
	先canRotate判断是否可以旋转
	1.可以旋转就改变旋转度数
	2.不行就不变
	 */
	public void tryRotateCW() {}

	// 通过函数获取私有数据
	public Board getBoard() { return board; }
	public BlockState getActive() { return active; }
	public Block getNext() { return next; }
	public int getScore() { return score; }
}


