<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<!DOCTYPE>
<link rel="icon" href="/resources/images/logo.ico" type="image/x-icon">
<html>
<iteaching:menu name="chat"/>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>iTeaching</title>
<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css">
<link href="../resources/style/chat.css" rel="stylesheet" type="text/css">
</head>

<body>
	<noscript>
		<h2>Sorry! Your browser doesn't support Javascript</h2>
	</noscript>

	<div id="username-page">
		<div class="username-page-container">
			<h1 class="title">Nombre de usuario</h1>
			<form id="usernameForm" name="usernameForm">
				<div class="form-group">
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
		<footer>
		<div class="contenedor-footer">
			<div class="footer-contact--email">
				<img src="resources/images/gmail.svg" class="footer_img">
			</div>
		</div>
		<h2 class="titulo-final">&copy; 2022,iTeaching</h2>
	</footer>
	</div>

	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
	<script src="../resources/js/main.js"></script>

</body>
<footer>
	<div class="contenedor-footer">
		<div class="footer-contact--email">
			<img src="resources/images/gmail.svg" class="footer_img">
		</div>
	</div>
	<h2 class="titulo-final">&copy; 2022,iTeaching</h2>
</footer>
</html>