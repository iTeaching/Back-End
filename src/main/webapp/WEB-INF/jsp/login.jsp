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
  background-color: #F9DA5C;
  border: none;
  color: #994E33;
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
  width: 300px;
}
	footer{
    background: #F9DA5C;
    padding: 60px 0 30px 0;
    overflow: hidden;
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
		<div class="form-floating mb-3" style="width:500px;">
  <input type="text" name="username" class="form-control" id="floatingInput" placeholder="Usuario">
  <label for="floatingInput">Usuario</label>
</div>
<div class="form-floating"style="width:500px;">
 <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Contraseña">
  <label for="floatingPassword">Contrase&ntildea</label>
</div>
		<p style="color:red;">${error}</p>
		
		<div class="center" >
		<button class="button">Iniciar sesión</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			</form>
		<div class="center">
   <a class="button" href="/alumnos/new">Registrarse como alumno</a>
    </div> 
		<div class="center">
    <a class="button" href="/profesores/new">Registrarse como profesor</a>
    </div> 
		
		</div>

        </section>
        
        
        
    
    </main>
    </body>
    <footer style=" background: #F9DA5C; height:25px;">
	<div class="contenedor-footer"style=" margin-top:-50px;">
		<div style=" font-size:26px;">
			<img src="../resources/images/gmail.svg" class="footer_img"style=" margin-left:-600px;">iteaching.sa@gmail.com
		</div>
	
		<h2 class="titulo-final" style="margin-right:-600px; margin-top:50px; font-size:18px;">&copy; 2022,iTeaching</h2>
	</div>
	
</footer>
