<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li><a href="ViewClients">Portefeuille</a></li>
				<li><a href="GestionVirement" class="menu">Virements</a></li>
				<li><a href="Autentification?log=logout" class="menu">D&eacute;connection</a></li>
			</ul>
		</div>
		<div class="col" id="c_center">
			<c:choose>
				<c:when test="${displayOK}">
					<h3 class="message Ok">${msg}</h3>
				</c:when>
				<c:otherwise>
					<h3 class="message Erreur">${msg}</h3>
				</c:otherwise>
			</c:choose>
			<form class="client"
				action="ViewDetailsClient?idClient=${client.idClient}" method="post">
				<table>
					<tr>
						<th>Nom</th>
						<th>Adresse</th>
						<th>T&eacute;l&eacute;phone</th>
					</tr>
					<tr>
						<td>${client.prenom}&nbsp;${client.nom}</td>
						<td>${client.adresse},&nbsp;${client.codePostal}&nbsp;
							${client.ville}</td>
						<td>${client.telephone}</td>
					</tr>
					<tr>
						<td><div class="field_line client">
								<input type="text" class="client" name="prenom" id="prenom"
									placeholder="Pr&eacute;nom"> <input type="text"
									class="client" name="nom" id="nom" placeholder="Nom">
							</div></td>
						<td><div class="field_line client">
								<input type="text" class="client" name="adresse" id="adresse"
									placeholder="Adresse"> <input type="text"
									class="client" name="codepostal" id="codepostal"
									placeholder="Code postal"> <input type="text"
									class="client" name="ville" id="ville" placeholder="Ville">
							</div></td>
						<td><div class="field_line client">
								<input type="text" class="client" name="telephone"
									id="telephone" placeholder="T&eacute;l&eacute;phone">
							</div></td>
					</tr>
					<tr>
						<td colspan="3"><div class="field_line client">
								<input type="submit" value="mettre &agrave; jour">
							</div></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="col" id="c_aside">
			<h3>${user},</h3>
			<p>D&eacute;tails du compte client</p>
			<p>Vous pouvez modifier les informations de votre client.</p>
		</div>
	</div>
	<div id="footer">&copy;Proxy Banque</div>
</body>
</html>