<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu FAO</title>
</head>
<body>
<center><h1>Menu utilisateur FAO</h1></center>
<br>
<h2>--- Produits de la base Express ---</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletExpressFAO" method="post">
<input type="submit" name="bouton" value="Afficher les produits chargés du fichier EXPRESS">
</form>
<br><br>
<h2>--- Traduction BD FAO vers OWL ---</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletOWLFAO" method="post">
<input type="submit" name="bouton" value="Traduire en fichier OWL">
</form>

<br><br>
<form action="index.jsp" method="post">
<input type="submit" name="bouton" value="retour accueil">
</form>
</body>
</html>