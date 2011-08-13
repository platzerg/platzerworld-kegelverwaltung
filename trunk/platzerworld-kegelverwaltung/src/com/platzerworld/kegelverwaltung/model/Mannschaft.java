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
	private Long klasseId;
	private String userId;
	private String name;
	private Date lastChangedDate;
	
	public Mannschaft(Long klasseId, String userId, String name, Date lastChangedDate){
		this.klasseId = klasseId;
		this.userId = userId;
		this.name = name;
		this.lastChangedDate = lastChangedDate;
	}
	
	public Long getKlasseId() {
		return klasseId;
	}


	public void setKlasseId(Long klasseId) {
		this.klasseId = klasseId;
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
	
	
}
