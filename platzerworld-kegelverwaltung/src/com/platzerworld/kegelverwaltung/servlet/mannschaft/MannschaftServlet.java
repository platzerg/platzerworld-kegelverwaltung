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
import com.platzerworld.kegelverwaltung.dao.MannschaftDAO;
import com.platzerworld.kegelverwaltung.model.Mannschaft;
import com.platzerworld.kegelverwaltung.vo.MannschaftTO;

@SuppressWarnings("serial")
public class MannschaftServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new Mannschaft ");
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
				String mannschaft = checkNull(req.getParameter("mannschaft"));
				MannschaftDAO.INSTANCE.add(user.getUserId(), mannschaft);
			}else if(1 == typ){
				List<Mannschaft> mannschaften = MannschaftDAO.INSTANCE.getMannschaften(user.getUserId());
				
				List<MannschaftTO> mannschaftenTOs = new ArrayList<MannschaftTO>();
				for (Mannschaft mannschaft : mannschaften) {
					MannschaftTO klassTO = new MannschaftTO(mannschaft.getKlasseId(), mannschaft.getId(), mannschaft.getName());
					mannschaftenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(mannschaftenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<MannschaftTO>>(){}.getType();
				 
				 List<MannschaftTO> studentList = gson.fromJson(data, type);

			        for (MannschaftTO klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.name);
			        }
				 
				out.println(data);
		           
				
			}else if(2 == typ){
				int mannschaftId = Integer.parseInt(checkNull(req.getParameter("mannschaftid")));
				MannschaftDAO.INSTANCE.remove(mannschaftId);
			}
		} catch (Exception e) {
            String errMsg = stackToString(e);
            resp.setContentType("application/json");
            out.println("\n");
            out.println(" " + errMsg + "");
        }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		System.out.println("Creating new Mannschaft ");
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
				String mannschaft = checkNull(req.getParameter("mannschaft"));
				MannschaftDAO.INSTANCE.add(user.getUserId(), mannschaft);
			}else if(1 == typ){
				List<Mannschaft> klassen = MannschaftDAO.INSTANCE.getMannschaften(user.getUserId());
				
				List<MannschaftTO> klassenTOs = new ArrayList<MannschaftTO>();
				for (Mannschaft klasse : klassen) {
					MannschaftTO klassTO = new MannschaftTO(1, klasse.getId(), klasse.getName());
					klassenTOs.add(klassTO);
				}
				
				Gson gson = new Gson();
				data = gson.toJson(klassenTOs);
				System.out.println("jsonStudents = " + data);
				
				 Type type = new TypeToken<List<MannschaftTO>>(){}.getType();
				 
				 List<MannschaftTO> studentList = gson.fromJson(data, type);

			        for (MannschaftTO klasse : studentList) {
			            System.out.println("student.getName() = " + klasse.name);
			        }
				 
				out.println(data);
		           
				
			}else if(2 == typ){
				int mannschaftId = Integer.parseInt(checkNull(req.getParameter("mannschaftid")));
				MannschaftDAO.INSTANCE.remove(mannschaftId);
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
