package gui;
import java.awt.Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * class Donkey erstellt und zeichnet einen Esel, der einzelne Koerperteile durch eine Animation bewegen kann
 */
@SuppressWarnings("serial")
public class Donkey extends JComponent {
	
	// IDs der jeweiligen Animation
	private final int EARFRAME = 0;
	private final int HEADFRAME = 1;
	private final int MOUTHFRAME = 2;
	private final int EARHEADFRAME = 3;
	private final int EARMOUTHFRAME = 4;
	private final int MOUTHHEADFRAME = 5;
	private final int EARHEADMOUTHFRAME = 6;
	private final int FOOTFRAME = 7;
	private final int TAILFRAME = 8;
	private final int BAMFRAME = 9;
	
	// Anzahl der Frames in einem FrameSet
	private final int TOTALIMAGES = 11;
	
	// Summe der einzelen FramSets
	private final int TOTALFRAMESET = 11;
	
	private ImageIcon frameSet [][];
	private int currentImage = 0;
	private int animationDelay = 80;
	
	private boolean [] animationArray;
	
	private Timer animator;
	private BufferedImage donkey_body;
	
	private int x;
	private int y;

	/**
	 * DonkeyKonstruktor
	 * @param x - xPos des Esels im AnimationPanel
	 * @param y - yPos des Esels im AnimationPanel
	 */
	public Donkey(int x, int y) {
		
		this.x = x;
		this.y = y;
		frameSet = new ImageIcon[TOTALFRAMESET][TOTALIMAGES];
		animationArray = new boolean[5];
				
		try {
			donkey_body = ImageIO.read(new File("img/essential/body-neu.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		fillFrameSet();
		setBounds(this.x, this.y, 1024, 768);
		
		animator = new Timer(animationDelay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentImage++;
				if (currentImage >= TOTALIMAGES) {
					currentImage = 0;
				}
				repaint();
			}
		}); 
		animator.start();
	}
	
	/**
	 * erstellt und befuellt FrameSet
	 */
	private void fillFrameSet(){
		// hier wird das komplette FrameSet Array mit den einzelnen Frames befuellt
		for (int i = 0; i < TOTALFRAMESET; i++) {
			for (int j = 0; j < TOTALFRAMESET; j++) {
				if (i == EARFRAME){
					frameSet[EARFRAME][j] = new ImageIcon("img/ear/ear" + j + ".png");
				}
				if (i == HEADFRAME){
					frameSet[HEADFRAME][j] = new ImageIcon("img/head/head" + j + ".png");
				}
				if (i == MOUTHFRAME){
					frameSet[MOUTHFRAME][j] = new ImageIcon("img/mouth/mouth" + j + ".png");
				}
				if (i == EARHEADFRAME){
					frameSet[EARHEADFRAME][j] = new ImageIcon("img/ear_head/ear_head" + j + ".png");
				}
				if (i == EARMOUTHFRAME){
					frameSet[EARMOUTHFRAME][j] = new ImageIcon("img/ear_mouth/ear_mouth" + j + ".png");
				}
				if (i == MOUTHHEADFRAME){
					frameSet[MOUTHHEADFRAME][j] = new ImageIcon("img/mouth_head/mouth_head" + j + ".png");
				}
				if (i == EARHEADMOUTHFRAME){
					frameSet[EARHEADMOUTHFRAME][j] = new ImageIcon("img/ear_head_mouth/ear_head_mouth" + j + ".png");
				}
				if (i == FOOTFRAME){
					frameSet[FOOTFRAME][j] = new ImageIcon("img/foot/foot" + j + ".png");
				}
				if (i == TAILFRAME){
					frameSet[TAILFRAME][j] = new ImageIcon("img/tail/tail" + j + ".png");
				}
				if (i == BAMFRAME){
					frameSet[BAMFRAME][j] = new ImageIcon("img/bam/bam" + j + ".png");
				}
			}
		}
	}

	/**
	 * zeichnet je nach True oder False in den Feldern des animationArray die jeweilige Bewegung des Esels
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if (animationArray[0] == true){
			frameSet[BAMFRAME][currentImage].paintIcon(this, g, 0, 155);
		}
		g.drawImage(donkey_body, 430, 0, this);
		if (animationArray[1] == true && animationArray[3] == true && animationArray[4] == true){
			frameSet[EARHEADMOUTHFRAME][currentImage].paintIcon(this, g, 468, 0);
			
		} else if(animationArray[1] == true && animationArray[3] == true && animationArray[4] == false){
			frameSet[MOUTHHEADFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else if(animationArray[1] == true && animationArray[3] == false && animationArray[4] == true){
			frameSet[EARHEADFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else if(animationArray[1] == false && animationArray[3] == true && animationArray[4] == true){
			frameSet[EARMOUTHFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else if(animationArray[1] == true && animationArray[3] == false && animationArray[4] == false){
			frameSet[HEADFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else if(animationArray[1] == false && animationArray[3] == true && animationArray[4] == false){
			frameSet[MOUTHFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else if(animationArray[1] == false && animationArray[3] == false && animationArray[4] == true){
			frameSet[EARFRAME][currentImage].paintIcon(this, g, 468, 0);
		} else {
			frameSet[HEADFRAME][0].paintIcon(this, g, 468, 0);
		}
		if (animationArray[2] == true){
			frameSet[TAILFRAME][currentImage].paintIcon(this, g, 550, 161);
		} else {
			frameSet[TAILFRAME][2].paintIcon(this, g, 550, 161);
		}
		if (animationArray[0] == true){
			frameSet[FOOTFRAME][currentImage].paintIcon(this, g, 498, 173);
		} else {
			frameSet[FOOTFRAME][0].paintIcon(this, g, 498, 173);
		}	
		
	}

	/**
	 * 
	 * @param array - setzt das animatioArray, das dem Esel mitteilt welches Koerperteil er bewegen soll
	 */
	public void setAnimationArray(boolean [] array){
		this.animationArray = array;
	}
}