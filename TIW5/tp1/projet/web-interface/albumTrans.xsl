<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<!--<html>
		<head><title>Infos de l'album</title></head>
		<body>  -->
		<h1>Infos de l'album</h1>
		<br></br>
		<UL>
		<LI>Nom de l'album: <xsl:value-of select="album/titre" /></LI>		
		<LI>Numero de l'album: <xsl:value-of select="album/@id" /></LI>

		<LI>Artistes de l'album :
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
		</LI>	
		<LI>Pistes de l'album :
		<table border="1">
		<tr>
		<th>Piste</th>
		<th>Durée</th>
		</tr>
		<xsl:for-each select="album/piste">
			<tr>
			<td><xsl:value-of select="titre"/></td>
			<td><xsl:value-of select="@duree" /> min</td>
			</tr>
		</xsl:for-each>
		</table>
		</LI>
		</UL>
		<br></br>
		<FORM Method="POST" Action="index.jsp">
		<INPUT type="submit" value="Retour"/>
		</FORM>		
		<!-- 
		</body></html> -->
	</xsl:template>
</xsl:stylesheet>