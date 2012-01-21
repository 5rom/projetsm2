<html>
<body>
<h2>Page d'accueil interface web!</h2>
<br>
<a href="http://localhost:8080">La liste des services</a>
<br>
<h2>Albums de la base</h2>
<!--  Formulaire de consultation de la base -->
<br>
<form action="MenuServlet" method="post">
<input type="submit" name="bouton" value="Afficher les produits de la base">
</form>
<h2>Remplir le panier</h2>
<!--  Formulaire de consultation de la base -->
<br>
<FORM METHOD=POST ACTION="testPanier.jsp">
Which album? <INPUT TYPE=TEXT NAME=album SIZE=20>
<P><INPUT TYPE=SUBMIT>
</FORM>

<br>
<%@page import="panier.Panier" %>
<% 
if (session.getAttribute( "panier" )==null){
	Panier p = new Panier();	
	session.setAttribute( "panier", p );
	%><h1>test</h1><%
	 
} else {
	%><h1>Deja dans la session</h1><%
    out.println("<html><body>  Albums du panier\n<br>"+
    		"<table border=\"1\">\n"+
    		"<tr>\n"+
    		"<th>AlbumID</th>\n"+
    		"<th>Quantite</th>\n"+
    		"</tr>\n");
	//for chaque element de la liste de cAODB.getProductList()
	//faire out.println(out.println("<tr><td>"+albums.get(0).getPiste(i).getTitre()+"</td><td>"+albums.get(0).getPiste(i).getDuree()+" min</td></tr>\n");
	Panier p2 = (Panier)request.getSession().getAttribute("panier");
	for (long mapKey : p2.getListeAlbumsQte().keySet()) {
		out.println("<tr><td>"+mapKey+"</td><td>"+p2.getListeAlbumsQte().get(mapKey)+"</td></tr>\n");
	}
	
    out.println("</table><br>\n");
    
    // Pour valider la commande on parcourera le panier et pour chaque id Album on fait qte * prix de getAlbumDescription.
}


%>


<br>
</body>
</html>
