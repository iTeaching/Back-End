<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="Mi perfil">

		
		<div class="row">
			<div class="col-sm-4" style="display: flex; align-items: center; flex-direction: column">
				
			<c:choose>
			    <c:when test="${alumno.avatar == null}">
			        <img src="/resources/images/avatar_defecto.jpg"
					style="width: 150px;" />
			        <br />
			    </c:when>    
			    <c:otherwise>
			        <img src="/resources/images/profile/${alumno.avatar}"
					style="width: 150px;" />
			        <br />
			    </c:otherwise>
			</c:choose>
			<a class="btn btn-default"
			href="<c:url value="/users/profile/changeAvatar/$alumno.username}" />">Actualiza tu avatar</a>
			</div>

</div>


    <div>
    	<p>Nombre: ${alumno.firstName} ${alumno.lastName }</p>
    	<p>Tel�fono: ${alumno.telephone}</p>
    	<p>Correo: ${alumno.email}</p>
    	<p><a href="/alumnos/${alumno.id}/edit">Editar perfil</a></p>
    </div>
</petclinic:layout>