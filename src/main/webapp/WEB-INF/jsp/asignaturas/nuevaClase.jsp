<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<head>
    <meta charset="UTF-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>iTeaching</title>
    <style type="text/css">
    <%@include file="../bootstrap/css/bootstrap.min.css" %>
</style>
	<script src="../bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <link rel="shortcut icon" href="resources/images/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 

</head>

<iteaching:layout pageName="alumnos">

<div style="min-height:85vh">
    <h1>
        Nuevo Clase
    </h1>
    
   
    
    <div class="col-sm-8" style="margin: 0 0 20px 0">
    <form:form modelAttribute="clase" class="form-horizontal" id="add-alumno-form">
        <div class="form-group has-feedback">     
            <iteaching:inputField label="Hora de comienzo" name="horaComienzo"/>
            <iteaching:inputField label="Hora de Fin" name="horaFin"/>
<%--             <form:input type="hidden" path="alumno.user.username"/> --%>
			<form:input type="hidden" path="aceptacionAlumno"/>
			<form:input type="hidden" path="aceptacionProfesor"/>
			<form:input type="hidden" path="asignatura.id" value="${asignaturaId}"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Crear Clase</button>
            </div>
        </div>
    </form:form>
    </div>
</div>
    
</iteaching:layout>