package controller;

// NEW: 棋盘模型
public final class Board {
	//数组地图的宽高与二维网格（0=空地，1表示方块）
	private final int width;
	private final int height;
	private final int[][] cells;

	//创建一个地图
	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.cells = new int[height][width];
	}

	//获取地图属性
	/*
	获取宽度
	获取长度
	获取整个二维数组
	 */
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int[][] getCells() { return cells; }

	//把活动方块放到棋盘（写入棋盘中不在移动）
	public void lock(BlockState state) {
	}

	/*
	消除整行
	将上方的方块整体拷贝到下方
	返回清除的行数
	 */
	public int clearFullLines() {
		return 0;
	}
}


