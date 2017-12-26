<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bienvenue sur ProxyBanque</title>
<link rel="stylesheet" href="./style/style.css" />
</head>
<body>
	<div id="header">
		<h1 id="titre">Votre Agence Proxy Banque</h1>
	</div>
	<div id="content">
		<div class="col" id="c_menu">
			<ul>
				<li><a href="./" class="menu">Accueil</a></li>
				<li><a href="./" class="menu">Accueil</a></li>
				<li><a href="./" class="menu">Accueil</a></li>
			</ul>
		</div>
		<div class="col" id="c_center">
			<c:choose>
				<c:when test="${displayOK}">
					<h3 class="message Ok">${msg}</h3>
					<table>
						<tr>
							<th>Nom</th>
							<th>Fonction</th>
							<th></th>
						</tr>
						<c:forEach items="${listConseillers}" var="cons">
							<tr>
								<td>${cons.prenom} ${cons.nom}</td>
								<td>${cons.typeConseiller}</td>
								<td><a class="tables" href="viewclients?idCons=${cons.id}">
										<c:forEach items="${mapNbrClientsConseiller}" var="listItem">
											<c:if test="${listItem.key eq cons.id}">${listItem.value}</c:if>
										</c:forEach> client(s)
								</a></td>
							</tr>
						</c:forEach>
					</table>
				</c:when>
				<c:otherwise>
					<h3 class="message Erreur">${msg}</h3>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="col" id="c_aside">
			<h3>Titre</h3>
			<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed
				do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut
				enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi
				ut aliquip ex ea commodo consequat. Duis aute irure dolor in
				reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
				pariatur. Excepteur sint occaecat cupidatat non proident, sunt in
				culpa qui officia deserunt mollit anim id est laborum.</p>
		</div>
	</div>

	<div id="footer">&copy;Proxy Banque</div>
</body>
</html>