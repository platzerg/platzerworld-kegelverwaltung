package com.platzerworld.kegelverwaltung.servlet;


import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.platzerworld.kegelverwaltung.dao.KlasseDAO;
import com.platzerworld.kegelverwaltung.dao.MannschaftDAO;
import com.platzerworld.kegelverwaltung.dao.SpielerDAO;


public class ServletRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		String idString = req.getParameter("id");
		long id = Long.parseLong(idString);
		
		
		String typString = (String) req.getParameter("typ");
		int typ = Integer.parseInt(typString);
		
		if(0== typ){
			KlasseDAO.INSTANCE.remove(id);
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
}