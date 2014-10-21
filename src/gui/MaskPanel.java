package gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/** 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 *
 * ImageLabel erbt von JLabel und ist Basisklasse fuer alle DragObjekte
 */
@SuppressWarnings("serial")
public class MaskPanel extends JComponent{
	private BufferedImage mask;
	private ImageIcon image;
	private final int BLACK = -16777216;
	private final int RED = -131072;
	
	/**
	 * MaskPanel Konstruktor
	 * @param imagePath enthaelt den Path fuer das Image der Maske
	 */
	public MaskPanel(String imagePath){
		try {
			mask = ImageIO.read(new File(imagePath));
			image = new ImageIcon(imagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(0,0, image.getIconWidth(), image.getIconHeight());
	}
	
	/**
	 * ueberprueft ob curser in maske schwarz
	 * @param x
	 * @param y
	 * @return true if in black, false if not in black
	 */
	public boolean isblack(int x, int y){
		if (super.contains(x,y)){
			if(mask.getRGB(x,y)==BLACK){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * ueberprueft ob curser in maske red
	 * @param x
	 * @param y
	 * @return true if in red, false if not in red
	 */
	public boolean isred(int x, int y){
		if (super.contains(x,y)){
			if(mask.getRGB(x, y)==RED){
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(image.getIconWidth(),image.getIconHeight());
	}
}