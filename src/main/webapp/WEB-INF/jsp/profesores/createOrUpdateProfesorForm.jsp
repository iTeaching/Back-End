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
<body>
    <header>
        <nav>
            <a href="/login">¡Aprende ya!</a>
            <a href="mailto:iteaching.sa@gmail.com​">Contáctanos </a>
            <h1 class="eslogan">Una nueva forma de conectar con el profesor</h1>
        </nav>
        <img src="../resources/images/logo.png" class="logo">

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

</body>
</iteaching:layout>