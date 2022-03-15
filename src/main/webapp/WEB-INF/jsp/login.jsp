<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html lang="es">

<head>
    <meta charset="UTF-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>iTeaching</title>
    <link rel="shortcut icon" href="resources/images/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 
	<link href="../resources/images/estilos.css" rel="stylesheet" type="text/css">
</head>

<body>
    <header>
        <nav>
        </nav>
        <img src="resources/images/logo.png" class="logo">

        <div class="wave" style="height: 150px; overflow: hidden;"><svg viewBox="0 0 500 150" preserveAspectRatio="none"
                style="height: 100%; width: 100%;">
                <path d="M0.00,49.98 C150.00,150.00 349.20,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
                    style="stroke: none; fill: #fff;"></path>
            </svg></div>
    </header>
    <main>
        <section class="contenedor sobre-nosotros">
            <h1>Login</h1>
	<form action="${path}/login" method="post" class="form-signin">
		<p style="color:red;">${error}</p>
		<p style="color:green;">${message}</p>
		<p>Usuario : <input type="text" name="username" placeholder="Usuario"/></p>
		<p>Contrasena : <input type="password" name="password" placeholder="Contrasena"/></p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<h1><button type="submit">Iniciar</button></h1>
	</form>
        </section>
    </main>
    <footer>
        <div class="contenedor-footer">
            <div class="footer-contact--email">
                <img src="resources/images/gmail.svg" class="footer_img">
            </div>
        </div>
        <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
    </footer>
</body>