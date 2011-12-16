<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.Enumeration"%>
<%@page import="tiw5.fourni.banque.RedirectBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Redirection</title>
</head>
<body>
<h1>Confirmation de débit</h1>
<%
	RedirectBean redBean = (RedirectBean) request.getAttribute("redirectbean");
%>
<p>Il reste sur le compte <%= redBean.compte.getNumero() %>: <%= redBean.compte.getValeur() %></p>
<% 
String url = redBean.url;
if (url.contains("?")) {
	url += "&";
} else {
	url += "?";
}
url += "transactionId="+redBean.trId;
%>
<p><a href="<%= url %>">Retour au site de vente</a></p>

</body>
</html>