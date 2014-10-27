import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Arena extends JPanel implements Runnable {
	
	
	Image reSpawn;
	Image BackGround;
	
	Buster buster;
	private Thread animator;
	int threshold = 0;
	int limit = 2500;
	int madFactor = 1;
	int shotCounter = 0;
	int tolerance = limit;
	static int Score = 0;
	static int KillsNeeded = 2;
	static ArrayList<Shark> Sharks = new ArrayList<Shark>();
	static ArrayList<SharkTroll> SharkTrolls = new ArrayList<SharkTroll>();
	static ArrayList<PowerUp> PowerUps = new ArrayList<PowerUp>();
	static ArrayList<Integer> xRates = new ArrayList<Integer>();
	static ArrayList<Integer> yRates = new ArrayList<Integer>();
	static ArrayList<Integer> xRates2 = new ArrayList<Integer>();
	static ArrayList<Integer> yRates2 = new ArrayList<Integer>();
	static ArrayList<Integer> xRates3 = new ArrayList<Integer>();
	static ArrayList<Integer> yRates3 = new ArrayList<Integer>();
	static ArrayList<PlasmaShot> shots = new ArrayList<PlasmaShot>();
	private final int timeLapse = 50;

    public Arena() {
    	addMouseListener(new pAdapter());
    	setBackground(Color.BLACK);
        setDoubleBuffered(true);
        ImageIcon ii = new ImageIcon(this.getClass().getResource("BackGround.jpg"));
        ImageIcon jj = new ImageIcon(this.getClass().getResource("spawn.png"));
        reSpawn = jj.getImage();
        BackGround = ii.getImage();
        buster = new Buster();
        for (int i = 0; i < 10; i++) {
        	Sharks.add(new Shark());
        	xRates.add(randomMoveRate());
        	yRates.add(randomMoveRate());
        }
    }

    public void paint(Graphics g) {
    	super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(BackGround, 0, 0, null);
        for (int i = 0; i < Sharks.size(); i++) {
        	g2d.drawImage(Sharks.get(i).Face, Sharks.get(i).getX(), Sharks.get(i).getY(), null);
        }
        for (int i = 0; i < shots.size(); i++) {
        	g2d.drawImage(shots.get(i).shot, shots.get(i).getX(), shots.get(i).getY(), null);
        }
        for (int i = 0; i < SharkTrolls.size(); i++) {
        	g2d.drawImage(SharkTrolls.get(i).troll, SharkTrolls.get(i).getX(), SharkTrolls.get(i).getY(), null);
        }
        for (int i = 0; i < PowerUps.size(); i++) {
        	g2d.drawImage(PowerUps.get(i).power, PowerUps.get(i).getX(), PowerUps.get(i).getY(), null);
        }
        g2d.drawImage(buster.target, buster.getX() - 80, buster.getY() - 80, null);
        String Scorest = String.format("Score = %s", Score);
        String Killsst = String.format("Kills Needed = %s", KillsNeeded);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 25)); 
        g2d.drawString(Scorest, 20, 30);
        g2d.drawString(Killsst, 200, 30);
        
        g2d.draw3DRect(700, 20, Math.round(limit/20), 40, true);
        g2d.setColor(Color.RED);
        g2d.fill3DRect(700, 30, Math.round(threshold/20), 20, true);
       
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    public void addNotify() {
    	super.addNotify();
    	animator = new Thread(this);
    	animator.start();
    }
    
    public void clearShots () {
    	shots.clear();
    }
    
    public int randomMoveRate() {
    	double chance = Math.random();
    	int rate;
    	if (chance <= 0.33) {
    		rate = madFactor;
    	}
    	else if (chance <= 0.66){
    		rate = -madFactor;
    	}
    	else {
    		rate = 0;
    	}
    	return rate;
    }
    
    public void gameOver() {
    	Graphics g = this.getGraphics();
    	String Scorest = String.format("Score = %s", Score);
    	
    	g.clearRect(0, 0, 1500, 1200);
    	g.drawImage(reSpawn, 400, 150, null);
    	g.setColor(Color.RED);
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
    	g.drawString("YOU LOST!", 460, 50);
    	g.drawString("YOU MAD BRO?", 410, 590);
    	g.drawString(Scorest, 470, 650);
    }
    
    public void cycle() {
    	
    	
    	buster.move();
    	for (int i = 0; i < Sharks.size(); i++) {
    		Sharks.get(i).move(xRates.get(i), yRates.get(i));
    	}
    	for (int i = 0; i < SharkTrolls.size(); i++) {
    		SharkTrolls.get(i).move(xRates2.get(i), yRates2.get(i));
    	}
    	for (int i = 0; i < PowerUps.size(); i++) {
    		PowerUps.get(i).move(xRates3.get(i), yRates3.get(i));
    	}
    	
    	threshold += 5;
    	shotCounter += 1;
    	if (shotCounter > 15) {
    		clearShots();
    		shotCounter = 0;
    	}
    	if (Score >= KillsNeeded) {
    		KillsNeeded = Score + Math.round((Score * 85) / 100);
    		threshold = 0;
    		tolerance = Math.round((limit / 2));
    		limit = limit + 200;
    	}
    	
    	if (threshold > tolerance) {
    		tolerance = tolerance * 2;
    		for (int i = 0; i < KillsNeeded; i++) {
            	Sharks.add(new Shark());
            	xRates.add(randomMoveRate());
            	yRates.add(randomMoveRate());
    		}
    		for (int i = 0; i < 5; i++) {
            	SharkTrolls.add(new SharkTroll());
            	xRates2.add(randomMoveRate());
            	yRates2.add(randomMoveRate());
    		}
    		for (int i = 0; i < 1; i++) {
            	PowerUps.add(new PowerUp());
            	xRates3.add(randomMoveRate());
            	yRates3.add(randomMoveRate());
    		}
            
            
    	}
    }

	public void run() {
		
		long initT;
		long deltaT;
		long sleep;
		
		initT = System.currentTimeMillis();
		while (true) {
			
			for (int i = 0; i < Sharks.size(); i++) {
	    		for (int j = 0; j < Arena.shots.size(); j++) {
	    			if (Arena.shots.get(j).getX() < Arena.Sharks.get(i).getX() + 50 && 
	    					Arena.shots.get(j).getX() > Arena.Sharks.get(i).getX() - 50 
	    					&& Arena.shots.get(j).getY() < Arena.Sharks.get(i).getY() + 70 
	    					&& Arena.shots.get(j).getY() > Arena.Sharks.get(i).getY() - 70) {
	    				Arena.Sharks.remove(i);
	    				Arena.Score = Score + 1;
	    			}
	    		}
	    	}
			for (int i = 0; i < SharkTrolls.size(); i++) {
	    		for (int j = 0; j < Arena.shots.size(); j++) {
	    			if (Arena.shots.get(j).getX() < Arena.SharkTrolls.get(i).getX() + 50 && 
	    					Arena.shots.get(j).getX() > Arena.SharkTrolls.get(i).getX() - 50 
	    					&& Arena.shots.get(j).getY() < Arena.SharkTrolls.get(i).getY() + 70 
	    					&& Arena.shots.get(j).getY() > Arena.SharkTrolls.get(i).getY() - 70) {
	    				Arena.SharkTrolls.remove(i);
	    				KillsNeeded = KillsNeeded + Math.round((KillsNeeded * 50) / 100);
	    			}
	    		}
	    	}
			for (int i = 0; i < PowerUps.size(); i++) {
	    		for (int j = 0; j < Arena.shots.size(); j++) {
	    			if (Arena.shots.get(j).getX() < Arena.PowerUps.get(i).getX() + 50 && 
	    					Arena.shots.get(j).getX() > Arena.PowerUps.get(i).getX() - 50 
	    					&& Arena.shots.get(j).getY() < Arena.PowerUps.get(i).getY() + 70 
	    					&& Arena.shots.get(j).getY() > Arena.PowerUps.get(i).getY() - 70) {
	    				Arena.PowerUps.remove(i);
	    				Score = Score + KillsNeeded - Score - 3;
	    				for (int m = 0; m < (KillsNeeded - Score - 3); i++) {
	    					Sharks.remove(m);
	    					xRates.remove(m);
	    					yRates.remove(m);
	    				}
	    			}
	    		}
	    	}
			
			cycle();
			if (threshold > limit) {
	    		if (Score < KillsNeeded) {
	    			gameOver();
	    			animator.stop();
	    			
	    		}
	    		else {
	    			threshold = 0;
	    			repaint();
	    		}
			}
			else {
				repaint();
			}
			
			deltaT = System.currentTimeMillis() - initT;
			sleep = timeLapse - deltaT;
			
			if (sleep < 0) 
                sleep = 2;
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
            initT = System.currentTimeMillis();
		}
	}
	
	public class pAdapter extends MouseAdapter {
		
		int pX;
		int pY;
		
		public void mouseClicked(MouseEvent m) {
			
			if (m.getID() == MouseEvent.MOUSE_CLICKED) {
				Point fireSite = m.getPoint();
			
				pX = fireSite.x;
				pY = fireSite.y;
				shots.add(new PlasmaShot(pX - 40, pY - 20));
			}
		}
		
	}
}
