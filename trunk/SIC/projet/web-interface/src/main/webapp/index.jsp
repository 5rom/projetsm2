<html>
<body>
<h2>Page d'accueil interface web!</h2>
<br>
<a href="http://localhost:8080/web-interface/services">La liste des services</a>
<br>
<h2>Produits de la base</h2>
<br><form action="ServletDBCAO" method="post">
<input type="submit" name="bouton" value="Afficher les produits de la base">
</form>
<h2>Ajout d'un produit</h2>
<br>
<!--  Formulaire de saisie du num�ro d'album recherch�. -->
<form action="ServletDBCAO" method="post">
Num�ro de produit: <input type="text" name="pnum" />
<br>
Nom de produit: <input type="text" name="pnom" />
<br>
<br>
<input type="submit" name="bouton" value="Ajouter le produit dans la base">
</form>
<h2>Suppression d'un produit</h2>
<br>
<!--  Formulaire de saisie du num�ro d'album recherch�. -->
<form action="ServletDBCAO" method="post">
Num�ro du produit: <input type="text" name="pnum" />
<br>
<br>
<input type="submit" name="bouton" value="Supprimer le produit de la base">
</form>
</body>
</html>
