package com.platzerworld.kegelverwaltung.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.platzerworld.kegelverwaltung.dao.KlasseDAO;
import com.platzerworld.kegelverwaltung.dao.MannschaftDAO;
import com.platzerworld.kegelverwaltung.dao.SpielerDAO;

@SuppressWarnings("serial")
public class ServletCreate extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		System.out.println("Creating new Klasse ");
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
			resp.sendRedirect("/Klassenverwaltung.jsp");
		}else if(1 == typ){
			String mannschaft = checkNull(req.getParameter("mannschaft"));
			String klasseZurMannschaft = (String) req.getParameter("klasseZurMannschaft");
			MannschaftDAO.INSTANCE.add(user.getUserId(), mannschaft, Long.parseLong(klasseZurMannschaft));
			resp.sendRedirect("/Mannschaftverwaltung.jsp");
		}else if(2 == typ){
			String spieler = checkNull(req.getParameter("spieler"));
			String mannschaftZumSpieler = (String) req.getParameter("mannschaftZumSpieler");
			SpielerDAO.INSTANCE.add(user.getUserId(), spieler, Long.parseLong(mannschaftZumSpieler));
			resp.sendRedirect("/Spielerverwaltung.jsp");
		}else{
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
