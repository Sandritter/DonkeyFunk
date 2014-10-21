package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import gui.DonkeyGUI;
import gui.IntroPanel;
import builder.GuiBuilder;
import manager.PlayerManager;

import repository.Repository;

/** 
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * @sounds from ChristianBratschke
 * DONKEY FUNK 2012/2013
 * 
 * Bei DonkeyFunk handelet sich um ein wahnsinnig gutes Musikprogramm,
 * das erlaubt sich aus vorgefertigten Loops eigene Songkompositionen 
 * zu kreieren.
 * 
 * Das Programm bietet einen leichten Einstieg in die Welt der Eigenkompositionen
 * von Liedern und soll durch interaktive Benutzung den Spassfaktor ausreizen.
 * Dies geschieht durch unseren, vom Logo nachempfundenen Esel, der als Reciever
 * auf die abgepielten Songs regiert.
 * Laessig wie unser Esel ist, reagiert er je nach Songkategorie unterschiedlich
 * und gibt sich auf niedliche aber dennoch coole Art der Musik vollkommen hin.
 * 
 * Das Progromm erhaelt damit einen sehr spielerschen Charakter, ohne das 
 * Handwerk der Eigenkomposition zu sehr zu verdecken.
 * 
 * Wie wuneschen Ihnen viel Spass mit DonkeyFunk
 * 
 */
public class Starter {

	private static final int WIDTH = 1024; // Feste Breite des Fensters
	private static final int HEIGHT = 768; // Feste Hoehe des Fensters
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Instanziierung Repositorie mit allen Soundspuren
		Repository rep = Repository.getInstance("music");
		
		// Instanziierung der Player, die jeweils eine Soundspur verwalten
		PlayerManager player = PlayerManager.getInstance(rep);
		
		// Instanziierung der GUI-Manager
		final GuiBuilder guiBuilder = GuiBuilder.getInstance(player);
		
		// Instanziierung GUI
		final DonkeyGUI app = DonkeyGUI.getInstance(guiBuilder);

		final IntroPanel intro = new IntroPanel();
		app.init();
		app.setBackground(Color.BLACK);
		app.add(intro);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ermoegliche das Schliessen des Fensters
		app.setVisible(true); 
		app.setSize(WIDTH, HEIGHT); // setze Fentergroesse auf 800 x 600
		intro.addSkipCallBack(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				app.remove(intro);
				app.add(guiBuilder.getAnimationPanel());
				app.setSize(WIDTH, HEIGHT);
			}
		});
		app.setIgnoreRepaint(true); // das Zeichnen wird ausschliesslich durch die Animation gesteuert
		app.setResizable(false); // Fenstergroesse kann nicht veraendert werden
		app.pack();
		
		
	}
}
