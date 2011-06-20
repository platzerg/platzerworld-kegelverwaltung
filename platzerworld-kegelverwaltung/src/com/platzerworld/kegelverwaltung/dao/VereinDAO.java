package com.platzerworld.kegelverwaltung.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.platzerworld.kegelverwaltung.common.persistence.EMFService;
import com.platzerworld.kegelverwaltung.model.Verein;

public enum VereinDAO {

	INSTANCE;

	public List<Verein> listVerein() {
		EntityManager em = EMFService.get().createEntityManager();
		// Read the existing entries
		Query q = em.createQuery("select m from Verein m");
		List<Verein> klassen = q.getResultList();
		return klassen;
	}

	public void add(String userId, String name) {
		synchronized (this) {
			EntityManager em = EMFService.get().createEntityManager();
			Verein klasse = new Verein(userId, name, new Date());
			em.persist(klasse);
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Verein> getVereine(String userId) {
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select t from Verein t where t.userId = :userId");
		q.setParameter("userId", userId);
		List<Verein> klassen = q.getResultList();
		return klassen;
	}

	public void remove(long id) {
		EntityManager em = EMFService.get().createEntityManager();
		try {
			Verein klasse = em.find(Verein.class, id);
			em.remove(klasse);
		} finally {
			em.close();
		}
	}
}