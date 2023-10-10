package calculator;

import javax.swing.*;

import util.Const;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener{
	//北控件
	private JPanel jp_north =new JPanel();
	private JTextField input_text=new JTextField();
	private JButton c_Btn =new JButton("C");
	//中
	private JPanel jp_center =new JPanel();
	
	
	public calculator() throws HeadlessException{
		this.init();
		this.addNorthComponent();
		this.addCenterComponent();
	}
	public void init() {
		this.setTitle(Const.TITLE);
		this.setSize(Const.FRAME_W, Const.FRAME_H);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setLocation(Const.FRAME_X,Const.FRAME_Y);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//关闭窗口退出程序	

	}
	
	public void addNorthComponent() {
		this.input_text.setPreferredSize(new Dimension(230,30));
		jp_north.add(input_text);
		this.c_Btn.setForeground(Color.red);
		jp_north.add(c_Btn);
		c_Btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				input_text.setText("");
			}});
		this.add(jp_north,BorderLayout.NORTH);
		
	}
	
	public void addCenterComponent() {
		String btn_text="123+456-789*0.=/";
		String regex = "[\\+\\-*/.=]";
		this.jp_center.setLayout(new GridLayout(4,4));
		for(int i=0;i<16;i++) {
			String temp=btn_text.substring(i,i+1);
			JButton btn = new JButton();
			btn.setText(temp);
			if(temp.matches(regex)) {
				btn.setFont(new Font("粗体",Font.BOLD,16));
				btn.setForeground(Color.red);		
			}
//			if(temp.equals("+")||
//					temp.equals("-")||
//					temp.equals("*")||
//					temp.equals("/")||
//					temp.equals(".")||
//					temp.equals("=")) {
//				btn.setFont(new Font("粗体",Font.BOLD,16));
//				btn.setForeground(Color.red);
//			}
			btn.addActionListener(this);
			jp_center.add(btn);
		}
		this.add(jp_center,BorderLayout.CENTER);
		
	}
	public static void main(String[] args) {
		calculator calculator = new calculator();
		calculator.setVisible(true);
		
		
		
	}
	private String firstInput = null;
	private String operator = null;
	@Override
	public void actionPerformed(ActionEvent e) {
		String clickStr = e.getActionCommand();
		if(".o1234567890".indexOf(clickStr)!=-1) {
			this.input_text.setText(input_text.getText()+clickStr);
			this.input_text.setHorizontalAlignment(JTextField.RIGHT);
			//JOptionPane.showMessageDialog(this, clickStr);
		}else if(clickStr.matches("[\\+\\-\\*/]{1}")) {
			operator = clickStr;
			firstInput =this.input_text.getText();
			this.input_text.setText("");
		}else if(clickStr.equals("=")) {
			Double a = Double.valueOf(firstInput);
			Double b = Double.valueOf(this.input_text.getText());
			Double result = null;
			switch(operator) {
				case"+":
					result = a+b;
					break;
				case"-":
					result =a-b;
					break;
				case"*":
					result=a*b;
					break;
				case"/":
					if(b!=0) {
						result = a/b;
					}
					break;
			}
			this.input_text.setText(result.toString());
		}
	}

}
