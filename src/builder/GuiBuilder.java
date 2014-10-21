package builder;

import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import gui.Stomach;
import manager.PlayerManager;
import businessLogic.Player;
import gui.AnimationPanel;
import gui.CyclusBar;
import gui.Donkey;
import gui.ImageLabel;
import gui.MaskPanel;
import enums.CategoryType;

/**
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 *
 * class GuiBuilder ist eine Singleton-Klasse, in welcher die Elemente, aus denen sich die GUI zusammensetzt, 
 * (mit ihren Positionskoordinaten) erzeugt und zum AnimationPanel hinzugefuegt werden. 
 *  
 */
public class GuiBuilder {
	private static GuiBuilder instance = null;
	private AnimationPanel animation;
	private PlayerManager player;
	private Stomach stomach;
	private Donkey donkey;
	private CyclusBar cyclusbar;
	private ArrayList<ImageLabel> dragObject;
	
	//Button zum Hintergrundswitchen mit seinen Zustaenden
	private JButton switchButton; 
	private ImageIcon nswitch;
	private ImageIcon oswitch;
	private ImageIcon pswitch;

	private boolean showIntro; //Boolean ob das Intro angezeigt werden soll

	//ArrayListen der DragObjekte, nach Kategorien getrennt
	private ArrayList<String> bassObjectPath;
	private ArrayList<String> beatsObjectPath;
	private ArrayList<String> harmonyObjectPath;
	private ArrayList<String> melodyObjectPath;
	private ArrayList<String> effectsObjectPath;
	private MaskPanel donkey_mask;

	// Startkoordinaten der DragObjekte-Images
	private int[][] startCoordinate;

	/**
	 * GuiBuilder-Konstruktor
	 * @param PlayerManager 
	 */
	private GuiBuilder(PlayerManager player) {
		this.showIntro = true; //Beim Starten des Programms wird das Intro angezeigt
		this.animation = new AnimationPanel();
		this.player = player;
		this.dragObject = new ArrayList<ImageLabel>();
		this.bassObjectPath = new ArrayList<String>();
		this.beatsObjectPath = new ArrayList<String>();
		this.harmonyObjectPath = new ArrayList<String>();
		this.melodyObjectPath = new ArrayList<String>();
		this.effectsObjectPath = new ArrayList<String>();
		this.donkey_mask = new MaskPanel("img/essential/MASKE.jpg"); 
		this.stomach = new Stomach(700,0, "img/essential/blase.png");
		this.cyclusbar = new CyclusBar(50, 210,8000);
		this.donkey = new Donkey(0, 129);
		nswitch = new ImageIcon("img/essential/switch.png");
		oswitch = new ImageIcon("img/essential/switchOver.png");
		pswitch = new ImageIcon("img/essential/switchPress.png");
		this.switchButton = new JButton(nswitch);
		switchButton.setRolloverIcon(oswitch);
		switchButton.setPressedIcon(pswitch);
		switchButton.setBounds(862,698, nswitch.getIconWidth(), nswitch.getIconHeight());
		
		//Erzeugen der DragObjekte
		createObjectPaths();
		createCoordinate();
		createDragObjects(CategoryType.BASS);
		createDragObjects(CategoryType.BEATS);
		createDragObjects(CategoryType.EFFECTS);
		createDragObjects(CategoryType.HARMONY);
		createDragObjects(CategoryType.MELODY);
		
		//Hinzufuegen der GUI-Elemente zum AnimatinPanel
		fillAnimationPanel();
		animation.add(donkey);
		animation.add(stomach);
		animation.add(cyclusbar);
		animation.add(switchButton);
		animation.add(donkey_mask);
	}

	/**
	 * Get-Instance-Methode fuer eine Instanz des Singletons
	 * @param playerManager 
	 * @return eine Instanz des GuiBuilders
	 */
	public static synchronized GuiBuilder getInstance(PlayerManager player) {
		// Falls noch kein Objekt vorhanden ist, wird dieses zunaechst erzeugt
		if (instance == null)
			instance = new GuiBuilder(player);
		return instance;
	}

	/**
	 * Erzeugt die DragObjects der jeweiligen Kategorie und fuegt sie zur ArrayList hinzu
	 * @param kat CategoryType
	 */
	private void createDragObjects(CategoryType kat) {
		ArrayList<Player> tmpPlayer = player.getPlayer(kat); //ArrayListe mit den Playern
		String tmpTitle;
		ImageLabel tmpLabel;
		ArrayList<String> tmpObjectPath = null;
		int posCoordinate = 0;
		
		if (kat == CategoryType.BASS) {
			tmpObjectPath = this.bassObjectPath;
			posCoordinate = 0;
		} else if (kat == CategoryType.BEATS) {
			tmpObjectPath = this.beatsObjectPath;
			posCoordinate = 3;
		} else if (kat == CategoryType.HARMONY) {
			tmpObjectPath = this.harmonyObjectPath;
			posCoordinate = 6;
		} else if (kat == CategoryType.MELODY) {
			tmpObjectPath = this.melodyObjectPath;
			posCoordinate = 9;
		} else if (kat == CategoryType.EFFECTS) {
			tmpObjectPath = this.effectsObjectPath;
			posCoordinate = 12;
		}

		//Hinzufuegen der DragObjects mit ihren jeweiligen Bildern, Titel, Kategorien 
		//und Startpositionen zur ArrayList dragObjects
		int i = 0;
		for (Player actPlayer : tmpPlayer) {
			tmpTitle = actPlayer.getTrack().getTitle();
			tmpLabel = new ImageLabel(tmpObjectPath.get(i), tmpTitle, kat,
					startCoordinate[posCoordinate + i][0],
					startCoordinate[posCoordinate + i][1], actPlayer);
			dragObject.add(tmpLabel);
			i++;
		}
	}

	/**
	 * Fuegt alle DragObjects zum AnimationPanel hinzu und setzt die jeweiligen
	 * Maus-Labels auf den Kategorienamen 
	 */
	private void fillAnimationPanel() {
		for (ImageLabel actObject : dragObject) {
			if (actObject.getCategory().compareTo(CategoryType.BASS)==0){
				actObject.setToolTipText("Bass");
			}
			if (actObject.getCategory().compareTo(CategoryType.BEATS)==0){
				actObject.setToolTipText("Beats");
			}
			if (actObject.getCategory().compareTo(CategoryType.HARMONY)==0){
				actObject.setToolTipText("Harmony");
			}
			if (actObject.getCategory().compareTo(CategoryType.MELODY)==0){
				actObject.setToolTipText("Melody");
			}
			if (actObject.getCategory().compareTo(CategoryType.EFFECTS)==0){
				actObject.setToolTipText("Effects");
			}
			
			animation.add(actObject);
		}
	}

	/**
	 * Laedt die Bildpfade der Dragobjects
	 */
	private void createObjectPaths() {
		bassObjectPath.add("img/essential/apfel1.png");
		bassObjectPath.add("img/essential/apfel2.png");
		bassObjectPath.add("img/essential/apfel3.png");

		beatsObjectPath.add("img/essential/kohlrabi1.png");
		beatsObjectPath.add("img/essential/kohlrabi2.png");
		beatsObjectPath.add("img/essential/kohlrabi3.png");

		harmonyObjectPath.add("img/essential/karotte1.png");
		harmonyObjectPath.add("img/essential/karotte2.png");
		harmonyObjectPath.add("img/essential/karotte3.png");

		melodyObjectPath.add("img/essential/sellerie1.png");
		melodyObjectPath.add("img/essential/sellerie2.png");
		melodyObjectPath.add("img/essential/sellerie3.png");

		effectsObjectPath.add("img/essential/birne1.png");
		effectsObjectPath.add("img/essential/birne2.png");
		effectsObjectPath.add("img/essential/birne3.png");
	}

	/**
	 * Festlegen der Startkoordinaten der DragObjects
	 */
	private void createCoordinate() {
		this.startCoordinate = new int[15][2];
		startCoordinate[0][0] = 45;
		startCoordinate[0][1] = 540;
		startCoordinate[1][0] = 135;
		startCoordinate[1][1] = 543;
		startCoordinate[2][0] = 91;
		startCoordinate[2][1] = 580;
		startCoordinate[3][0] = 240;
		startCoordinate[3][1] = 543;
		startCoordinate[4][0] = 326;
		startCoordinate[4][1] = 543;
		startCoordinate[5][0] = 281;
		startCoordinate[5][1] = 583;
		startCoordinate[6][0] = 432;
		startCoordinate[6][1] = 575;
		startCoordinate[7][0] = 520;
		startCoordinate[7][1] = 579;
		startCoordinate[8][0] = 474;
		startCoordinate[8][1] = 622;
		startCoordinate[9][0] = 620;
		startCoordinate[9][1] = 565;
		startCoordinate[10][0] = 711;
		startCoordinate[10][1] = 573;
		startCoordinate[11][0] = 674;
		startCoordinate[11][1] = 609;
		startCoordinate[12][0] = 810;
		startCoordinate[12][1] = 534;
		startCoordinate[13][0] = 900;
		startCoordinate[13][1] = 534;
		startCoordinate[14][0] = 859;
		startCoordinate[14][1] = 574;
	}
	

	/**
	 * Getter und Setter fuer alle GUI-Elemente
	 * @return das jeweilige GUI-Element
	 */
	public boolean getshowIntro(){
		return this.showIntro;
	}

	public AnimationPanel getAnimationPanel() {
		return this.animation;
	}
	
	public CyclusBar getCyclusBar(){
		return this.cyclusbar;
	}
	
	public Donkey getDonkey(){
		return this.donkey;
	}
	
	public Stomach getStomach(){
		return this.stomach;
	}
	
	public JButton getSwitchButton(){
		return switchButton;
	}
	

	public ArrayList<ImageLabel> getDragObjects() {
		return this.dragObject;
	}

	public MaskPanel getMaskPanel() {
		return this.donkey_mask;
	}
	
	/**
	 * Sucht ein DragObject ueber seinen Namen und gibt es zurueck 
	 * @param name des DragObjects
	 * @return das DragObject
	 */
	public ImageLabel getDragObjectByName(String name) {
		for (ImageLabel actLabel : dragObject) {
			if (actLabel.getName().compareTo(name) == 0)
				return actLabel;
		}
		return null;
	}
}
