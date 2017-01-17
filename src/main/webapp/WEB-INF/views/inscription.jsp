<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Direction</title>
</head>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<script src="resources/js/bootstrap.css"></script>
<body>
	Page d'inscription<br/>
	
	<c:if test="${emptyUsernameOrPassword}">Username ou password n'est pas valide</c:if>
	<form:form method="POST" action="register" modelAttribute="client">
		<form:label path="nom">Name</form:label>
		<form:input path="nom" />
		<form:label path="password">Password</form:label>
		<form:input path="password" />
		<form:label path="secteur">Secteur</form:label>
		<form:select path="secteur">
			<option value="informatique">Informatique</option>
			<option value="industriel">Industriel</option>
		</form:select>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>