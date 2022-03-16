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
	<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css">
	<style>
body {
    font-family: 'open sans';
}

.contenedor {
    padding: 60px 0;
    width: 90%;
    max-width: 1000px;
    margin: auto;
    overflow: hidden;
}

.registros {
  background-image: url(../images/img1.jpg);
  background-size: cover;
 }

 </style>
</head>

<body class="registros" background="../images/img1.jpg">
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