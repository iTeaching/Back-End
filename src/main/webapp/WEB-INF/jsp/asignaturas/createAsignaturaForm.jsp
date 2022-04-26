<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<petclinic:layout pageName="asignaturas">
    <h2>
        <c:if test="${asignatura['new']}">Nueva </c:if> Asignatura
    </h2>
    <form:form modelAttribute="asignatura" class="form-horizontal" id="add-room-form">
    	<input type="hidden" name="id" value="${asignatura.id}"/>
        <%-- <input type="hidden" name="version" value="${asignatura.version}"/>
        <input type="hidden" name="version" value="${asignatura.version}"/> --%>

        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
            <petclinic:inputField label="Título del anuncio" name="titulo_anuncio"/>
            <petclinic:inputField label="Descripción" name="descripcion"/>
            <div class="col-sm-10">Precio</div> <input type="number" name="precio"/>
			
		</div>
		<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${asignatura['new']}">
                        <button class="btn btn-default" type="submit">¡Publicar Asignatura!</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar datos de la asignatura</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>