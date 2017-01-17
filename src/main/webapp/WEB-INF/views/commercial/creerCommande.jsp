<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Direction</title>
</head>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<script src="resources/js/bootstrap.css"></script>
<body>
	<form:form method="POST" action="/tecma/createCommande" modelAttribute="commande">
		<form:label path="produit">produit</form:label>
		<form:select path="produit">
			<option value="Dell">Dell</option>
			<option value="Acer">Acer</option>
		</form:select>
		<form:label path="quantite">Quantité</form:label>
		<form:input path="quantite" />
		<form:label path="transport">Transport</form:label>
		<form:input path="transport" />
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>