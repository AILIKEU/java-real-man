package ReallyMan;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame implements ActionListener{
	JFrame f = new JFrame();
	MyPanel p = new MyPanel();
	public MainFrame(){
		f.setTitle("20秒真男人");
		f.setBounds(100,100,600,500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setUndecorated(true);
		f.add(p);
		f.addKeyListener(p);
		f.addMouseListener(p);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new MainFrame();

	}
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
