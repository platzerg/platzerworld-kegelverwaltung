package com.platzerworld.kegelverwaltung.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Klasse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String klasseId;
	private String userId;
	private String name;
	private String schub;
	private Date lastChangedDate;
	
	
	public Klasse(String userId, String klasseId, String name, Date lastChangedDate, String schub){
		this.userId = userId;
		this.klasseId = klasseId;
		this.name = name;
		this.lastChangedDate = lastChangedDate;
		this.schub = schub;
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

	public String getUserId() {
		return userId;
	}
	

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getKlasseId() {
		if(null == klasseId) return "";
		return klasseId;
	}

	public void setKlasseId(String klasseId) {
		this.klasseId = klasseId;
	}

	public String getSchub() {
		return schub;
	}

	public void setSchub(String schub) {
		this.schub = schub;
	}
}
