package com.platzerworld.kegelverwaltung.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Mannschaft {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private String name;
	private Date lastChangedDate;	
	private Klasse klasse;
	private List<Spieler> spielerList = new ArrayList<Spieler>();
	
	public Mannschaft(String userId, String name, Date lastChangedDate){
		this.userId = userId;
		this.name = name;
		this.lastChangedDate = lastChangedDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastChangedDate() {
		return lastChangedDate;
	}

	public void setLastChangedDate(Date lastChangedDate) {
		this.lastChangedDate = lastChangedDate;
	}

	public Long getId() {
		return id;
	}
		
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	@ManyToOne
	public Klasse getKlasse() {
		return klasse;
	}

	public void setKlasse(Klasse klasse) {
		this.klasse = klasse;
	}
	
	@OneToMany
	public List<Spieler> getJobList() {
		return this.spielerList;
	}

	public void setJobList(List<Spieler> nickName) {
		this.spielerList = nickName;
	}
	
}
