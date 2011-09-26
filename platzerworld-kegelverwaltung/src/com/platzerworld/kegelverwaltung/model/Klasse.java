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
	private Long klasseId;
	private String userId;
	private String name;
	private Date lastChangedDate;
	
	
	public Klasse(String userId, long klasseId, String name, Date lastChangedDate){
		this.userId = userId;
		this.klasseId = klasseId;
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

	public String getUserId() {
		return userId;
	}
	

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getKlasseId() {
		if(null == klasseId) return new Long(0);
		return klasseId;
	}

	public void setKlasseId(Long klasseId) {
		this.klasseId = klasseId;
	}
	
}
