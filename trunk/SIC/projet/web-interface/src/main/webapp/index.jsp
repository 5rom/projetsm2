<html>
<body>
<h2>Page d'accueil interface web!</h2>
<br>
<a href="http://localhost:8080/web-interface/services">La liste des services</a>
<br>
<h2>Produits de la base</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletDBCAO" method="post">
<input type="submit" name="bouton" value="Afficher les produits de la base">
</form>
<h2>Ajout d'un produit</h2>
<br>
<!--  Formulaire de saisie du produit � ajouter dans la base -->
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
<!--  Formulaire de saisie du produit � supprimer -->
<form action="ServletDBCAO" method="post">
Num�ro du produit: <input type="text" name="pnum" />
<br>
<br>
<input type="submit" name="bouton" value="Supprimer le produit de la base">
</form>
<h2>Ajout d'une composition</h2>
<br>
<!--  Formulaire de saisie de la composition � supprimer -->
<form action="ServletDBCAO" method="post">
Num�ro du produit majeur: <input type="text" name="pmajeur" />
<br>
Num�ro du produit mineur: <input type="text" name="pmineur" />
<br>
<br>
<input type="submit" name="bouton" value="Ajouter la composition dans la base">
</form>
<h2>Suppression d'une composition</h2>
<br>
<!--  Formulaire de saisie de la composition � supprimer -->
<form action="ServletDBCAO" method="post">
Num�ro du produit majeur: <input type="text" name="pmajeur" />
<br>
Num�ro du produit mineur: <input type="text" name="pmineur" />
<br>
<br>
<input type="submit" name="bouton" value="Supprimer la composition de la base">
</form>
<h2>Mise � jour d'un nom de produit</h2>
<br>
<!--  Formulaire de saisie de nouveau nom pour un produit -->
<form action="ServletDBCAO" method="post">
Num�ro du produit � modifier: <input type="text" name="pnum" />
<br>
Nouveau nom: <input type="text" name="pnom" />
<br>
<br>
<input type="submit" name="bouton" value="Mettre � jour le nom du produit dans la base">
</form>
<br>
<h2>Produits de la base Express</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletExpressFAO" method="post">
<input type="submit" name="bouton" value="Afficher les produits de la base">
</form>
<h2>Traduction BD CAO vers OWL</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="ServletOWLCAO" method="post">
<input type="submit" name="bouton" value="Traduire">
</form>

</body>
</html>
