package com.platzerworld.kegelverwaltung.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.platzerworld.kegelverwaltung.dao.KlasseDAO;

@SuppressWarnings("serial")
public class LigaVerwaltungServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating new Klasse ");
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}

		String typ = checkNull(req.getParameter("typ"));
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		
		String typ = req.getParameter("typ");
		
		if("0".equals(typ)){
			resp.sendRedirect("/Klassenverwaltung.jsp");
		}else if("1".equals(typ)){
			resp.sendRedirect("/Mannschaftverwaltung.jsp");
		}else if("2".equals(typ)){
			resp.sendRedirect("/Spielerverwaltung.jsp");
		}else {
			resp.sendRedirect("/Ligaverwaltung.jsp");
		}
		
	}

	private String checkNull(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
}
