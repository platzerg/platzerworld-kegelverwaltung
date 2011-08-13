package com.platzerworld.kegelverwaltung.vo;


/**
 * Ein Kontakt mit Geoinformationen.
 * 
 * @author David Müller, 2010 visionera gmbh
 */
public class SpielerTO extends KeyValueTO{

	/** id der DB Tabelle in der Amando Datenbank. */
	public long id;

	/** id der DB Tabelle in der Amando Datenbank. */
	public long mannschaftId;
	
	/** Passnummer der DB Tabelle in der Amando Datenbank. */
	public long passNr;

	/** Name des Besitzers der Mobilnummer. */
	public String name;
	
	/** Name des Besitzers der Mobilnummer. */
	public String vorname;
	
	/** Geburtsdatum des Besitzers der Mobilnummer. */
	public String gebDatum;
	
	/** latidute */
	public int latidute;
	
	/** longitude */
	public int longitude;

	public SpielerTO(){
		
	}
	
	public SpielerTO(long key, long mannschaftId, String value){
		super(key, value);
		this.id = key;
		this.name = value;
		this.mannschaftId = mannschaftId;
	}

	public SpielerTO(long mannschaftId, long passNr, String name, String vorname, String gebDatum, int latidute, int longitude){
		this.mannschaftId = mannschaftId;
		this.passNr = passNr;
		this.name = name;
		this.vorname = vorname;
		this.gebDatum = gebDatum;
		this.latidute = latidute;
		this.longitude = longitude;
	}
	
	public boolean istNeu() {
		return id == 0;
	}
}
