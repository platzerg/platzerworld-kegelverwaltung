package com.platzerworld.kegelverwaltung.servlet.mannschaft;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.platzerworld.kegelverwaltung.dao.KlasseDAO;
import com.platzerworld.kegelverwaltung.dao.SpielerDAO;
import com.platzerworld.kegelverwaltung.model.Klasse;
import com.platzerworld.kegelverwaltung.vo.KlasseTO;

@SuppressWarnings("serial")
public class MannschaftServlet extends HttpServlet {
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
				KlasseDAO.INSTANCE.add(klasse);
				resp.sendRedirect("/Klassenverwaltung.jsp");
			}else if(1 == typ){
				List<Klasse> klassen = KlasseDAO.INSTANCE.getKlassen(null);
				
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
				String spieler = checkNull(req.getParameter("spieler"));
				SpielerDAO.INSTANCE.add(spieler);
				resp.sendRedirect("/Spielerverwaltung.jsp");
			}else{
				resp.sendRedirect("/Ligaverwaltung.jsp");
			}
		} catch (Exception e) {
            String errMsg = stackToString(e);
            resp.setContentType("application/json");
            out.println("\n");
            out.println(" " + errMsg + "");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		String data="";
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		
		try {
			User user = (User) req.getAttribute("user");
			if (user == null) {
				UserService userService = UserServiceFactory.getUserService();
				user = userService.getCurrentUser();
			}
			
			List<Klasse> klassen = KlasseDAO.INSTANCE.getKlassen(null);
			
			Gson gson = new Gson();
			data = gson.toJson(klassen);
			System.out.println("jsonStudents = " + data);
			
			 Type type = new TypeToken<List<Klasse>>(){}.getType();
			 
			 List<Klasse> studentList = gson.fromJson(data, type);

		        for (Klasse klasse : studentList) {
		            System.out.println("student.getName() = " + klasse.getName());
		        }
			 
			out.println(data);
			
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
