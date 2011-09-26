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
public class ServletEdit extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String idString = req.getParameter("id");
		long id = Long.parseLong(idString);
		
		User user = (User) req.getAttribute("user");
		if (user == null) {
			UserService userService = UserServiceFactory.getUserService();
			user = userService.getCurrentUser();
		}
		
		String typString = (String) req.getParameter("typ");
		int typ = Integer.parseInt(typString);
		
		if(0== typ){
			String name = checkNull(req.getParameter("klasse"));
			String klasseId = checkNull(req.getParameter("klasseid"));
			KlasseDAO.INSTANCE.update(id, Long.parseLong(klasseId), user.getUserId(), name);
			resp.sendRedirect("/Klassenverwaltung.jsp");
		}else if(1 == typ){
			MannschaftDAO.INSTANCE.remove(id);
			resp.sendRedirect("/Mannschaftverwaltung.jsp");
		}else if(2 == typ){
			SpielerDAO.INSTANCE.remove(id);
			resp.sendRedirect("/Spielerverwaltung.jsp");
		}else                   {
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
