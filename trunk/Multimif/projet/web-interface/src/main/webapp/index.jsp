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


<br>
<%@page import="panier.Panier" %>
<% 
if (session.getAttribute( "panier" )==null){
	Panier p = new Panier();	
	session.setAttribute( "panier", p );
	%><h1>Panier cr��</h1><%
	 
} else {
	
    out.println("Contenu du panier\n<br>"+
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
    %>
    <%@page import="javax.xml.soap.*" import="javax.xml.namespace.QName" import="java.net.URL" %>
    <%
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory.createMessage();
		SOAPPart soapPart = message.getSOAPPart();
		SOAPBody body = message.getSOAPBody();
		QName bodyName = new QName("http://wombat.ztrade.com",
		"GetLastTradePrice", "m");
		SOAPBodyElement bodyElement =
		body.addBodyElement(bodyName);
		// bodyElement est utilisable comment un Element DOM    
		SOAPConnectionFactory
		soapConnectionFactory =
		SOAPConnectionFactory.newInstance();
		SOAPConnection connection =
		soapConnectionFactory.createConnection();
		java.net.URL endpoint = new URL("http://wombat.ztrade.com/quotes");
		/*SOAPMessage resp = connection.call(message, endpoint);
		connection.close();*/

    %>
    Prix total : <%out.println(p2.getPricePanier());%>
	<!--  Formulaire de validation de la commande -->
	<br>
	<form action="http://localhost:8085/provided-services/banque.jsp" method="post">
	<input type="hidden" name="redirectUrl" value="http://localhost:8081/web-interface/index.jsp">
	<input type="hidden" name="confirmeA" value="<%//out.println(endpoint.getRef());%>">
	<input type="hidden" name="combien" value="<%out.println(p2.getPricePanier());%>">
	<input type="submit" name="bouton" value="Valider la commande">
	</form>
    <%
	}
	%>
<br>


<br>
</body>
</html>
