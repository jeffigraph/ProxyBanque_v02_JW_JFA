<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>
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
				<c:if test="${session.isCons ne null }">
					<li><a href="Autentification?log=logout" class="menu">D&eacute;connection</a></li>
				</c:if>
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
			<form action="Autentification" method="post" class="autentification">
				<fieldset>
					<legend>Connection :</legend>
					<div class="field_line">
						<label for="login">Login :</label><input type="text" name="login"
							id="login" placeholder="Identifiant" required="required">
					</div>
					<div class="field_line">
						<label for="password">Mot de passe :</label><input type="password" name="password" 
						id="password" placeholder="Mot de passe" required="required">
					</div>
					<div class="field_line">
						<input type="submit" class="form" value="se connecter">
					</div>
				</fieldset>
			</form>
		</div>
		<div class="col" id="c_aside">
			<h3>Connexion</h3>
			<p>Veuillez entrer votre login et votre mot de passe afin de vous
				connecter &agrave; votre portefeuille client.</p>
		</div>
	</div>
	<div id="footer">&copy;Proxy Banque</div>

</body>
</html>