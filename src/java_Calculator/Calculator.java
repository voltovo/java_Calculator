package java_Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
	private String[] delim = {"+", "-", "*", "/", "(", ")"};
	private Stack tokenStack = new Stack();
	private Stack exprStack = new Stack();
	private List postOrderList = new ArrayList();
	
	private void makeTokens(String s) {
		StringBuffer tokenBuf = new StringBuffer();
		int argSize = s.length();
		String token = null;
		for(int i = 0; i < argSize; i++) {
			token = s.substring(i, i+1);
			if(!isDelim(token)) {
				tokenBuf.append(token);
				
				if(i == argSize - 1) {
					tokenStack.push(tokenBuf.toString());
				}
				else {
					if(tokenBuf.length() > 0) {
						tokenStack.push(tokenBuf.toString());
						tokenBuf = new StringBuffer();
					}
					tokenStack.push(token);
				}
			}
		}
		
		//구분자인지 검사 @param s  @return
		private boolean isDelim(String s) {
			boolean bDelim = false;
			
			int delimSize = delim.length;
			for(int i = 0; i < delimSize; i++) {
				if(delim[i].equals(s)) {
					bDelim = true;
					break;
				}
			}
			return bDelim;
		}
	}
}
