package com.platzerworld.kegelverwaltung.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.kegelverwaltung.common.persistence.EMFService;
import com.platzerworld.kegelverwaltung.model.Spieler;

public enum SpielerDAO {

	INSTANCE;

	public List<Spieler> listKlasse() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Spieler m");
		List<Spieler> spieler = q.getResultList();
		return spieler;
	}

	public void add(String userId, String name) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Spieler spieler = new Spieler(userId, name, new Date());
			em.persist(spieler);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Spieler> getKlassen(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Spieler t");
		//q.setParameter("userId", "Platzer");
		List<Spieler> spieler = q.getResultList();
		return spieler;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Spieler spieler = em.find(Spieler.class, id);
			em.remove(spieler);
		} finally {
			em.close();
		}
	}
}