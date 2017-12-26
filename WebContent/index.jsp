<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>Bienvenue sur ProxyBanque</title>
<link rel="stylesheet" href="./style/style.css" />
</head>
<body>
	<div id="header">
		<h1 id="titre">
			Bienvenue dans l'interface applicative Web<br />de Proxy Banque
		</h1>
	</div>
	<div id="content">
		<div class="col" id="c_menu">
			<ul>
				<li><a href="./" class="menu">Accueil</a></li>
<%-- 				<c:if test="${session ne null }"> --%>
<!-- 				<li><a href="Autentification?log=logout" class="menu">Déconnection</a></li> -->
<%-- 				</c:if> --%>
			</ul>
		</div>
		<div class="col" id="c_center">
			<h3 class="bienvenue">
				<a href="Autentification?log=login"><strong>Acceder à votre Agence ProxyBanque</strong></a>
			</h3>
		</div>
		<div class="col" id="c_aside">
			<h3>Proxy Banque</h3>
			<p>Veuillez vous identifier pour acceder à vos services Proxy Banque.</p>
		</div>
	</div>
	<div id="footer">&copy;Proxy Banque</div>
</body>
</html>