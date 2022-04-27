<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<!DOCTYPE>
<link rel="shortcut icon" href="resources/images/logo.ico">
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
					<input type="text" id="name" value="${user.username}"  
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
	<script>
	'use strict';

	var usernamePage = document.querySelector('#username-page');
	var chatPage = document.querySelector('#chat-page');
	var usernameForm = document.querySelector('#usernameForm');
	var messageForm = document.querySelector('#messageForm');
	var messageInput = document.querySelector('#message');
	var messageArea = document.querySelector('#messageArea');
	var connectingElement = document.querySelector('.connecting');
	var asignatura = window.location.pathname.split('/')[1];

	var stompClient = null;
	var username = null;
	
	var colors = [
	    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
	    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
	];

	function connect(event) {
	    username = document.querySelector('#name').value.trim();

	    if(username) {
	        usernamePage.classList.add('hidden');
	        chatPage.classList.remove('hidden');

	        var socket = new SockJS('/javatechie');
	        stompClient = Stomp.over(socket);

	        stompClient.connect({}, onConnected, onError);
	        
	    }
	    event.preventDefault();
	}


	function onConnected() {
	    // Subscribe to the Topic
	    stompClient.subscribe('/topic/asignatura/'+asignatura, onMessageReceived);

	    // Tell your username to the server
	    stompClient.send("/topic/asignatura/"+asignatura,
	        {},
	        JSON.stringify({sender: username, type: 'JOIN'})
	    )

	    connectingElement.classList.add('hidden');
	}


	function onError(error) {
	    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	    connectingElement.style.color = 'red';
	}


	function send(event) {
	    var messageContent = messageInput.value.trim();

	    if(messageContent && stompClient) {
	        var chatMessage = {
	            sender: username,
	            content: messageInput.value,
	            type: 'CHAT'
	        };

	        stompClient.send("/topic/asignatura/"+asignatura, {}, JSON.stringify(chatMessage));
	        messageInput.value = '';
	    }
	    event.preventDefault();
	}


	function onMessageReceived(payload) {
	    var message = JSON.parse(payload.body);

	    var messageElement = document.createElement('li');

	    if(message.type === 'JOIN') {
	        messageElement.classList.add('event-message');
	        message.content = message.sender + ' conectado!';
	    } else if (message.type === 'LEAVE') {
	        messageElement.classList.add('event-message');
	        message.content = message.sender + ' abandonó!';
	    } else {
	        messageElement.classList.add('chat-message');

	        var avatarElement = document.createElement('i');
	        var avatarText = document.createTextNode(message.sender[0]);
	        avatarElement.appendChild(avatarText);
	        avatarElement.style['background-color'] = getAvatarColor(message.sender);

	        messageElement.appendChild(avatarElement);

	        var usernameElement = document.createElement('span');
	        var usernameText = document.createTextNode(message.sender);
	        usernameElement.appendChild(usernameText);
	        messageElement.appendChild(usernameElement);
	    }

	    var textElement = document.createElement('p');
	    var messageText = document.createTextNode(message.content);
	    textElement.appendChild(messageText);

	    messageElement.appendChild(textElement);

	    messageArea.appendChild(messageElement);
	    messageArea.scrollTop = messageArea.scrollHeight;
	}


	function getAvatarColor(messageSender) {
	    var hash = 0;
	    for (var i = 0; i < messageSender.length; i++) {
	        hash = 31 * hash + messageSender.charCodeAt(i);
	    }

	    var index = Math.abs(hash % colors.length);
	    return colors[index];
	}

	usernameForm.addEventListener('submit', connect, true)
	messageForm.addEventListener('submit', send, true)
	</script>

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