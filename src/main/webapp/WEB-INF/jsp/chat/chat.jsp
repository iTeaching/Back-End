<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>iTeaching</title>
<link rel="shortcut icon" href="resources/images/logo.ico">
<link rel="stylesheet" href="css/main.css" />
</head>
<body background="18.jpg"
style="background-position: center; background-repeat: no-repeat; background-size: cover;">
	<noscript>
		<h2>Sorry! Your browser doesn't support Javascript</h2>
	</noscript>

	<div id="username-page">
		<div class="username-page-container">
			<h1 class="title">Nombre de usuario</h1>
			<form id="usernameForm" name="usernameForm">
				<div class="form-group">
					<!-- <input type="text" id="name" placeholder="Nombre" class="form-control" /> -->
					<input type="text" id="name" value="${alumno.firstName}&nbsp;${alumno.lastName}"  
					readonly= True placeholder="Nombre" class="form-control" />
				</div>
				<div class="form-group">
					<button type="submit" class="accent username-submit">Iniciar conversación</button>
				</div>
			</form>
		</div>
	</div>

	<div id="chat-page" class="hidden">
		<div class="chat-container">
			<div class="chat-header">
				<h2>iTeaching Chat </h2>
			</div>
			<div class="connecting">Conectando...</div>
			<ul id="messageArea">
			</ul>
			<form id="messageForm" name="messageForm" nameForm="messageForm">
				<div class="form-group">
					<div class="input-group clearfix">
						<input type="text" id="message" placeholder="Tipo de mensaje..."
							autocomplete="off" class="form-control" />
						<button type="submit" class="primary">Enviar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="js/main.js"></script>
</body>
</html>