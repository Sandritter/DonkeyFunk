package gui;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import enums.CategoryType;

@SuppressWarnings("serial")
/**
 * 
 * @author MerleHiort, MichaelSandritter, BenjaminChristaini, JoergEinfeldt
 *	Komponente fuer den Magen in dem sich die DragObjekte befinden und abgespielt werden
 */
public class Stomach extends JComponent {

	private int[][] coordinates;
	private ImageLabel[] objects;
	private boolean[] categories; //Boolean an Stelle im Array entspicht Kategorie
	private BufferedImage stomachImg;
	private int x;
	private int y;
	
	/**
	 * Konstruktor fuer den Magen
	 * @param x - xPosition des Bildes
	 * @param y - yPosition des Bildes
	 * @param path - Dateipfad zum Bild
	 */
	public Stomach(int x, int y, String path) {
		this.x = x;
		this.y = y;
		this.coordinates = new int[6][2];
		this.objects = new ImageLabel[6];
		this.categories = new boolean[5];
		/*
		 * 1.Bass
		 * 2.Beats
		 * 3.Harmony
		 * 4.Melody
		 * 5.Effects
		 */
		
		try {
			stomachImg = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setBounds(x, y, stomachImg.getWidth(), stomachImg.getHeight());
		createCoordinates();
	}

	/**
	 * Setzen der Koordinaten fuer die Dragobjekte im Magen
	 */
	private void createCoordinates() {
		coordinates[0][0] = 110;
		coordinates[1][0] = 189;
		coordinates[2][0] = 48;
		coordinates[3][0] = 107;
		coordinates[4][0] = 36;
		coordinates[5][0] = 168;
		coordinates[0][1] = 239;
		coordinates[1][1] = 217;
		coordinates[2][1] = 215;
		coordinates[3][1] = 168;
		coordinates[4][1] = 148;
		coordinates[5][1] = 153;
	}

	/**
	 * Aktualisiert welche Soundkategorien sich im Magen befinden
	 * Wird benoetigt fuer die entsprechende Animatin des Esels
	 */
	private void updateCategory() {
		for (@SuppressWarnings("unused") boolean act : categories) {
			act = false;
		}
		for (ImageLabel act : objects) {
			if (act != null) {
				CategoryType tmp = act.getCategory();
				switch (tmp) {
				case BASS:
					categories[0] = true;
				case BEATS:
					categories[1] = true;
				case EFFECTS:
					categories[2] = true;
				case HARMONY:
					categories[3] = true;
				case MELODY:
					categories[4] = true;
				}
			}
		}
	}

	/**
	 * Fuegt dem Magen ein Dragobjekt hinzu
	 * @param imgLabel - Das Dragobjekt
	 */
	public void addDragObject(ImageLabel imgLabel) {
		int i = 0;
		while (objects[i] != null) {
			i++;
		}
		if (i < objects.length) {
			objects[i] = imgLabel;
		}
		objects[i].setBounds(coordinates[i][0]+x, coordinates[i][1]+y, 70, 70);
		updateCategory();
	}

	/**
	 * Loescht ein Dragobjekt aus dem Magen mit dem entsprechenden Namen
	 * @param name - Name des Dragobjekt
	 */
	public void deleteDragObject(String name) {
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] != null && objects[i].getName().compareTo(name) == 0) {
				objects[i] = null;
			}
		}
		updateCategory();
	}
	/**
	 * Getter fuer die Soundkategorien die sich im Magen befinden
	 * Wird fuer die Animation des Esels benoetigt
	 * @return
	 */
	public boolean [] getCategories(){
		return this.categories;
	}
	/**
	 * Getter fuer Dragobjekt
	 * @param name - Name des Dragobjekts
	 * @return Dragobjekt mit name
	 */
	public int [] getDragObjectPos(String name){
		int i = 0;
		int [] ret = new int[2];
		while (objects[i] ==  null || objects[i].getName().compareTo(name)!=0){
			i++;
		}
		ret[0] = coordinates[i][0] + x;
		ret[1] = coordinates[i][1] + y;
		return ret;
	}
	
	/**
	 * Zeichnet den Magen
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(stomachImg, 0, 0, this);
	}
	
	/**
	 * Ueberprueft ob im Array noch Platz ist fuer Dragobjekte
	 * Es koennen nur 6 Dragobjekte in den Magen rein
	 * @return
	 */
	public boolean isFull(){
		for (int i = 0; i < objects.length; i++){
			if(objects[i] == null){
				return false;
			}
		}
		return true;
	}
}
