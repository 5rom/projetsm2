<html>
<body>
<h2>Page d'accueil intercface web!</h2>
<br>
<a href="http://localhost:8080/web-interface/services">La liste des services</a>
<br>
<h2>Ajout d'un produit</h2>
<br>
<!--  Formulaire de saisie du num�ro d'album recherch�. -->
<form action="ServletDBCAO" method="post">
Num�ro de produit: <input type="text" name="pnum" />
<br>
Nom de produit: <input type="text" name="pnom" />
<br>
<br>
<input type="submit" name="bouton" value="Ajouter le produit">
</form>
</body>
</html>
