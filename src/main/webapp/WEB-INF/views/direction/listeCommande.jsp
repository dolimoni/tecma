<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste des commandes</title>
</head>
<body>

<c:forEach items="${commandes}" var="commande">
    ${commande.produit.nom} ${commande.destinataire.nom} ${commande.status} 
    <a href="/system/direction/approval?commande_id=${commande.id}">Accepter</a> 
    <a href="/system/direction/disapproval?commande_id=${commande.id}">Refuser</a> 
    <br>
</c:forEach>
</body>
</html>