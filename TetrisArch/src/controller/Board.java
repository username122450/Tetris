package controller;

import java.awt.*;

// NEW: 棋盘模型
public final class Board {
	//数组地图的宽高与二维网格（0=空地，2表示方块）
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
		Block block = state.shape;
		Point pos = state.position;
		int rotation = state.rotation;


		// 获取当前旋转状态下的方块形状
		int[][] shape = block.shade[rotation];

		for(int y = 0; y < shape.length; y++) {
			for(int x = 0; x < shape[y].length; x++) {
				if(shape[y][x] == 2) {
					// 坐标映射：与GameView中的绘制逻辑保持一致
					int boardX = pos.x + y;  // x坐标对应行，使用y作为行偏移
					int boardY = pos.y + x;  // y坐标对应列，使用x作为列偏移

					// 确保在棋盘范围内
					if(boardX >= 0 && boardX < height && boardY >= 0 && boardY < width) {
						cells[boardX][boardY] = 2;//锁定方块
					}
				}
			}
		}

	}

	/*
	检验这一行是否已满
	 */
	private boolean isLineFull(int line){
		for(int x = 0; x < width; x++){
			if(cells[line][x] != 2){
				return false;
			}
		}
		return true;
	}

	/*
	清除这一行
	并下移
	 */
	private void clearLine(int line){
		//清除这一行并将之前的下移一行
		for(int y = line; y > 0; y--){
			for(int x = 0; x < width; x++){
				cells[y][x] = cells[y-1][x];
			}
		}

		//清空顶行
		for(int x = 0; x < width; x++){
			cells[0][x] = 0;
		}
	}

	/*
	消除整行
	将上方的方块整体拷贝到下方
	返回清除的行数
	 */
	public int clearFullLines() {
		//清除的行数
		int LinesCleared = 0;

		//从下往上检查是否需要清除
		for(int y = height-1; y >= 0; y--){
			if(isLineFull(y)){
				clearLine(y);
				LinesCleared++;
				y++;
			}
		}

		return LinesCleared;
	}

	/*
	清空棋盘
	游戏重置时使用
	 */
	public void clearBoard(){
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				cells[y][x] = 0;
			}
		}
	}
}


