package controller;

// NEW: 计分规则
public final class Scoring {
	private Scoring() {}

	//计算分数，几行得几分
	public static int scoreForClears(int lines) {

		return switch (lines) {
            case 1 -> 100;
            case 2 -> 300;
            case 3 -> 500;
            case 4 -> 800;
            default -> 0;
        };
	}
}


