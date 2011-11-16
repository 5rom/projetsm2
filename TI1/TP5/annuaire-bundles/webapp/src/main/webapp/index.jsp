<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Formulaire servlet annuaire</h1>
        <form action="AnnuaireServlet" method="get">
        <table>
        <tbody>
        <tr><td>Nom annuaire:</td><td><input name="annuaire" type="text"></td></tr>
        <tr><td>Action:</td><td>
        	<select name="action">
        	<option value="ajout">Ajouter un site</option>
        	<option value="supression">Supprimer un site</option>
        	<option value="lister-sites">Lister les sites</option>
        	<option value="lister-annuaires">Lister les annuaires</option>
        	</select></td></tr>
        <tr><td>URL du site</td><td><input name="url" type="text"></td></tr>
        <tr><td>Description</td><td><input name="description" type="text"></td></tr>
        </tbody>
        </table>
        <input type="submit">
        </form>
    </body>
</html>
