package com.platzerworld.kegelverwaltung.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.kegelverwaltung.common.persistence.EMFService;
import com.platzerworld.kegelverwaltung.model.Mannschaft;

public enum MannschaftDAO {

	INSTANCE;

	public List<Mannschaft> listKlasse() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Mannschaft m");
		List<Mannschaft> mannschaften = q.getResultList();
		return mannschaften;
	}

	public void add(String userId, String name) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Mannschaft mannschaft = new Mannschaft(new Long(0), userId, name, new Date());
			em.persist(mannschaft);
			em.close();
		}
	}
	
	public void add(String userId, String name, Long klasseId) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Mannschaft mannschaft = new Mannschaft(klasseId, userId, name, new Date());
			em.persist(mannschaft);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mannschaft> getMannschaften(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Mannschaft t where t.userId = :userId");
		q.setParameter("userId", userId);
		List<Mannschaft> mannschaften = q.getResultList();
		return mannschaften;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Mannschaft mannschaft = em.find(Mannschaft.class, id);
			em.remove(mannschaft);
		} finally {
			em.close();
		}
	}
}