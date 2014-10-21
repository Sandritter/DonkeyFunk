package gui;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import enums.CategoryType;

/** 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 *
 * ImageLabel erbt von JLabel und ist Basisklasse fuer alle DragObjekte
 */
public class ImageLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 444994062163920000L;
	private ImageIcon image;
	@SuppressWarnings("unused")
	private String imagePath;
	private String name;
	private CategoryType categorie;
	private int startX;
	private int startY;
	private Object player;
	private boolean isInStomach;

	/**
	 * ImageLabelKonstruktor
	 * @param imagePath - BildPath des jeweiligen DragObjekts
	 * @param name - Name des DragObjekts
	 * @param categorie - Song Kategorie, dem das DragObjekt angehoert
	 * @param startX - xPos des DragObjekts (Anfangsposition)
	 * @param startY - yPos des DragObjekts (Anfangsposition)
	 * @param player - Player des DragObjekts
	 */
	public ImageLabel(String imagePath, String name, CategoryType categorie, int startX, int startY, Object player){
		super();
		this.imagePath = imagePath;
		this.name = name;
		this.categorie = categorie;
		this.startX = startX;
		this.startY = startY;
		this.player = player;
		setColorMask(false);
		image = new ImageIcon(imagePath);
		setIcon(image);
		// Legt die Startposition fest
		setBounds(this.startX, this.startY, image.getIconWidth(), image.getIconHeight());
	}
	
	/**
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return categorie
	 */
	public CategoryType getCategory(){
		return this.categorie;
	}
	
	/**
	 * 
	 * @return player
	 */
	public Object getPlayer(){
		return this.player;
	}

	/**
	 * 
	 * @return startX
	 */
	public int getStartX(){
		return this.startX;
	}
	
	/**
	 * 
	 * @return startY
	 */
	public int getStartY(){
		return this.startY;
	}

	/**
	 * 
	 * @return boolean fuer in Maske oder nicht
	 */
	public boolean isInStomach() {
		return isInStomach;
	}

	/**
	 * 
	 * @param colorMask - boolean Wert, der besagt ob in Maskenbereich oder nicht
	 */
	public void setColorMask(boolean colorMask) {
		this.isInStomach = colorMask;
	}
}