package com.platzerworld.kegelverwaltung.vo;



/**
 * Eine Klasse mit Klasseninformationen.
 * 
 * @author platzerg
 */
public class KlasseTO  extends KeyValueTO{
	
	private static final long serialVersionUID = -268797146622943521L;

	public String klasseId;
	
	public String schub;
  
  /** Bezeichnung der Klasse. */
	public String name;
	
	public KlasseTO(){
		super();
	}
	
	public KlasseTO(String value){
		this.name = value;
	}
	
	public KlasseTO(long key, String klasseId, String value, String schub){
		super(key, value);
		this.klasseId = klasseId;
		this.name = value;
		this.schub = schub;
	}
	
  /**
   * Zeigt an, ob die Klasse bereits gespeichert wurde.
   * 
   * @return true, wenn die Klasse in Datenbank vorhanden ist.
   */
  public boolean istNeu() {
    return key == 0;
  }
}
