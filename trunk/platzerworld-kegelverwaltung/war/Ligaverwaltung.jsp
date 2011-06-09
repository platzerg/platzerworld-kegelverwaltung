<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

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

UserService userService = UserServiceFactory.getUserService();
User user = userService.getCurrentUser();

String url = userService.createLoginURL(request.getRequestURI());
String urlLinktext = "Login";
            
if (user != null){
    url = userService.createLogoutURL(request.getRequestURI());
    urlLinktext = "Logout";
}
    
%>
	<div style="width: 100%;">
		<div class="line"></div>
		<div class="topLine">
			<div style="float: left;" class="headline">Kegelverwaltung</div>
			<div style="float: right;"><a href="<%=url%>"><%=urlLinktext%></a> <%=(user==null? "" : user.getNickname())%></div>
		</div>
	</div>

<hr />

<div class="main">


<% if (user != null){ %> 

<form  method="post" accept-charset="utf-8">
	<table>
		<tr>
			<td ><a href="/Ligaverwaltung?typ=0" >Verwaltung von Klassen</a></td>
		</tr>
		<tr>
			<td><a href="/Ligaverwaltung?typ=1" >Verwaltung von Mannschaften</a></td>
		</tr>
		<tr>
			<td><a href="/Ligaverwaltung?typ=2" >Verwaltung von Spielern</a></td>
		</tr>
		
	</table>
	
</form>

<% }else{ %>

Please login with your Google account

<% } %>
</div>
</body>
</html>