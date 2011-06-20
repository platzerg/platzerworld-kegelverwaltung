package com.platzerworld.kegelverwaltung.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.kegelverwaltung.common.persistence.EMFService;
import com.platzerworld.kegelverwaltung.model.Klasse;

public enum KlasseDAO {

	INSTANCE;

	public List<Klasse> listKlasse() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Klasse m");
		List<Klasse> klassen = q.getResultList();
		return klassen;
	}

	public void add(String userId, String name) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Klasse klasse = new Klasse(userId, name, new Date());
			em.persist(klasse);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Klasse> getKlassen(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Klasse t where t.userId = :userId");
		q.setParameter("userId", userId);
		List<Klasse> klassen = q.getResultList();
		return klassen;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Klasse klasse = em.find(Klasse.class, id);
			em.remove(klasse);
		} finally {
			em.close();
		}
	}
}