package java_Calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
	private String[] delim = { "+", "-", "*", "/", "(", ")" };
	private Stack tokenStack = new Stack();
	private Stack exprStack = new Stack();
	private List postOrderList = new ArrayList();
	
	//여기서 부터 수정해야 할듯
	private void makeTokens(String s) {
		StringBuffer tokenBuf = new StringBuffer();
		int argSize = s.length();
		String token = null;
		//소수점 들어왓을 때 구분하기 위해서
		int j = 0;
		for (int i = 0; i < argSize; i++) {
			token = s.substring(i, i + 1);
			//소수점이 들어온 경우
			if(token.equals(".")) {
				j = i;
			}
			if(i == j +1) {
				String tempA = token;
				String tempB = tokenStack.pop().toString();
				String tempC = tokenStack.pop().toString();
				String RealNum = tempC + tempB + tempA;
				tokenStack.push(RealNum);
				j = 0;
			}else {
			//연산자가 아니면 tokenBuf 추가
			if (!isDelim(token)) {
				tokenBuf.append(token);
			
				if (i == argSize - 1) {
					tokenStack.push(tokenBuf.toString());
				} else {
					if (tokenBuf.length() > 0) {
						tokenStack.push(tokenBuf.toString());
						tokenBuf = new StringBuffer();
					}
				}
			}
			//연산자면 바로 tokenStack에 추가
			else {
				tokenStack.push(token);
			}
			
		  }
		}

	}

	/*
	 * 구분자인지 검사
	 * 
	 * @param s
	 * 
	 * @return
	 */
	private boolean isDelim(String s) {
		boolean bDelim = false;

		int delimSize = delim.length;
		for (int i = 0; i < delimSize; i++) {
			if (delim[i].equals(s)) {
				bDelim = true;
				break;
			}
		}
		return bDelim;
	}

	/*
	 * 연산자인지 검사
	 * 
	 * @param s
	 * 
	 * @return
	 */
//	private boolean isOpcode(String s) {
//		boolean opcode = isDelim(s);
//		
//		//isDelim에서 ( ) 검사 해 놓고 왜 한번 더 하지 ? 
//		if ("(".equals(s) || ")".equals(s)) {
//			opcode = false;
//		}
//		return opcode;
//	}
	
	/*
	 * PostOrder로 변환
	 * 
	 * @param expr
	 * 
	 * @return
	 */
	private void convertPostOrder(String expr) {
		for(Object token : tokenStack) {
			if(isDelim(token.toString())) {
				exprAppend(token.toString());
			}
			else{
				postOrderList.add(token);
			}
		}
		//exprStack 에 자료가 남아 있으면 출력한다
		String item = null;
		while(!exprStack.isEmpty()) {
			item = (String) exprStack.pop();
			postOrderList.add(item);
		}
	}
	
	/*
	 * 연산자를 비교해서 postorder방식에 표시 할지 스택에 넣을지 확인
	 * 
	 * @param postOrderBuf
	 */
	private void exprAppend(String token) {
		//'(' 일 경우 스택에 넣는다
		if("(".equals(token)) {
			exprStack.push(token);
			return;
		}
		//')'일 경우 '('가 나올 때까지 출력한다
		if(")".equals(token)) {
			String opcode = null;
			while(true) {
				opcode = (String) exprStack.pop();
				if("(".equals(opcode)) {
					break;
				}
				postOrderList.add(opcode);
			}
			return;
		}
		
		//연자일 경우 스택에서 pop하여 낮은 우선순위를 만날때 까지 출력하고 자신을 push
		if(isDelim(token)) {
			String opcode = null;
			while(true) {
				if(exprStack.isEmpty()) {
					exprStack.push(token);
					break;
				}
				opcode = (String) exprStack.pop();
				if(exprOrder(opcode) > exprOrder(token)) {
					postOrderList.add(opcode);
				}
				else {
					exprStack.push(opcode);
					exprStack.push(token);
					break;
				}
			}
			return;
		}
	}
	
	/*
	 * 값을 계산한다
	 * 
	 * @return
	 */
	public double calculate(String expr) {
		//토큰을 만든다
		makeTokens(expr);
		
		//postOrder로 변환
		convertPostOrder(expr);
		
		Stack calcStack = new Stack();
		
		//postOrder 리스트의 값을 calcStack에 저장
		for(Object s : postOrderList) {
			calcStack.push(s);
			calcPostOrder(calcStack);
		}
		
		//스택의 최종 값을 반환한다
		double result = Double.parseDouble((String) calcStack.pop());
		return result;
	}
	
	/*
	 * 스택에서 pop연산을 하여 계산 가능하면 계산 후 값을 스택에 넣는다
	 * 
	 * @param calcStack
	 */
	private void calcPostOrder(Stack calcStack) {
		//연산자가 아니면 게산을 하지 않는다
		if(!isDelim((String)calcStack.lastElement())) {
			return;
		}
		
		//3개를 꺼내야 하므로 3개 이상인지 검사
		String op1 = null;
		String op2 = null;
		String opcode = null;
		if(calcStack.size() >= 3) {
			opcode = (String) calcStack.pop();
			op1 = (String) calcStack.pop();
			op2 = (String) calcStack.pop();
			
			//값을 계산하여 스택에 넣는다
			double result = calculateByOpCode(op1, op2, opcode);
			calcStack.push(Double.toString(result));
		}
	}
	
	private double calculateByOpCode(String op1, String op2, String opcode) {
		double d1 = Double.parseDouble(op1);
		double d2 = Double.parseDouble(op2);
		
		double result = 0.0;
		if("+".equals(opcode)) {
			result = d2 + d1;
		}else if("-".equals(opcode)) {
			result = d2 - d1;
		}else if("*".equals(opcode)) {
			result = d2 * d1;
		}else if("/".equals(opcode)) {
			result = d2 / d1;
		}
		return result;
	}
	
	/*
	 * 연산자 순위를 리턴한다
	 * 
	 * @param s1
	 * 
	 * @param s2
	 * 
	 * @return
	 */
	public int exprOrder(String s) {
		int order = -1;
		
		if("-".equals(s) || "+".equals(s)) {
			order = 0;
		}else if("*".equals(s) || "/".equals(s)){
			order = 1;
		}
		
		return order;
	}
	
	public static void main(String[] args) {
		Calculator cal = new Calculator();
		System.out.println(cal.calculate("3+2"));
	}
}
