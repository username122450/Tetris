package controller;

// NEW: 计分规则
public final class Scoring {
	private Scoring() {}

	//计算分数，几行得几分
	public static int scoreForClears(int lines) {

		if (lines <= 0) {
			return 0;
		}
		switch(lines){
			case 1: return 1;
			case 2: return 3;
			case 3: return 5;
			case 4: return 8;
			default: return lines*2+1;
		}
	}
}


