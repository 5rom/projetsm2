<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu CAO</title>
</head>
<body>
<h2>Menu utilisateur CAO</h2>
<br>
<h2>Produits de la base</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletDBCAO" method="post">
<input type="submit" name="bouton" value="Afficher les produits de la base">
</form>
<h2>Ajout d'un produit</h2>
<br>
<!--  Formulaire de saisie du produit à ajouter dans la base -->
<form action="ServletDBCAO" method="post">
Numéro de produit: <input type="text" name="pnum" />
<br>
Nom de produit: <input type="text" name="pnom" />
<br>
<br>
<input type="submit" name="bouton" value="Ajouter le produit dans la base">
</form>
<h2>Suppression d'un produit</h2>
<br>
<!--  Formulaire de saisie du produit à supprimer -->
<form action="ServletDBCAO" method="post">
Numéro du produit: <input type="text" name="pnum" />
<br>
<br>
<input type="submit" name="bouton" value="Supprimer le produit de la base">
</form>
<h2>Ajout d'une composition</h2>
<br>
<!--  Formulaire de saisie de la composition à supprimer -->
<form action="ServletDBCAO" method="post">
Numéro du produit majeur: <input type="text" name="pmajeur" />
<br>
Numéro du produit mineur: <input type="text" name="pmineur" />
<br>
<br>
<input type="submit" name="bouton" value="Ajouter la composition dans la base">
</form>
<h2>Suppression d'une composition</h2>
<br>
<!--  Formulaire de saisie de la composition à supprimer -->
<form action="ServletDBCAO" method="post">
Numéro du produit majeur: <input type="text" name="pmajeur" />
<br>
Numéro du produit mineur: <input type="text" name="pmineur" />
<br>
<br>
<input type="submit" name="bouton" value="Supprimer la composition de la base">
</form>
<h2>Mise à jour d'un nom de produit</h2>
<br>
<!--  Formulaire de saisie de nouveau nom pour un produit -->
<form action="ServletDBCAO" method="post">
Numéro du produit à modifier: <input type="text" name="pnum" />
<br>
Nouveau nom: <input type="text" name="pnom" />
<br>
<br>
<input type="submit" name="bouton" value="Mettre à jour le nom du produit dans la base">
</form>
<br>
<h2>Traduction BD CAO vers OWL</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletOWLCAO" method="post">
<input type="submit" name="bouton" value="Traduire">
</form>

<br>
<form action="index.jsp" method="post">
<input type="submit" name="bouton" value="retour accueil">
</form>
<!-- Voir comment envoyer un fichier dans le form -->
</body>
</html>
