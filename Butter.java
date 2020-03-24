package ReallyMan;

import java.awt.Graphics;
import java.awt.Image;

public class Butter {
	int x;
	int y;
	int xSpeed;
	int ySpeed;
	Image img;
	public Butter(){
		
	}
	public void setXSpeed(int x){
		xSpeed = x;
	}
	public void setYSpeed(int y){
		ySpeed = y;
	}
	public Butter(int x, int y, int xSpeed, int ySpeed, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.img = img;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x-3,y-3,null);
	}
	public void move(){
		x += xSpeed;
		y += ySpeed;
	}
}
