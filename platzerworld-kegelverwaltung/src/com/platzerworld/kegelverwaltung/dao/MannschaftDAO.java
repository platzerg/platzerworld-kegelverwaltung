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

	public void add(String name) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Mannschaft mannschaft = new Mannschaft(name, new Date());
			em.persist(mannschaft);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mannschaft> getKlassen(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Mannschaft t");
		//q.setParameter("userId", "Platzer");
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