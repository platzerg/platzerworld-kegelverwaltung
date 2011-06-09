package com.platzerworld.kegelverwaltung.vo;


/**
 * Eine Mannschaft mit Mannschafsinformationen
 * 
 * @author platzerg
 */
public class MannschaftTO extends KeyValueTO{

	/** id der DB Tabelle in der Kegelverwaltung-Datenbank. */
	public long id;

	/** FK (zur Klasse-Tabelle) der DB Tabelle in der Kegelverwaltung-Datenbank. */
	public long klasseId;
	
	/** FK (zur Verein-Tabelle) der DB Tabelle in der Kegelverwaltung-Datenbank. */
	public long vereinId;

	/** Name der Mannschaft. */
	public String name;

	/** Mannschaftsnummer (z.b. 1, 2, 3). */
	public long mannschaft;

	public MannschaftTO(){
		
	}
	
	public MannschaftTO(long klasseId, long key, String value){
		super(key, value);
		this.klasseId = klasseId;
		this.id = key;
		this.name = value;
	}
	
	/**
	 * Zeigt an, ob die Mannschaft bereits gespeichert wurde.
	 * 
	 * @return true, wenn die Mannschaft in der Datenbank vorhanden ist.
	 */
	public boolean istNeu() {
		return id == 0;
	}
}
