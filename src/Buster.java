import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Buster {
	
	private Point point;
	private int x;
	private int y;
	public Image target;
	
	
	public Buster() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("target.png"));
		target = ii.getImage();
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void move() {
		point = MouseInfo.getPointerInfo().getLocation();
		x = point.x;
		y = point.y;
	}
}
