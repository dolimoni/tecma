<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Direction</title>
</head>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<script src="resources/js/bootstrap.css"></script>
<body>
	<form:form method="POST" action="addProduct" modelAttribute="produit">
		<form:label path="nom">Name</form:label>
		<form:input path="nom" />
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>