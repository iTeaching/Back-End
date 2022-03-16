<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<iteaching:layout pageName="profesor nuevo">
<!DOCTYPE>
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
            <a href="/login">¡Aprende ya!</a>
            <a href="mailto:iteaching.sa@gmail.com​">Contáctanos </a>
            <h1 class="eslogan">Una nueva forma de conectar con el profesor</h1>
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
            <form:form modelAttribute="profesor" class="form-signin" >
                <iteaching:inputField label="First Name" name="firstName"/>
                <iteaching:inputField label="Last Name" name="lastName"/>
                <iteaching:inputField label="Telephone" name="telephone"/>
                <iteaching:inputField label="Email" name="email"/>
                <iteaching:inputField label="Username" name="user.username"/>
                <iteaching:inputPassword  label="Password" name="user.password"/>
                <h1><button type="submit">Iniciar</button></h1>
            </form:form>
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
</iteaching:layout>