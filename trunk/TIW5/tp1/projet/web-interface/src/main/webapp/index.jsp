<html>
<body>



<h2>Informations d'albums</h2>
<br>
<form action="HelloServlet" method="post">
Identifiant de l'album: <input type="text" name="noAlbum" />
<br>
Quel type d'affichage d&eacute;sirez-vous?
<input type="radio" name="format" value="xml"
 checked>affichage XML
<input type="radio" name="format" value="xhtml">affichage XHTML
<br>
<br>
<input type="submit" name="btapp" value="Voir l'album">
<br>
<br>
ATTENTION: A la premiere utilisation la base sera vide. Veuillez cliquer sur le bouton suivant pour ajouter deux albums commencant a l'id 1.<br>
<input type="submit" name="btapp" value="Ajouter deux albums a la BD">
</form>
</body>
</html>
