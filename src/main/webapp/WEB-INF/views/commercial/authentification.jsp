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
	Page d'authentification<br/>
	
	<c:if test="${invalidUsernameOrPassword}">Username ou password n'est pas valide</c:if>
	<form:form method="POST" action="/system/tecma/authenticateCommercial" modelAttribute="commercial">
		<form:label path="name">Name</form:label>
		<form:input path="name" />
		<form:label path="password">Password</form:label>
		<form:input path="password" />
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>