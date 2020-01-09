package java_Calculator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CalGUI extends JFrame implements ActionListener {

	//���  ��ü
	CalLogic c = new CalLogic();
	int check = -1;
	soch = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//text �޾ƿ��� ����
		String mun;
		
		//getActionCommand() ��ư�� �ؽ�Ʈ�� ���� , getSource() �̺�Ʈ�� �߻��� ������Ʈ ���
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
				
			//���ڸ� �ι�° �ؽ�Ʈ ���� ����
			case "��":
				mun = jfsum.getText();
				mun = mun.substring(0,mun.length()-1);
				jfsum.setText(mun);
				jf.setText("");
				break;
			
			case "x2":
				mun = jfsum.getText();
				//+-* ���а�
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
			
			case "��":
				mun = jfsum.getText();
				//+-* ���а�
				StringTokenizer t1 = new StringTokenizer(mun, "+/*-", true);
				String na1 = null;
				double re1 = 0;
				
				while(t1.hasMoreTokens()) {
					String x1 = t1.nextToken();
					na1 = x1;
				}
				System.out.println(na);
				jfsum.setText(Math.sqrt(Double.parseDouble(na1)) + "");
				break;
				
			case "mc":
				jf.setText();
				jfsum.setText();
				//�Ҽ� �ʱ�ȭ
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
				//�Ҽ����
				soch = 1;
				String str = jf.getText();
				jf.setText(str + ".");
				jfsum.setText(jfsum.getText()+".");
				break;
				
			case "=":
				c.cal(jfsum.getText());
				double result = c.cal(jfsum.getText());
				if(soch==1) {
					//setText �ȿ��� ���ڿ��� ���ϱ�
					jf.setText(result + "");
					jfsum.setText(result + "");
				}
				else {
					//setText �ȿ��� ���ڿ��� ���ϱ�
					jf.setText((int)result+"");
					jfsum.setText((int)result+"");
				}
				//=�� ���� ������ �ʱ�ȭ
				check = 0;
				break;
		}
		
	}
	
	JTextField jfsum;
	JFrame jf;
	
	String[] str = {"=",".","/","0","1","2","3","4","5","6","7","8","9","��","x2","��","mc","+","-","*"};
	JButton[] b = new JButton[20];
	JButton clear;
	//��Ʈ ����
	Font jffont = new Font("arian", Font.BOLD, 40);
	//��Ʈ ����
	Font bfont = new Font("arian", Font.BOLD, 20);
	
	CalGUI(){
		
	}

}
