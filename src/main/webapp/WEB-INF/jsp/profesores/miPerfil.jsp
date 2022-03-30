<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<petclinic:layout pageName="Mi perfil">
		<div class="col-sm-4" style="display: flex; flex-direction: column">
				
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
			</div>
    <div>
    	<p>Nombre: ${profesor.firstName} ${profesor.lastName }</p>
    	<p>Teléfono: ${profesor.telephone}</p>
    	<p>Correo: ${profesor.email}</p>
    	<span>Asignaturas
    		<ul>
    		<c:forEach var="asignatura" items="${profesor.asignaturas}">
    			<li>${asignatura.nombre}</li>
			</c:forEach>
    		</ul>
    	</span>
    	<p><a href="/profesores/${profesor.id}/edit">Editar perfil</a></p>
    </div>
</petclinic:layout>