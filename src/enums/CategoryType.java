package enums;

/**
 * @author MerleHiort, MichaelSandritter BenjaminChristiani, JoergEinfeldt
 * Enum-Klasse fuer den Kategorie-Typ der DragObjects
 */
public enum CategoryType {
	
	BASS("bass"),
	BEATS("beats"),
	HARMONY("harmony"),
	MELODY("melody"),
	EFFECTS("effects");
	
	private String label;
	
	/**
	 * Kategorie-Enum-Konstruktor
	 * @param label
	 */
	private CategoryType (String label){
		this.label = label;
	}
	
	/**
	 * Gibt das Label des Enums zurueck
	 * @return
	 */
	public String getLabel(){
		return this.label;
	}
}
