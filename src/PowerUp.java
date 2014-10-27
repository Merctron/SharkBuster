import java.awt.Image;
import javax.swing.ImageIcon;


public class PowerUp { 
	
	public String visibility = "not visible";
	private int x;
	private int y;
	public Image power;
	
	
	public PowerUp() {
		ImageIcon ii = new ImageIcon(this.getClass().getResource("energy.png"));
		power = ii.getImage();
		x = randomize();
		y = randomize();
	}
	
	public void move(int xRate, int yRate) {
		x += 3 * xRate;
		y += 3 * yRate;
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
	
	private int randomize() {
    	double randomPick = Math.random();
    	int startPoint;
    	if (randomPick < 0.1 && randomPick > 0) {
    		startPoint = 10;
    	}
    	else if (randomPick < 0.2 && randomPick >= 0.1) {
    		startPoint = 200;
    	}
    	else if (randomPick < 0.3 && randomPick >= 0.2) {
    		startPoint = 300;
    	}
    	else if (randomPick < 0.4 && randomPick >= 0.3) {
    		startPoint = 400;
    	}
    	else if (randomPick < 0.5 && randomPick >= 0.4) {
    		startPoint = 500;
    	}
    	else if (randomPick < 0.6 && randomPick >= 0.5) {
    		startPoint = 600;
    	}
    	else if (randomPick < 0.7 && randomPick >= 0.6) {
    		startPoint = 700;
    	}
    	else if (randomPick < 0.8 && randomPick >= 0.7) {
    		startPoint = 800;
    	}
    	else if (randomPick < 0.9 && randomPick >= 1) {
    		startPoint = 900;
    	}
    	else {
    		startPoint = 100;
    	}
    	return startPoint;
    }

}
