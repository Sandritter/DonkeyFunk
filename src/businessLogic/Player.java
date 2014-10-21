package businessLogic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import dataObjects.Track;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;

/**
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * Player-Klasse, in welcher ein Player erzeugt wird, der auf dem Minim-Player basiert
 * und Tracks laden und abspielen kann.
 */
public class Player {
	Timer stopTimer;
	Minim minim;
	AudioPlayer player;
	AudioInput input;
	Track song;
	boolean playing = false;

	/**
	 * Player-Konstruktor
	 * @param song aktueller zu ladender Track
	 */
	public Player(Track song) {
		this.stopTimer = new Timer();
		this.song = song;
		minim = new Minim(this);
		player = minim.loadFile(song.getPath());
		input = minim.getLineIn();
	}

	/**
	 * Play-Methode, welche den Track langsam einblendet und abspielt
	 */
	public void play() {
		player.shiftGain(-60, 30, 0); //Fade-in des Tracks
		playing = true;
		player.play(0);
	}

	/**
	 * Stop-Methode, welche den Track langsam ausblendet und dann stoppt
	 */
	public void stop() {
		player.shiftGain(30, -60, 3000); //Fade-out des Tracks
		stopTimer.schedule(new TimerTask(){
			public void run(){
				if (playing != false) {
					player.pause();
					playing = false;
				}
			}	
		}, 3000); //nach 3000ms wird der Track gestoppt
	}

	/**
	 * Gibt aus, welcher Track gerade geladen wurde
	 * @param fileName
	 * @return
	 */
	public String sketchPath(String fileName) {
		System.out.println("SketchPath: " + fileName);
		return fileName;
	}

	/**
	 * Erzeugt InputStream des geladenen Tracks
	 * @param fileName
	 * @return
	 */
	public InputStream createInput(String fileName) {
		InputStream in2;
		try {
			in2 = new FileInputStream(fileName);
			System.out.println("InputStream: created! " + fileName);
			return in2;
		} catch (Exception ex) {
			System.out.println("Error Catch Triggered: " + ex);
			in2 = null;
		}
		return in2;
	}
	
	/**
	 * Fragt ab, ob Player etwas abspielt
	 * @return
	 */
	public boolean isPlaying() {
		return this.playing;
	}
	
	/**
	 * Gibt den aktuellen Track zuruecl
	 * @return
	 */
	public Track getTrack() {
		return this.song;
	}

}
