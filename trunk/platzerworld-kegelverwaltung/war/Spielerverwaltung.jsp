<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="com.platzerworld.kegelverwaltung.model.Spieler" %>
<%@ page import="com.platzerworld.kegelverwaltung.dao.SpielerDAO" %>
<%@ page import="com.platzerworld.kegelverwaltung.model.Mannschaft" %>
<%@ page import="com.platzerworld.kegelverwaltung.dao.MannschaftDAO" %>
<%@ page import="com.platzerworld.kegelverwaltung.model.Klasse" %>
<%@ page import="com.platzerworld.kegelverwaltung.dao.KlasseDAO" %>

<!DOCTYPE html>


<%@page import="java.util.ArrayList"%>

<html>
	<head>
		<title>Kegelklassen</title>
		<link rel="stylesheet" type="text/css" href="css/main.css"/>
		  <meta charset="utf-8"> 
	</head>
	<body>
<%
SpielerDAO dao = SpielerDAO.INSTANCE;
MannschaftDAO mannschaftdao = MannschaftDAO.INSTANCE;
KlasseDAO klassedao = KlasseDAO.INSTANCE;

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";

List<Spieler> spielerListe = new ArrayList<Spieler>();
List<Mannschaft> mannschaftenListe = new ArrayList<Mannschaft>();
List<Klasse> klassenListe = new ArrayList<Klasse>();
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
    spielerListe = dao.getSpieler(user.getUserId());
    klassenListe = klassedao.getKlassen(user.getUserId());
    mannschaftenListe = mannschaftdao.getMannschaften(user.getUserId());
}

    
%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">		
		<div style="float: left;" class="headline">Spieler verwalten</div>
		
		<div style="float: right;">
		<table>
		<tr> 
			<td>
				<a href="/Ligaverwaltung.jsp" >Ligaverwaltung</a>
			</td>
			<td>
			<a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%>
			
			</td>
		</tr> 
		</table>	
		</div>
			
		</div>
	</div>

<div style="clear: both;"/>	
You have a total number of <%= spielerListe.size() %>  spielerListe.

<table>
  <tr>
      <th>Klasse</th>
      <th>Done</th>
    </tr>

<% for (Spieler todo : spielerListe) {%>
<tr> 
<td>
<%=todo.getName()%>
</td>

<td>
<a class="done" href="/done?typ=2&id=<%=todo.getId()%>" >Done</a>
</td>
</tr> 
<%}
%>
</table>


<hr />

<div class="main">

<div class="headline">New Spieler</div>

<% if (user != null){ %> 

<form action="/new" method="post" accept-charset="utf-8">
<INPUT TYPE="hidden" NAME="typ" VALUE="2">
	<table>
		<tr>
			<td><label for="summary">Summary</label></td>
			<td><input type="text" name="spieler" id="spieler" size="65"/></td>
		</tr>
		
		<tr>

		<select name="mannschaftZumSpieler">
		
		<% for (Mannschaft mannschaft : mannschaftenListe) {%>
			<option value="<%out.print(mannschaft.getId());%>">  
			<%out.println(mannschaft.getName()); %>
			</option>  
		<%}%>
		</select>  
		
		</tr>
		
		<tr>
			<td colspan="2" align="right"><input type="submit" value="Create"/></td>
		</tr>
	</table>
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html>