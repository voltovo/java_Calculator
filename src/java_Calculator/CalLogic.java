package java_Calculator;

import java.util.StringTokenizer;

public class CalLogic {
	public double cal(String str) {
		StringTokenizer t = new StringTokenizer(str, "+-*/", true);
		while(t.hasMoreTokens()) {
			System.out.println(t.nextToken());
		}
		return Double.parseDouble(t.toString());
	}
}
