package java_Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class CalGUI extends JFrame implements ActionListener {

	//계산 객체
	CalLogic c = new CalLogic();
	int check = -1;
	int soch = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//text 받아오는역할
		String mun;
		
		//getActionCommand() 버튼의 텍스트를 얻어옴 , getSource() 이벤트를 발생한 컴포넌트 얻기
		switch(e.getActionCommand()) {
			case "0":
				ch("0");
				break;
			case "1":
				ch("1");
				break;
			case "2":
				ch("2");
				break;
			case "3":
				ch("3");
				break;
			case "4":
				ch("4");
				break;
			case "5":
				ch("5");
				break;
			case "6":
				ch("6");
				break;
			case "7":
				ch("7");
				break;
			case "8":
				ch("8");
				break;
			case "9":
				ch("9");
				break;
				
			//숫자만 두번째 텍스트까지 유지
			case "←":
				mun = jfsum.getText();
				mun = mun.substring(0,mun.length()-1);
				jfsum.setText(mun);
				jf.setText("");
				break;
			
			case "x2":
				mun = jfsum.getText();
				//+-* 구분값
				StringTokenizer t = new StringTokenizer(mun, "+/*-", true);
				String na = null;
				double re = 0;
				
				while(t.hasMoreTokens()) {
					String x1 = t.nextToken();
					na = x1;
				}
				System.out.println(na);
				
			jfsum.setText(Double.parseDouble(na) * Double.parseDouble(na) + "");
			break;
			
			case "√":
				mun = jfsum.getText();
				//+-* 구분값
				StringTokenizer t1 = new StringTokenizer(mun, "+/*-", true);
				String na1 = null;
				double re1 = 0;
				
				while(t1.hasMoreTokens()) {
					String x1 = t1.nextToken();
					na1 = x1;
				}
				System.out.println(na1);
				jfsum.setText(Math.sqrt(Double.parseDouble(na1)) + "");
				break;
				
			case "mc":
				jf.setText("");
				jfsum.setText("");
				//소수초기화
				soch = 0;
				break;
				
			case "+":
				jf.setText("");
				jfsum.setText(jfsum.getText()+"+");
				break;
			
			case "-":
				jf.setText("");
				jfsum.setText(jfsum.getText()+"-");
				break;
			
			case "*":
				jf.setText("");
				jfsum.setText(jfsum.getText()+"*");
				break;
				
			case "/":
				jf.setText("");
				jfsum.setText(jfsum.getText()+"/");
				break;
				
			case ".":
				//소수계산
				soch = 1;
				String str = jf.getText();
				jf.setText(str + ".");
				jfsum.setText(jfsum.getText()+".");
				break;
				
			case "=":
				//두번 게산하는 이유가 뭐지 ? 
				//c.cal(jfsum.getText());
				double result = c.cal(jfsum.getText());
				if(soch==1) {
					//setText 안에는 문자열이 들어가니까
					jf.setText(result + "");
					jfsum.setText(result + "");
				}
				else {
					//setText 안에는 문자열이 들어가니까
					jf.setText(result+"");
					jfsum.setText(result+"");
				}
				//=을 쓰고 나서는 초기화
				check = 0;
				break;
		}
		
	}
	
	JTextField jfsum;
	JTextField jf;
	
	String[] str = {"←","√","mc","x2","+","7","8","9","-","4","5","6","*","1","2","3","/","0",".","="};
	JButton[] b = new JButton[20];
	JButton clear;
	//폰트 설정
	Font jffont = new Font("Serif", Font.BOLD, 40);
	//폰트 설정
	Font bfont = new Font("Serif", Font.BOLD, 20);
	
	CalGUI(){
		//이름
		setTitle("계산기");
		//크기
		setSize(500,500);
		setLayout(new BorderLayout());
		//뒷배경 판넬
		getContentPane().setBackground(Color.GRAY);
		
		//--------메뉴바 만들기------------------
		JMenuBar mb = new JMenuBar();
		JMenu menu1 = new JMenu("보기(v)");
		//단축키 사용 alt+v
		menu1.setMnemonic('v');
		JMenuItem item1 = new JMenuItem("Open");
		item1.setMnemonic('o');
		menu1.add(item1);
		
		JMenu menu2 = new JMenu("도움말(H)");
		menu2.setMnemonic('H');
		JMenuItem item2 = new JMenuItem("Help");
		item2.setMnemonic('h');
		menu2.add(item2);
		
		mb.add(menu1);
		mb.add(menu2);
		//프레임에 menubar 붙이기
		setJMenuBar(mb);
		
		//--------버튼 달기------------------
		//첫번째 패널 만들기
		//textField 판넬, 첫번째 판넬
		Panel tp = new Panel();
		tp.setLayout(new GridLayout(2,1));
		jfsum = new JTextField(getWidth());
		jf = new JTextField(getWidth());
		jf.setFont(jffont);
		jfsum.setHorizontalAlignment(JTextField.RIGHT);
		jf.setHorizontalAlignment(JTextField.RIGHT);
		jf.setEditable(false);
		tp.add(jfsum);
		tp.add(jf);
		
		//두번째 패널 만들기(버튼)
		Panel bp = new Panel();
		bp.setLayout(new GridLayout(5,4));
		for(int i = 0; i < 20; i ++) {
			b[i] = new JButton(str[i]);
			b[i].addActionListener(this);
			b[i].setFont(bfont);
			bp.add(b[i]);
		}
		
		//TextField panel을 Frame에 붙이기
		add(BorderLayout.NORTH, tp);
		//버튼 panel을 Farme에 붙이기
		add(BorderLayout.CENTER, bp);
		
		//--------프레임 위치-----------------
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//중앙에 배치
		setLocation((dim.width/2) - (getWidth()/2), (dim.height/2) - (getHeight()/2));
		//주의할 점 getWidth()을 하는데 만약 setSize를 밑에 줄에 적어주면 0으로 잡히기 때문에 크기값을 위에서 잡아 줄것
		//크기 고정
		setResizable(false);	
	}
	//= 이후인지 전인지 판별, = 사용한 다음 숫자를 누르면 바로 두번째 textField는 초기화 되어야함
	public void ch(String a) {
		if(check == 0) {
			jfsum.setText(jfsum.getText()+a);
			jf.setText(""+a);
			check = -1;
		}
		else {
			jf.setText(jf.getText()+a);
			jfsum.setText(jfsum.getText()+a);
		}
	}
	
	//C 버튼은 계산기를 완전히 초기화시킵니다. 계산했던 내용을 모두 지웁니다. 다른 계산을 하기 전에는 계산기를 리셋하는 것이 좋습니다.
	//CE 버튼은 모두 지우는 것이 아니라, 방금 전에 최종적으로 입력했던 숫자 1개만 지웁니다. 수식을 입력하다가 오타가 났을 때 지우는 버튼 입니다.
	public static void main(String[] args) {
		CalGUI c= new CalGUI();
		c.setVisible(true);
		c.jf.setText("0");
	}
}
