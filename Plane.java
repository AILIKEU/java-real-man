package ReallyMan;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

public class Plane {
	int w;
	int h;
	int x;
	int y;
	//代码实现将将x，y看做飞机的中心点！
	Image img;
	int speed;

	enum D {
		U, UR, R, DR, D, DL, L, UL, STOP
	};

	D dir = D.STOP;

	boolean bL = false, bR = false, bU = false, bD = false;

	public Plane(int x, int y, Image img, int speed) {
		super();
		this.img = img;
		w = img.getWidth(null);
		h = img.getHeight(null);
		this.x = x ;
		this.y = y ;
		this.speed = speed;
	}

	public void draw(Graphics g) {
		g.drawImage(img, x-w/2, y-h/2, null);
	}

	public void move() {
		dir();

		
		switch (dir) {
		case U:
			y -= speed;
			break;
		case UR:
			x += speed;
			y -= speed;
			break;

		case R:
			x += speed;
			break;

		case DR:
			x += speed;
			y += speed;
			break;

		case D:
			y += speed;
			break;

		case DL:
			x -= speed;
			y += speed;
			break;
		case L:
			x -= speed;
			break;
		case UL:
			x -= speed;
			y -= speed;
			break;
		default:
			break;
		}
		if (x < 20) {
			x = 20;
		}
		if (x > 560) {
			x = 560;
		}
		if (y < 10) {
			y = 10;
		}
		if (y > 450) {
			y = 450;
		}
	}

	public void dir() {
		if (bU && !bL && !bR) {
			dir = D.U;
		} else if (bU && bR) {
			dir = D.UR;
		} else if (bR && !bD) {
			dir = D.R;
		} else if (bR && bD) {
			dir = D.DR;
		} else if (bD && !bL) {
			dir = D.D;
		} else if (bD && bL) {
			dir = D.DL;
		} else if (bL && !bU) {
			dir = D.L;
		} else if (bL && bU) {
			dir = D.UL;
		} else {
			dir = D.STOP;
		}
	}

	public void pressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			bU = true;
			break;
		case KeyEvent.VK_DOWN:
			bD = true;
			break;
		case KeyEvent.VK_LEFT:
			bL = true;
			break;
		case KeyEvent.VK_RIGHT:
			bR = true;
			break;

		default:
			break;
		}

	}

	public void released(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			bU = false;
			break;
		case KeyEvent.VK_DOWN:
			bD = false;
			break;
		case KeyEvent.VK_LEFT:
			bL = false;
			break;
		case KeyEvent.VK_RIGHT:
			bR = false;
			break;

		default:
			break;
		}

	}
}
