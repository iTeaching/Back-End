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
    <style type="text/css">
    <%@include file="bootstrap/css/bootstrap.min.css" %>
</style>
	<script src="bootstrap/js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="resources/images/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 
	<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css">

	<style type="text/css">
	.button {
  background-color: #dab305;
  border: none;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 12px;
  padding: 14px 40px;
  align-self:center;
}

	</style>
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
        <div align="center">
            <h1>Login</h1>
	<form action="${path}/login" method="post" class="form-signin">
	
		<p style="color:green;">${message}</p>
		<div class="form-floating mb-3">
  <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Usuario">
  <label for="floatingInput">Usuario</label>
</div>
<div class="form-floating">
 <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
  <label for="floatingPassword">Contrase&ntildea</label>
</div>
		<p style="color:red;">${error}</p>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button class="button">Iniciar sesión</button> 
			</form>
		</div>

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