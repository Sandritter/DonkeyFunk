package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
/**
 * 
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 * AnimationPanel ist Container fuer alle GuiElemente, die dem AnimationPanel im GuiBuilder hinzugefuegt werden
 */
@SuppressWarnings("serial")
public class AnimationPanel extends JLayeredPane {
	
	private BufferedImage backgroundImage;
	private String defaultPath = "img/essential/bg.jpg";
	
	/**
	 * AnimationPanelKonstrukor
	 */
	public AnimationPanel(){
		try {
			backgroundImage = ImageIO.read(new File(defaultPath));
		} catch (IOException e) {	
			System.out.println(e.getMessage());
		}
		setPreferredSize(new Dimension(1024, 768));
	}
	
	/**
	 * zeichnet den Hintergrund
	 */
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		g.drawImage(backgroundImage, 0, 0, this);
	}
	
	/**
	 * is setting Background Image
	 * @param path - filepath von dem Image, zu welchem geschwitcht werden soll
	 */
	public void setBackgroundImage(String path){
		this.defaultPath = path;
		try {
			this.backgroundImage = ImageIO.read(new File(defaultPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		repaint();
	}
	
	/**
	 * 
	 * @return ImagePath - ImagePath des zu setzenden Hintergrundbildes
	 */
	public String getBackgroundPath(){
		return this.defaultPath;
	}

}
