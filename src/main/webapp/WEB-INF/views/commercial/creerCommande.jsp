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
	<form:form method="POST" action="/system/tecma/createCommande"
		modelAttribute="commande">
		<form:label path="produit">produit</form:label>
		<form:select path="produit">
			<option value="2">Dell</option>
			<option value="1">Acer</option>
		</form:select>
		<form:label path="quantite">Quantité</form:label>
		<form:input path="quantite" />
		<form:label path="transport">Transport</form:label>
		<form:input path="transport" />
		<%-- <form:select path="destinataire">
		<c:forEach items="${clients}" var="client">
			<option value="${client.id}">${client.nom}</option>
			</c:forEach>
		</form:select> --%>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>