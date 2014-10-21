package gui;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import businessLogic.Player;
import builder.GuiBuilder;
import utilities.MouseListen;
import utilities.MouseMotion;
import utilities.Watch;

@SuppressWarnings("serial")
/**
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 *
 * class DonkeyGUI ist ein JFrame, der alle Gui Componenten beinhaltet,
 * die PlayerPositionen bereitstellt, MouseListener erzeugt und den DragObjekten mitgibt und und und...
 */
public class DonkeyGUI extends JFrame {

	private Watch watch;
	private final long loopEnd = 8000; // Laenge der Soundspuren
	private GuiBuilder guiBuilder;
	private ArrayList<Player> plList;

	private Player pos1;
	private Player pos2;
	private Player pos3;
	private Player pos4;
	private Player pos5;
	private Player pos6;

	private Cursor cursor1;
	private Cursor cursor2;
	private Cursor cursor3;
	private Cursor cursor4;

	// Repositorie wird als Objekt abgelegt
	private static DonkeyGUI instance = null;

	public static synchronized DonkeyGUI getInstance(GuiBuilder guiBuilder) {
		// Falls noch kein Objekt vorhanden ist, wird dieses zunaechst erzeugt
		if (instance == null)
			instance = new DonkeyGUI(guiBuilder);
		return instance;
	}

	/**
	 * DonkeyGuiKonstruktor
	 * @param guiBuilder enthaelt die erzeugten Gui Elemente
	 */
	private DonkeyGUI(GuiBuilder guiBuilder) {
		this.guiBuilder = guiBuilder;
		this.watch = new Watch();
		plList = new ArrayList<Player>();

		plList.add(pos1);
		plList.add(pos2);
		plList.add(pos3);
		plList.add(pos4);
		plList.add(pos5);
		plList.add(pos6);
	}

	/**
	 * initialisiert CurserImages, fuegt DragObjekten MouseListener hinzu
	 */
	public void init() {

		play();

		try {
			BufferedImage img1 = ImageIO.read(new File("img/curser/curser_drag.png"));
			BufferedImage img2 = ImageIO.read(new File("img/curser/curser_drop.png"));
			BufferedImage img3 = ImageIO.read(new File("img/curser/curser_delete.png"));
			BufferedImage img4 = ImageIO.read(new File("img/curser/curser_full.png"));
			Point hotSpot = new Point(16, 10);
			cursor1 = Toolkit.getDefaultToolkit().createCustomCursor(img1,hotSpot, "drag Cursor");
			cursor2 = Toolkit.getDefaultToolkit().createCustomCursor(img2,hotSpot, "drop Cursor");
			cursor3 = Toolkit.getDefaultToolkit().createCustomCursor(img3,hotSpot, "delete Cursor");
			cursor4 = Toolkit.getDefaultToolkit().createCustomCursor(img4,hotSpot, "full Cursor");
			setCursor(cursor1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<ImageLabel> tmp = guiBuilder.getDragObjects();
		MouseListen ml = new MouseListen(plList, guiBuilder);
		MouseMotion mm = new MouseMotion(guiBuilder);
		for (ImageLabel actObject : tmp) {
			actObject.addMouseListener(ml);
			actObject.addMouseMotionListener(mm);
		}

		// Hintergrundswitchen
		guiBuilder.getSwitchButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (guiBuilder.getAnimationPanel().getBackgroundPath() == "img/essential/bg.jpg") {
					guiBuilder.getAnimationPanel().setBackgroundImage(
							"img/essential/bg_strand.jpg");
				} else {
					guiBuilder.getAnimationPanel().setBackgroundImage(
							"img/essential/bg.jpg");
				}
			}
		});
	}

	/**
	 * spielt bei jedem Start eines neuen Cyclus die ausgewaehlten Tracks
	 */
	public void play() {

		guiBuilder.getCyclusBar().startTimer();
		watch.start();

		final Timer t = new Timer();
		// Muss so umständlich sein wegen final
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				for (int i = 0; i < plList.size(); i++) {
					if (plList.get(i) != null) {
						plList.get(i).play();
					}
				}
			}

		}, 0, loopEnd);
	}

	/**
	 * 
	 * @param curser - erhaelt einen String mit Namen des Cursors der gesetzt werden soll
	 */
	public void setCurser(String curser) {
		if (curser.compareTo("drag") == 0) {
			setCursor(cursor1);
		}
		if (curser.compareTo("drop") == 0) {
			setCursor(cursor2);
		}
		if (curser.compareTo("delete") == 0) {
			setCursor(cursor3);
		}
		if (curser.compareTo("full") == 0) {
			setCursor(cursor4);
		}
	}
	
	/**
	 * 
	 * @return - aktuelle Position der Watch
	 */
	public long getWatchPos(){
		return watch.getPos();
	}

}