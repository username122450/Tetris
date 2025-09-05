package controller;

import java.awt.*;

// NEW: 活动方块状态（位置与旋转）
public final class BlockState {
	public final Block shape; // 使用现有 Block 作为形状载体
	/*
	java自带的坐标类
	point.x
	point.y可以获取
	 */
	public final Point position; // 左上角网格坐标
	public final int rotation; // 0..3

	public BlockState(Block shape, Point position, int rotation) {
		this.shape = shape;
		this.position = position;
		this.rotation = rotation;
	}

	//改变方块位置（只左右移动）
	public BlockState withPosition(int x, int y) {
		return new BlockState(this.shape, new Point(x, y), this.rotation); }

	//改变方块旋转状态（只改变旋转状态）（rotation & 3 相当于对rotation%4）
	public BlockState withRotation(int rotation) {
		return new BlockState(this.shape, this.position, rotation & 3); }
}


