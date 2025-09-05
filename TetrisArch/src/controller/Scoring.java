package controller;

// NEW: 计分规则
public final class Scoring {
	private Scoring() {}

	//计算分数，几行得几分
	public static int scoreForClears(int lines) {

		if (lines <= 0) {
			return 0;
		}
		return lines;
	}
}


