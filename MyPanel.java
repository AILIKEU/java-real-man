package ReallyMan;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener,MouseListener{
	Random random = new Random();
	
	ArrayList<Butter> bList = new ArrayList<Butter>();
	Plane plane;
	Image planeImg;
	Point p1,p2;
	int count = 0;
	long timeStar;
	long timeCurret;
	long timeEnd;

	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAMEOVER = 3;
	int state = START;
	
	public MyPanel(){
		timeStar = System.currentTimeMillis();
		planeImg = new ImageIcon("image/plane/hero.gif").getImage();
		plane = new Plane(290,230,planeImg,4);
		for(int i=0;i<12;i++){
			addButter();
		}
		
		gameMove();
	}
	public void paint(Graphics g){
		super.paint(g);
		if(state == RUNNING){
			setBackground(Color.white);
			g.setColor(Color.black);
			g.fillRect(20,10,540,440);
			plane.draw(g);
			for(int i=0;i<bList.size();i++){
				bList.get(i).draw(g);
			}
			g.setFont(new Font("123",Font.PLAIN,20));
			g.setColor(Color.pink);
			timeCurret = System.currentTimeMillis();
			timeEnd = timeCurret-timeStar;
			String show = "时间： " + timeEnd/1000+"'"+timeEnd%1000;
			g.drawString(show, 300,100);
		}else if(state == START){
			Image img = new ImageIcon("image/1.png").getImage();
			g.drawImage(img,300,200,null);
			g.setFont(new Font("宋体",Font.BOLD,50));
			g.setColor(Color.green);
			g.drawString("坚持20秒", 150,200);
		}else if(state == GAMEOVER){
			g.setFont(new Font("宋体",Font.BOLD,50));
			g.setColor(Color.green);
			g.drawString("GAME OVER", 150,200);
			g.setFont(new Font("宋体",Font.BOLD,40));
			g.setColor(Color.pink);
			g.drawString(timeEnd/1000+"'"+timeEnd%1000, 250, 250);
		}else if(state == PAUSE){
			
		}
		
	}
	public void addButter(){
		Image img = new ImageIcon("image/plane/bullet3456.png").getImage();
		int x = 30;
		int y = 20;
		int xSpeed = random.nextInt(10);
		int ySpeed = random.nextInt(10);
		if(xSpeed == 0){
			xSpeed ++;
		}
		if(ySpeed == 0){
			ySpeed++;
		}
		Butter butter = new Butter(x,y,xSpeed,ySpeed,img);
		bList.add(butter);
	}
	public void gameMove(){
		new Thread(){
			public void run(){
				int count2 = 0;
				while(true){
					if(state == RUNNING){
						count2 ++;
						plane.move();
						for(int i=0;i<bList.size();i++){
							if(count2 >50){
								bList.get(i).move();
							}
							if(bList.get(i).x<20){
								bList.get(i).xSpeed = Math.abs(bList.get(i).xSpeed);
							}
							if(bList.get(i).x>560){
								bList.get(i).xSpeed = -Math.abs(bList.get(i).xSpeed);
							}
							if(bList.get(i).y<10){
								bList.get(i).ySpeed = Math.abs(bList.get(i).ySpeed);
							}
							if(bList.get(i).y>450){
								bList.get(i).ySpeed = -Math.abs(bList.get(i).ySpeed);
							}
						}
						//ѭ���ж��Ƿ���ײ
						for(int i =0;i<bList.size();i++){
							p1 = new Point(bList.get(i).x,bList.get(i).y);
							p2 = new Point(plane.x,plane.y);
							int len12 = distance2(p1, p2);
							
							int len22 = plane.w*plane.w/16+plane.h*plane.h/16;
							if(len12<len22){	
								count2 = 0;
								
								state = GAMEOVER;
								repaint();
								break;
							}
						}
						
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
				}
				
			}
		}.start();
	}
	public int distance2(Point p1,Point p2){
		int xLen2;
		int yLen2;
		xLen2 = (int) (p1.getX() - p2.getX());
		xLen2 = xLen2 * xLen2;
		yLen2 = (int) (p1.getY() - p2.getY());
		yLen2 = yLen2 *yLen2 ;
		int len2 = xLen2 + yLen2;
		return len2;
	}
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_SPACE){
			if(state == START){
				state = RUNNING;
				bList.removeAll(bList);
				for(int j=0;j<12;j++){
					addButter();	
				}
				plane = new Plane(290,230,planeImg,4);
				timeStar = System.currentTimeMillis();
			}else if(state == RUNNING){
				state = PAUSE;
			}else if(state == PAUSE){
				state = RUNNING;
			}else if(state == GAMEOVER){
				state = START;
			}
		}
		plane.pressed(e);
		
	}
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		plane.released(e);
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state == START){
			bList.removeAll(bList);
			for(int j=0;j<12;j++){
				addButter();	
			}
			plane = new Plane(290,230,planeImg,4);
			timeStar = System.currentTimeMillis();
			state = RUNNING;
			repaint();
		}else if(state == GAMEOVER){
			state = START;
			repaint();
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
