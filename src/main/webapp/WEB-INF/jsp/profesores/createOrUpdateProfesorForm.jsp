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
    <h2>
        <c:if test="${profesor['new']}">Nuevo </c:if> Profesor
    </h2>
    <div class="col-sm-4" style="display: flex; align-items: center; flex-direction: column">
				
			<c:choose>
			    <c:when test="${profesor.avatar == null}">
			        <img src="/resources/images/profile/avatar_defecto.jpg"
					style="width: 150px;" />
			        <br />
			    </c:when>    
			    <c:otherwise>
			        <img src="/resources/images/profile/${profesor.avatar}"
					style="width: 150px;" />
			        <br />
			    </c:otherwise>
			</c:choose>
			<a class="btn btn-default"
			href="<c:url value="/profesor/miPerfil/changeAvatar/${profesor.id}" />">Actualiza tu avatar</a>
			</div>
			 <div class="col-sm-8" style="margin: 0 0 20px 0">
    <form:form modelAttribute="profesor" class="form-horizontal" id="add-profesor-form">
        <div class="form-group has-feedback">
       
            <iteaching:inputField label="First Name" name="firstName"/>
            <iteaching:inputField label="Last Name" name="lastName"/>
            <iteaching:inputField label="Telephone" name="telephone"/>
            <iteaching:inputField label="Email" name="email"/>
            <form:input type="hidden" path="puntuacion"/>
            <form:input type="hidden" path="division"/>
	        <c:choose>
                    <c:when test="${profesor['new']}">
                        <iteaching:inputField label="username" name="user.username"/>
                    </c:when>
                <c:otherwise>
                        <form:input type="hidden" path="user.username"/>

                </c:otherwise>
			</c:choose>
            <iteaching:inputPassword label="Password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${profesor ['new']}">
                        <button class="button" type="submit">Registrarse</button>
                    </c:when>
                    <c:otherwise>
                        <button class="button" type="submit">Actualizar Usuario</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
    </div>
</iteaching:layout>