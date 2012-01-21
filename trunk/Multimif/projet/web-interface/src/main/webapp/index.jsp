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
}


%>

<br>
</body>
</html>
