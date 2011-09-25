package com.platzerworld.kegelverwaltung.vo;



/**
 * Ein Kontakt mit Geoinformationen.
 * 
 * @author David Müller, 2010 visionera gmbh
 */
public class WettkampfTO extends KeyValueTO{
	
	public long spielNr;
	public long heimId;
	public long gastId;
	public long klasseId;
	public String spielZeit;
	
	public String paarung1;
	public long paarung1ErgebnisHeim;
	public long paarung1ErgebnisGast;
	
	public String paarung2;
	public long paarung2ErgebnisHeim;
	public long paarung2ErgebnisGast;
	
	public String paarung3;
	public long paarung3ErgebnisHeim;
	public long paarung3ErgebnisGast;
	
	public String paarung4;
	public long paarung4ErgebnisHeim;
	public long paarung4ErgebnisGast;
	
	public String paarung5;
	public long paarung5ErgebnisHeim;
	public long paarung5ErgebnisGast;
	
	public String paarung6;
	public long paarung6ErgebnisHeim;
	public long paarung6ErgebnisGast;
	
	public String gesamtErgebnis;

	
	public WettkampfTO(){
		
	}
	
	
	public WettkampfTO(long key, long spielNr, long heimId, long gastId, long klasseId, String spielZeit,
			String paarung1, long paarung1ErgebnisHeim, long paarung1ErgebnisGast,
			String paarung2, long paarung2ErgebnisHeim, long paarung2ErgebnisGast,
			String paarung3, long paarung3ErgebnisHeim, long paarung3ErgebnisGast,
			String paarung4, long paarung4ErgebnisHeim, long paarung4ErgebnisGast,
			String paarung5, long paarung5ErgebnisHeim, long paarung5ErgebnisGast,
			String paarung6, long paarung6ErgebnisHeim, long paarung6ErgebnisGast, String gesamtErgebnis){
		super(key, "-");
		this.spielNr = spielNr;
		this.heimId = heimId;
		this.gastId = gastId;
		this.klasseId = klasseId;
		this.spielZeit = spielZeit;
		this.paarung1 = paarung1; this.paarung1ErgebnisHeim = paarung1ErgebnisHeim; this.paarung1ErgebnisGast = paarung1ErgebnisGast;
		this.paarung2 = paarung2; this.paarung2ErgebnisHeim = paarung2ErgebnisHeim; this.paarung2ErgebnisGast = paarung2ErgebnisGast;
		this.paarung3 = paarung3; this.paarung3ErgebnisHeim = paarung3ErgebnisHeim; this.paarung3ErgebnisGast = paarung3ErgebnisGast;
		this.paarung4 = paarung4; this.paarung4ErgebnisHeim = paarung4ErgebnisHeim; this.paarung4ErgebnisGast = paarung4ErgebnisGast;
		this.paarung5 = paarung5; this.paarung5ErgebnisHeim = paarung5ErgebnisHeim; this.paarung5ErgebnisGast = paarung5ErgebnisGast;
		this.paarung6 = paarung6; this.paarung6ErgebnisHeim = paarung6ErgebnisHeim; this.paarung6ErgebnisGast = paarung6ErgebnisGast;
		
		this.gesamtErgebnis = gesamtErgebnis;
	}
	
  /**
   * Zeigt an, ob der GeoKontakt bereits gespeichert wurde.
   * 
   * @return true, wenn Kontakt in Datenbank vorhanden.
   */
  public boolean istNeu() {
    return key == 0;
  }
}
