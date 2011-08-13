package com.platzerworld.kegelverwaltung.servlet.klasse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platzerworld.kegelverwaltung.common.persistence.EMFService;
import com.platzerworld.kegelverwaltung.dao.KlasseDAO;
import com.platzerworld.kegelverwaltung.model.Klasse;
import com.platzerworld.kegelverwaltung.model.Mannschaft;
import com.platzerworld.kegelverwaltung.vo.KlasseTO;

@SuppressWarnings("serial")
public class KlassenServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new Klasse ");
		String data="";
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		try {
			User user = (User) req.getAttribute("user");
			if (user == null) {
				UserService userService = UserServiceFactory.getUserService();
				user = userService.getCurrentUser();
			}
			
			String typString = (String) req.getParameter("typ");
			int typ = Integer.parseInt(typString);
			
			if(0== typ){
				String klasse = checkNull(req.getParameter("klasse"));
				KlasseDAO.INSTANCE.add(user.getUserId(), klasse);
			}else if(1 == typ){
				List<Klasse> klassen = KlasseDAO.INSTANCE.getKlassen(user.getUserId());
				
				List<KlasseTO> klassenTOs = new ArrayList<KlasseTO>();
				for (Klasse klasse : klassen) {
					KlasseTO klassTO = new KlasseTO(klasse.getId(), klasse.getName());
					klassenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(klassenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<Klasse>>(){}.getType();
				 
				 List<Klasse> studentList = gson.fromJson(data, type);

			        for (Klasse klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.getName());
			        }
				 
				out.println(data);		           
				
			}else if(2 == typ){
				int klasseId = Integer.parseInt(checkNull(req.getParameter("klasseid")));
				KlasseDAO.INSTANCE.remove(klasseId);
			}
			
		} catch (Exception e) {
            String errMsg = stackToString(e);
            resp.setContentType("application/json");
            out.println("\n");
            out.println(" " + errMsg + "");
        }
	}
	
	public void setUp() throws Exception {
		
		EntityManager em = EMFService.get().createEntityManager();
		
		// Begin a new local transaction so that we can persist a new entity
		em.getTransaction().begin();

		// Read the existing entries
		Query q = em.createQuery("select m from Klasse m");
		// Persons should be empty

		// Do we have entries?
		boolean createNewEntries = (q.getResultList().size() == 0);

		// No, so lets create new entries
		if (createNewEntries) {
			Klasse klasse = new Klasse("", "", new Date());
			em.persist(klasse);
			for (int i = 0; i < 40; i++) {
				Mannschaft mannschaft = new Mannschaft(new Long(0), "", "", new Date());
				em.persist(mannschaft);
				// Now persists the family person relationship
				//klasse.getMannschaften().add(mannschaft);
				em.persist(mannschaft);
				em.persist(klasse);
			}
		}

		// Commit the transaction, which will cause the entity to
		// be stored in the database
		em.getTransaction().commit();

		// It is always good practice to close the EntityManager so that
		// resources are conserved.
		em.close();

	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		System.out.println("Creating new Klasse ");
		String data="";
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		try {
			User user = (User) req.getAttribute("user");
			if (user == null) {
				UserService userService = UserServiceFactory.getUserService();
				user = userService.getCurrentUser();
			}
			
			String typString = (String) req.getParameter("typ");
			int typ = Integer.parseInt(typString);
			
			if(0== typ){
				String klasse = checkNull(req.getParameter("klasse"));
				KlasseDAO.INSTANCE.add(user.getUserId(), klasse);
			}else if(1 == typ){
				List<Klasse> klassen = KlasseDAO.INSTANCE.getKlassen(user.getUserId());
				
				List<KlasseTO> klassenTOs = new ArrayList<KlasseTO>();
				for (Klasse klasse : klassen) {
					KlasseTO klassTO = new KlasseTO(klasse.getId(), klasse.getName());
					klassenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(klassenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<KlasseTO>>(){}.getType();
				 
				 List<KlasseTO> studentList = gson.fromJson(data, type);

			        for (KlasseTO klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.name);
			        }
				 
				out.println(data);		           
				
			}else if(2 == typ){
				int klasseId = Integer.parseInt(checkNull(req.getParameter("klasseid")));
				KlasseDAO.INSTANCE.remove(klasseId);
			}
			
		} catch (Exception e) {
            String errMsg = stackToString(e);
            resp.setContentType("application/json");
            out.println("\n");
            out.println(" " + errMsg + "");
        }
	}

	/**
     * This method helps us get the stack trace as a string,
     * in most cases you will want it in your 'util' pkg.
     * @param e
     * @return the stack trace of an exception
     */
    private String stackToString(Exception e) {
        if (e != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                return sw.toString() + "\n";
            } catch (Exception ex) {
                return "Could not show the stackTrace in String\n"
                        + ex.getMessage();
            }
        }
        return "";
    }

    
	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
