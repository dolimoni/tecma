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
	
	<c:if test="${insufficientQuantity}">Quantité insuffisante</c:if>
	<c:if test="${newOrder}">
	<form:form method="POST" action="/system/tecma/createCommande"
		modelAttribute="commandeDTO">
		<form:label path="produitId">produit</form:label>
		<form:select path="produitId">
		<c:forEach items="${produitDTOs}" var="produitDTO">
			<option value="${produitDTO.id}">${produitDTO.nom}</option>
			</c:forEach>
		</form:select>
		<form:label path="quantite">Quantité</form:label>
		<form:input path="quantite" />
		<form:label path="transport">Transport</form:label>
		<form:input path="transport" />
		<form:select path="clientId">
		<c:forEach items="${clientDTOs}" var="client">
			<option value="${client.id}">${client.nom}</option>
			</c:forEach>
		</form:select>
		<input type="submit" value="Submit" />
	</form:form>
	</c:if>
	<c:if test="${not newOrder}">
		Aucun produit n'est disponible
	</c:if>
	
	
</body>
</html>