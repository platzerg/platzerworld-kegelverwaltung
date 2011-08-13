package com.platzerworld.kegelverwaltung.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Spieler {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long mannschaftId;
	private String userId;
	private String name;
	private Date lastChangedDate;
	
	public Spieler(String userId, Long mannschaftId, String name, Date lastChangedDate){
		this.userId = userId;
		this.mannschaftId = mannschaftId;
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

	public Long getMannschaftId() {
		return mannschaftId;
	}

	public void setMannschaftId(Long mannschaftId) {
		this.mannschaftId = mannschaftId;
	}
	
	

}
