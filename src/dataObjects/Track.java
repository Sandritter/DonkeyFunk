package dataObjects;

import enums.CategoryType;
/**
 * Trackobjekt welches vom Player zum Abspielen benutzt wird
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 */
public class Track {
	private String title;
	private String path;
	private CategoryType kat;
	
	/**
	 * Konstruktor fuer den Track
	 * @param title - Titel des Tracks
	 * @param path - Pfad zur mp3-Datei
	 * @param kat - Kategorie des Tracks
	 */
	public Track(String title, String path, CategoryType kat){
		this.title = title;
		this.path = path;
		this.kat = kat;
	}

	/**
	 * Getter fuer den Titel
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Setter fuer den Titel
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter fuer den Dateipfad
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Setter fuer den Dateipfad
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Getter fuer die Kategorie
	 */
	public CategoryType getKat() {
		return kat;
	}

	/**
	 * Setter fuer die Kategorie
	 */
	public void setKat(CategoryType kat) {
		this.kat = kat;
	}
	
	/**
	 * Equals-Methode um zwei Tracks zu vergleichen
	 */
	public boolean equals(Object o){
		Track track = (Track) o;
		if(this.title.compareTo(track.getTitle())==0 
				&& this.path.compareTo(track.getPath())==0 && this.kat == track.getKat())
			return true;
		else
			return false;
	}
	
	/**
	 * Clone-Methode um einen Track zu kopieren
	 */
	public Object clone(){
		Track trackClone = new Track(title, path, kat);
		return trackClone;
	}
	
	/**
	 * Informationen zum Track als String
	 */
	public String toString(){
		return title + " (" + kat.getLabel() + ") " + path; 
	}
}
