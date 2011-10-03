<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!--<html>
		<head><title>Infos de l'album</title></head>
		<body>  -->
		<h3>Nom de l'album <xsl:value-of select="album/@id" />: <xsl:value-of select="album/titre" /></h3>
		<br></br>
		<h3>Pistes de l'album :</h3>
		<table border="1">
		<tr>
		<th>Piste</th>
		<th>Duree</th>
		</tr>
		<xsl:for-each select="album/piste">
			<tr>
			<td><xsl:value-of select="titre"/></td>
			<td><xsl:value-of select="@duree" /></td>
			</tr>
		</xsl:for-each>
		</table>
		<br></br>
		<h3>Artistes de l'album :</h3>
		<table border="1">
		<tr>
		<th>Prenom</th>
		<th>Nom</th>
		</tr>
		<xsl:for-each select="album/artiste">
			<tr>
			<td><xsl:value-of select="prenom"/></td>
			<td><xsl:value-of select="nom" /></td>
			</tr>
		</xsl:for-each>		
		</table>		
		<!-- 
		</body></html> -->
	</xsl:template>
</xsl:stylesheet>