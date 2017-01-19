<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Direction</title>
</head>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<script src="resources/js/bootstrap.css"></script>
<body>
	Accueil Commercial
	<br />

	<a href="/system/direction/listeDesProduits?from=1">Les des
		produits</a>
	<br />
	<a href="/system/tecma/getCommandeByCommercial">Mes Commandes</a>
	<a href="/system/tecma/creerCommande">Créer une commande</a>

	<form:form method="POST" action="/system/tecma/logout?from=1">
		<input type="submit" value="logout" />
	</form:form>

</body>
</html>