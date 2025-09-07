package controller;

// NEW: 计分规则
public final class Scoring {
	private Scoring() {}

	//计算分数，几行得几分
	public static int scoreForClears(int lines) {

		return switch (lines) {
            case 1 -> 1;
            case 2 -> 3;
            case 3 -> 5;
            case 4 -> 8;
            default -> 0;
        };
	}
}


