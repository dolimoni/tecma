<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Direction</title>
</head>
<link rel="stylesheet" type="text/css"
	href="resources/bootstrap/css/bootstrap.css">
<script src="resources/js/bootstrap.css"></script>
<body>
	<div class="row">
		<div class="col-md-5">
			<form:form method="POST" action="authentification"
				modelAttribute="client">
				<input type="submit" value="Authetification" />
			</form:form>

		</div>
		<div class="col-md-5">
			<form:form method="POST" action="inscription"
				modelAttribute="client">
				<input type="submit" value="Inscription" />
			</form:form>

		</div>
	</div>
</body>
</html>