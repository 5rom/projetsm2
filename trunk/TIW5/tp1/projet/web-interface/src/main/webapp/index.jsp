<html>
<!-- JSP index.jsp : page d'accueil de la web-interface pour les albums -->
<!-- Contient un formulaire recherche d'un album et d'appel de la servlet-->
<!-- Auteurs : David CRESCENCE et Sebastien FAURE (M2TI) -->
<!-- UCBL1 2011-2012 -->
<body>
<h2>Informations d'albums</h2>
<br>
<!--  Formulaire de saisie du numéro d'album recherché. -->
<form action="ServiceServlet" method="post">
Identifiant de l'album: <input type="text" name="noAlbum" />
<br>
Quel type d'affichage d&eacute;sirez-vous?
<input type="radio" name="format" value="xml"
 checked>affichage XML
<input type="radio" name="format" value="xhtml">affichage XHTML
<br>
<br>
<input type="submit" name="bouton" value="Afficher les infos de l'album">
<br>
<br>
ATTENTION: A la premi&egrave;re utilisation la base sera vide. Veuillez cliquer sur le bouton suivant pour ajouter deux albums commencant &agrave; l'id 1.<br>
<input type="submit" name="bouton" value="Ajouter deux albums a la BD">
</form>
</body>
</html>
