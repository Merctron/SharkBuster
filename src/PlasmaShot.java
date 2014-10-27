import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;

import javax.swing.ImageIcon;


public class PlasmaShot {
	
	public String visibility = "not visible";
	private int x;
	private int y;
	public Image shot;
	
	
	public PlasmaShot(int x, int y) {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("bust.gif"));
		shot = ii.getImage();
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setVisible() {
		visibility = "visible";
	}

}
