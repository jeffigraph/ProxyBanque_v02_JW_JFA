<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./style/style.css" />
<title>Choix du compte d&eacute;diteur</title>
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
			<!-- 			<form action="GestionVirement" method="post"> -->
			<!-- 				<fieldset> -->
			<!-- 					<legend>Virement :</legend> -->
			<!-- 					<div class="field_line"> -->
			<!-- 						<label for="numerocomptecredit">Compte &agrave; -->
			<!-- 							d&eacute;biter :</label> <input type="text" name="numerocomptedebit" -->
			<!-- 							id="numerocomptedebit" -->
			<!-- 							placeholder="Compte &agrave; d&eacute;biter" required="required"> -->
			<!-- 					</div> -->
			<!-- 					<div class="field_line"> -->
			<!-- 						<label for="numerocomptedebit">Compte &agrave; -->
			<!-- 							cr&eacute;diter :</label> <input type="text" name="numerocomptecredit" -->
			<!-- 							id="numerocomptecredit" -->
			<!-- 							placeholder="Compte &agrave; cr&eacute;diter" required="required"> -->
			<!-- 					</div> -->
			<!-- 					<div class="field_line"> -->
			<!-- 						<label for="montant">Montant du virement :</label> <input -->
			<!-- 							type="text" name="montant" id="montant" placeholder="Montant" -->
			<!-- 							required="required"> -->
			<!-- 					</div> -->
			<!-- 					<div class="field_line"> -->
			<!-- 						<input type="submit" value="Effectuer le virement"> -->
			<!-- 					</div> -->
			<!-- 				</fieldset> -->
			<!-- 			</form> -->

			<form action="GestionVirement2" method="post" style="overflow: inline;">
				<p>Debiteur</p>
				<table>
					<th>
					<td>Nom</td>
					<td>Numero Compte</td>
					</th>
					<c:forEach items="${ clientsDebiteurList}" var="clientDeb">
						<c:if test="${clientDeb.compteCourant ne Empty }">
							<tr><td></td><td>${clientDeb.nom}&nbsp;${  clientDeb.prenom}</td>
								<td>${clientDeb.compteCourant.numeroCompte }<input
									type="radio" name="clientDeb"
									value="${clientDeb.compteCourant.numeroCompte }"></td>
							</tr>
						</c:if>
						<c:if test="${clientDeb.compteEpargne ne Empty }">
							<tr><td></td><td>${clientDeb.nom}&nbsp;${  clientDeb.prenom}</td>
								<td>${clientDeb.compteEpargne.numeroCompte }<input
									type="radio" name="clientDeb"
									value="${clientDeb.compteEpargne.numeroCompte}"></td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
				<p>Crediteur</p>
				<table>
					<th>
					<td>Nom</td>
					<td>Numero Compte</td>
					</th>
					<c:forEach items="${clientsCrediteurList}" var="clientCred">

						<c:if test="${clientCred.compteCourant ne Empty }">
							<tr><td></td><td>${clientCred.nom}&nbsp;${ clientDeb.prenom}</td>
								<td>${clientCred.compteCourant.numeroCompte }<input
									type="radio" name="clientCred"
									value="${clientCred.compteCourant.numeroCompte}"></td>
						</c:if>
						</tr>
						<c:if test="${clientCred.compteEpargne ne Empty }">
							<tr><td></td><td>${clientCred.nom}&nbsp;${ clientDeb.prenom}</td>
								<td>${clientCred.compteEpargne.numeroCompte }<input
									type="radio" name="clientCred"
									value="${clientCred.compteEpargne.numeroCompte }"></td>
							</tr>
						</c:if>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="faire virement" />
			</form>

			<c:choose>
				<c:when test="${ (virementOK && displayOK ) }">
					<div class="virement">
						<table>
							<thead>
								<tr>
									<td>Compte</td>
									<td>Numero compte</td>
									<td>Solde</td>
								</tr>
							</thead>
							<tr>
								<td>Debiteur</td>
								<td>${debiteur.numeroCompte}</td>
								<td>${debiteur.solde}</td>
							</tr>
							<tr>
								<td>Crediteur</td>
								<td>${crediteur.numeroCompte}</td>
								<td>${crediteur.solde}</td>
							</tr>
						</table>
					</div>
				</c:when>
			</c:choose>

		</div>
		<div class="col" id="c_aside">
			<h3>${user},</h3>
			<p>Gestion de virements</p>
			<p>Selectionnez les comptes clients sur lesquels effectuer
				l'op&eacute;ration</p>
		</div>
	</div>
	<div id="footer">&copy;Proxy Banque</div>
</body>
</html>