package java_Calculator;

import java.util.StringTokenizer;

public class CalLogic {
	public double cal(String str) {
		StringTokenizer t = new StringTokenizer(str, "+-*/", true);
		//게산 기능 불러오기
		Calculator cu = new Calculator();
		return cu.calculate(str);
	}
}
