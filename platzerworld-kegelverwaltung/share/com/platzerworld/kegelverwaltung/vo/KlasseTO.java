package com.platzerworld.kegelverwaltung.vo;



/**
 * Eine Klasse mit Klasseninformationen.
 * 
 * @author platzerg
 */
public class KlasseTO  extends KeyValueTO{
	
	/** id der DB Tabelle in der Kegelverwaltung-Datenbank. */
	public long id;
	
	public long klasseId;
  
  /** Bezeichnung der Klasse. */
	public String name;
	
	public KlasseTO(){
		super();
	}
	
	public KlasseTO(String value){
		this.name = value;
	}
	
	public KlasseTO(long key, long klasseId, String value){
		super(key, value);
		this.id = key;
		this.klasseId = klasseId;
		this.name = value;
	}
	
  /**
   * Zeigt an, ob die Klasse bereits gespeichert wurde.
   * 
   * @return true, wenn die Klasse in Datenbank vorhanden ist.
   */
  public boolean istNeu() {
    return id == 0;
  }
}
