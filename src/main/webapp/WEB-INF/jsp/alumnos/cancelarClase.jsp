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
	<style type="text/css">
	.button {
  background-color: #FFDD33;
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

<iteaching:layout pageName="alumnos">
       
    
    <div class="col-sm-8" style="margin: 0 0 20px 0">
                <h1>Cancelar Clase</h1> 
                

        <table id="clasesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Hora de Comienzo</th>
            <th style="width: 150px;">Hora de Fin</th>
            <th style="width: 200px;">Profesor</th>
            <th>Asignatura</th>          
            <th>Precio por hora</th>


         
        </tr>
        </thead>
        <tbody>            
                <tr>
                    <td>
                        <c:out value="${clase.horaComienzo}"/>
                    </td>
                    <td>
                        <c:out value="${clase.horaFin}"/>
                    </td>
                    <td>
                        <c:out value="${clase.profesor.firstName}"/>
                    </td>

                    <td>
                        <c:out value="${clase.asignatura.nombre}"/>
                    </td>
                    <td>
                   	 	<c:out value="${clase.asignatura.precio}€"/> 
                    </td>

                </tr>

        </tbody>
            
    </table>

   
    
    <form:form modelAttribute="clase" class="form-horizontal" id="add-alumno-form">
        <div class="form-group has-feedback">     
<%--             <form:input type="hidden" path="alumno.user.username"/> --%>
			<form:input type="hidden" path="horaComienzo"/>
			<form:input type="hidden" path="horaFin"/>
			<form:input type="hidden" path="aceptacionAlumno"/>
			<form:input type="hidden" path="aceptacionProfesor"/>
			<form:input type="hidden" path="profesor"/>
			<form:input type="hidden" path="asignatura"/>
			<form:input type="hidden" path="alumno"/>
			
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="button" type="submit">Cancelar Clase</button>
            </div>
        </div>
    </form:form>
    </div>

    
</iteaching:layout>