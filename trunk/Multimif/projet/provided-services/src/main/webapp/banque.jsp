<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Banque: identifiants</title>
</head>
<body>
	<h1>Saisie des identifiants bancaires</h1>
	<p>Normalement, on attend ici un formulaire type carte de crédit.
		On saisira juste le numéro de compte.</p>
	<%
		String redirectUrl = request.getParameter("redirectUrl");
		String actionURL = "services/rest/banque/debit";
	%>
	<form action="<%=actionURL%>" method="post">
		<p>
			Compte n°<input type="text" name="numero" size="9" /><br />
			Débiter de <input type="text" name="combien" value="<%= request.getParameter("combien") %>"/><br/> 
			<input type="submit" value="Envoyer" />
			<input type="hidden" name="redirectUrl" value="<%= redirectUrl %>"  />
			<input type="hidden" name="confirmeA" value="<%= request.getParameter("confirmeA") %>"  />
		</p>
	</form>
</body>
</html>