package utilities;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Uhr die immer von 0 bis 8 Sekunden geht
 * @author MerleHiort, MichaelSandritter, BenjaminChristiani, JoergEinfeldt
 *
 */
public class Watch {
	
	private Timer t;
	private long startTime = 0;
	private long endTime = 8000;
	
	/**
	 * Konstruktor fuer die Watch
	 */
	public Watch(){
		t = new Timer();
	}
	
	/**
	 * Startet die Watch
	 */
	public void start(){
		
		startTime = getCurrentTime();
		//Alle 8 Sekunden wird die Run Methode aufgerugen
		t.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				//Startzeit wird auf aktuelle Systemzeit gesetzt
				startTime = getCurrentTime();
			}
		}, 0, endTime);
	}
	
	/**
	 * Gibt aktuelle Systemzeit zurueck
	 */
	private long getCurrentTime(){
		return System.currentTimeMillis();
	}
	/**
	 * Gibt Position der Watch zwischen 0 und 8 Sekunden zurueck
	 */
	public long getPos(){
		//Aktuelle Sytemzeit muss mit der Startzeit verrechnet werden um auf den Sekundenwert zwischen 0 und 8 zu kommen
		return (getCurrentTime() - startTime);
	}
	
}