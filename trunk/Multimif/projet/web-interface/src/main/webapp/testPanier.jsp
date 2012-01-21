<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ajout au panier</title>
</head>
<body>
<%
   String album = request.getParameter( "album" );
   session.setAttribute( "album", album );
%>
Element ajoute au panier.
<A HREF="index.jsp">Continuer</A>
</body>
</html>