package manager;
import java.util.ArrayList;

import dataObjects.Track;

import repository.Repository;
import enums.CategoryType;

import businessLogic.Player;

/**
 * Erstellt die Player fuer alle Soundspuren und stellt sie bereit
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 *
 */
public class PlayerManager {
	
	private static PlayerManager instance = null;
	private Repository rep;
	private ArrayList<Player> bassPlayer;
	private ArrayList<Player> beatsPlayer;
	private ArrayList<Player> harmonyPlayer;
	private ArrayList<Player> melodyPlayer;
	private ArrayList<Player> effectsPlayer;
	
	/**
	 * Privater Konstruktor fuer Playermanager, da er als Singleton umgesetzt ist
	 * @param rep
	 */
	private PlayerManager(Repository rep){
		this.rep = rep;
		bassPlayer = new ArrayList<Player>();
		beatsPlayer = new ArrayList<Player>();
		harmonyPlayer = new ArrayList<Player>();
		melodyPlayer = new ArrayList<Player>();
		effectsPlayer = new ArrayList<Player>();
		createPlayer();
	}
	
	/**
	 * Gibt die Instanz des Playermanagers zurueck
	 * @param rep - Repository mit allen Tracks
	 * @return Instanz des Playermanagers
	 */
	public static synchronized PlayerManager getInstance(Repository rep){
		// Falls noch kein Objekt vorhanden ist, wird dieses zunaechst erzeugt.
		if(instance == null)
			instance = new PlayerManager(rep);
				
		return instance;
	}
	
	/**
	 * Erzeugt fuer alle Tracks im Repository einen Player
	 * Weisst sie nach Kategorien entsprechendem Array zu
	 */
	private void createPlayer(){
		CategoryType kat;
		Track t;
		ArrayList<Track> tracks = rep.getTracks();
		
		for(int i = 0; i < tracks.size(); i++){
			t = tracks.get(i);
			kat = t.getKat();
			
			switch(kat) {
				case BASS:
					bassPlayer.add(new Player(t));
					break;
				case BEATS:
					beatsPlayer.add(new Player(t));
					break;
				case HARMONY:
					harmonyPlayer.add(new Player(t));
					break;
				case MELODY:
					melodyPlayer.add(new Player(t));
					break;
				case EFFECTS:
					effectsPlayer.add(new Player(t));
					break;
			}
		}
	}
	
	/**
	 * Gibt List mit entsprechenden Playern fuer Kategorie zurueck
	 * @param kat - Kategorie der gesuchten Player
	 * @return Alle Player mit Kategorie kat
	 */
	public ArrayList<Player> getPlayer(CategoryType kat){
		if(kat == CategoryType.BASS)
			return bassPlayer;
		else if(kat == CategoryType.BEATS)
			return beatsPlayer;
		else if(kat == CategoryType.HARMONY)
			return harmonyPlayer;
		else if(kat == CategoryType.MELODY)
			return melodyPlayer;
		else if(kat == CategoryType.EFFECTS)
			return effectsPlayer;
		else
			return null;
	}
	
	

}
