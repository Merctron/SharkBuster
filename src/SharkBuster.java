import java.io.File;

import javax.swing.JFrame;

public class SharkBuster extends JFrame {
	
	public SharkBuster() {
		add(new Arena());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setTitle("Shark Buster");
        setVisible(true);	
	}
	
	public static void main(String[] args) {
		new SharkBuster();	
	}
}
