package repository;
import java.io.File;
import java.util.ArrayList;
import enums.CategoryType;
import dataObjects.Track;

/**
 * Realisierung des Repositories als Singleton.
 * Alle verfuegbaren Soundspuren werden hier verwaltet.
 * 
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 *
 *	Bei den Soundpuren gibt es folgende Namenskonvention: Kategorie_TitelDerSoundspur.mp3
 */
public class Repository {
	
	private static Repository instance = null;
	private ArrayList<Track> tracks;
	
	/**
	 * Privater Konstruktor fuers Repository da als Singleton
	 * @param dirPath - Pfad zum Ordner mit allen Soundspuren
	 */
	private Repository(String dirPath){
		tracks = new ArrayList<Track>();
		fill(dirPath);
	}
	
	/**
	 * Getter fuer die Instanz des Repositories
	 * @param dirPath - Pfad zum Ordner mit allen Soundspuren
	 * @return Instanz des Repositories
	 */
	public static synchronized Repository getInstance(String dirPath){
		// Falls noch kein Objekt vorhanden ist, wird dieses zunaechst erzeugt
		if(instance == null)
			instance = new Repository(dirPath);
		return instance;
	}
	
	/**
	 * Fuellt das Repository mit allen Soundpuren in einem Ordner
	 * @param dirPath - Dateipfad zum Ordner
	 */
	private void fill(String dirPath){
		CategoryType kat;
		File f;
		File dir = new File(dirPath); 
		File[] files = dir.listFiles(); 
		
		for(int i = 0; i < files.length; i++){
			f = files[i];
			if(f.getName().endsWith("mp3")){
			kat = readCategory(f.getName());
			tracks.add(new Track(readTitle(f.getName()), f.getAbsolutePath(), kat));
			}
		}
	}
	
	/**
	 * Kategorien werden ausgelesen 
	 * @param title - Name der Datei
	 * @return Kategorietyp der Soundspur
	 */
	private CategoryType readCategory(String title){
		String s[] = title.split("_");
		
		if(s[0].compareTo("bass") == 0)
			return CategoryType.BASS;
		else if(s[0].compareTo("beats") == 0)
			return CategoryType.BEATS;
		else if(s[0].compareTo("harmony") == 0)
			return CategoryType.HARMONY;
		else if(s[0].compareTo("melody") == 0)
			return CategoryType.MELODY;
		else if(s[0].compareTo("effects") == 0)
			return CategoryType.EFFECTS;
		else
			return null;
	}
	
	/**
	 * Gibt den Namen der Soundspur zurueck
	 * @param title - Name der Datei
	 * @return Name der Spundspur
	 */
	private String readTitle(String title){
		String s[] = title.split("_");
		return s[1];
	}
	/**
	 * Gibt alle Tracks des Repositories zurueck
	 */
	public ArrayList<Track> getTracks() {
		return this.tracks;
	}
}
