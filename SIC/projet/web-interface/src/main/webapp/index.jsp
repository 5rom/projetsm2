<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Système collaboratif</title>
</head>
<body>
<center><h1>Page d'accueil du système collaboratif</h1></center>
<br>
<h2>--- Expert CAO ---</h2>
<!--  Menu CAO -->
<br>
<form action="menucao.jsp" method="post">
<input type="submit" name="bouton" value="Accéder au menu CAO">
</form>
<br>
<br>
<h2>--- Expert FAO ---</h2>
<!--  Menu FAO -->
<br>
<form action="menufao.jsp" method="post">
<input type="submit" name="bouton" value="Accéder au menu FAO">
</form>
<br>
<br>
<h2>--- Mapping entre les ontologies des deux domaines ---</h2>
<!--  Menu FAO -->
<br>
<form action="ServletOWLMapping" method="post">
<input type="submit" name="bouton" value="Lancer le mapping CAO FAO des ontologies">
</form>
<br>
<br>
<h2>--- Description des web services utilsés ---</h2>
<br>
<a href="http://localhost:8080/web-interface/services">Description des services avec les fichiers WSDL</a>
<br><br><br><br>
<center>R&eacute;alis&eacute; par <a href="mailto:sebastien.faure3@gmail.com">S&eacute;bastien FAURE</a> et <a href="mailto:crescence.david@gmail.com">David CRESCENCE</a>. M2 Pro TI UCBL 2012</center>
</body>
</html>
