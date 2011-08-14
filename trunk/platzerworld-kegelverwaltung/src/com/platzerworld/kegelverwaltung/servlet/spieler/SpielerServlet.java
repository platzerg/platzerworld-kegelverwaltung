package com.platzerworld.kegelverwaltung.servlet.spieler;

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
import com.platzerworld.kegelverwaltung.dao.SpielerDAO;
import com.platzerworld.kegelverwaltung.model.Spieler;
import com.platzerworld.kegelverwaltung.vo.SpielerTO;

@SuppressWarnings("serial")
public class SpielerServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
				String klasse = checkNull(req.getParameter("spieler"));
				SpielerDAO.INSTANCE.add(user.getUserId(), klasse, null);
			}else if(1 == typ){
				List<Spieler> spielerList = SpielerDAO.INSTANCE.getSpieler(user.getUserId());
				
				List<SpielerTO> klassenTOs = new ArrayList<SpielerTO>();
				for (Spieler spieler : spielerList) {
					SpielerTO klassTO = new SpielerTO(spieler.getId(), spieler.getMannschaftId(), spieler.getName());
					klassenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(klassenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<SpielerTO>>(){}.getType();
				 
				 List<SpielerTO> studentList = gson.fromJson(data, type);

			        for (SpielerTO klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.name);
			        }
				 
				out.println(data);
		           
				
			}else if(2 == typ){
				int spielerId = Integer.parseInt(checkNull(req.getParameter("spielerid")));
				SpielerDAO.INSTANCE.remove(spielerId);
			}
		} catch (Exception e) {
            String errMsg = stackToString(e);
            resp.setContentType("application/json");
            out.println("\n");
            out.println(" " + errMsg + "");
        }
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
				String klasse = checkNull(req.getParameter("spieler"));
				SpielerDAO.INSTANCE.add(user.getUserId(), klasse, null);
			}else if(1 == typ){
				List<Spieler> spielerList = SpielerDAO.INSTANCE.getSpieler(user.getUserId());
				
				List<SpielerTO> klassenTOs = new ArrayList<SpielerTO>();
				for (Spieler spieler : spielerList) {
					SpielerTO klassTO = new SpielerTO(spieler.getId(), spieler.getMannschaftId(), spieler.getName());
					klassenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(klassenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<SpielerTO>>(){}.getType();
				 
				 List<SpielerTO> studentList = gson.fromJson(data, type);

			        for (SpielerTO klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.name);
			        }
				 
				out.println(data);
		           
				
			}else if(2 == typ){
				int spielerId = Integer.parseInt(checkNull(req.getParameter("spielerid")));
				SpielerDAO.INSTANCE.remove(spielerId);
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
