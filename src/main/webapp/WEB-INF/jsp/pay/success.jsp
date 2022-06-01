<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>iTeaching</title>
    <link rel="shortcut icon" href="img/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 
	<link href="css/estilos.css" rel="stylesheet" type="text/css"> 
    <link href="../resources/style/cookies.css" rel="stylesheet" type="text/css"> 
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-K4BDBJD0X0"></script>

    <header>
        <nav>
        	<a href="https://iteaching-2021-2022.herokuapp.com/"><button class="nav_button" data-hover="¡Bienvenido!"><div>Aprende ya</div></button></a>
            <a href="mailto:iteaching.sa@gmail.com​"><button class="nav_button" data-hover="¡Hola!"><div>Contáctanos</div></button> </a>
            <h1 class="eslogan">UNA NUEVA FORMA DE CONECTAR CON EL PROFESOR</h1>
        </nav>
        <img src="img/logo.png" alt="logo" class="logo">

        <div class="wave" style="height: 150px; overflow: hidden;"><svg viewBox="0 0 500 150" preserveAspectRatio="none"
                style="height: 100%; width: 100%;">
                <path d="M0.00,49.98 C150.00,150.00 349.20,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
                    style="stroke: none; fill: #fff;"></path>
            </svg></div>
                
        </section>
        <section class="contenedor sobre-nosotros">
            <h2 class="titulo">Pago realizado</h2>
            <div class="contenedor-sobre-nosotros">
                <img src="img/clases.png" alt="" class="imagen-sobre-nosotros">
                <div class="contenido-textos">
                    <h3><span>✔</span>Su pago se ha realizado correctamente</h3>
                </div>
            </div>
        </section>
    <footer>
        <div class="contenedor-footer">
            <div class="footer-contact--email">
                <img src="img/gmail.svg" alt="footer" class="footer_img">
            </div>
            <div class="footer-contact--facebook">
                <img src="img/facebook.svg" alt="footer" class="footer_img">
            </div>
            <div class="footer-contact--instagram">
                <img src="img/instagram.svg" alt="footer" class="footer_img">
            </div>
            <div class="footer-contact--twitter">
                <img src="img/twitter.svg" alt="footer" class="footer_img">
            </div>
        </div>
        <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
    </footer>
</body>