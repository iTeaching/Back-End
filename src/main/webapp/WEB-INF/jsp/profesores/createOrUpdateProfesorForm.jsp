<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<iteaching:layout pageName="alumnos">
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
    
        	<script src="https://www.google.com/recaptcha/api.js"></script>
	
	<script>
        	window.onload = function() {
        		var $recaptcha = document.querySelector('#g-recaptcha-response');
        		if($recaptcha) {
        			$recaptcha.setAttribute("required", "required");
        		}
        		$recaptcha.oninvalid = function(e) {
        			alert("Por favor, verifique que es una persona");
        		}
        	};
    </script>
    
</head>

<div style="min-height:85vh">
    <h2>
        <c:if test="${profesor['new']}">Nuevo </c:if> Profesor
    </h2>

			 <div class="col-sm-8" style="margin: 0 0 20px 0">
    <form:form modelAttribute="profesor" class="form-horizontal" id="add-profesor-form">
        <div class="form-group has-feedback">
       
            <iteaching:inputField label="Nombre" name="firstName"/>
            <iteaching:inputField label="Apellidos" name="lastName"/>
            <iteaching:inputField label="Teléfono" name="telephone"/>
            <iteaching:inputField label="Correo electrónico" name="email"/>
            <form:input type="hidden" path="puntuacion"/>
            <form:input type="hidden" path="division"/>
	        <c:choose>
                    <c:when test="${profesor['new']}">
                        <iteaching:inputField label="Usuario" name="user.username"/>
                    </c:when>
                <c:otherwise>
                        <form:input type="hidden" path="user.username"/>

                </c:otherwise>
			</c:choose>
            <iteaching:inputPassword label="Contraseña" name="user.password"/>
            <input type="checkbox" name="agree" required/>
        <label for="agree">Como usuario acepto los <a href="/TerminosYCondiciones" target="_blank">
        Términos y condiciones de la empresa</a></label>
        
       <br>
       <input type="checkbox" name="agree" required/>
        <label for="agree">He leído y acepto la <a href="/GarantiaDePrivacidad" target="_blank">
        Garantía de privacidad de la empresa</a></label>
        
        
        </div>
        
        <div class="g-recaptcha" data-sitekey="6LcFXzMgAAAAAN9xjgKYGWgKGWIiPItTTKR2thHa">

        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${profesor ['new']}">
                        <button type="submit" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Registrarse</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Actualizar Usuario</button>
                        <br><br>
                        <a href="/profesores/${profesor.id }/delete" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Eliminar Usuario</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </div>
 </div>
</iteaching:layout>