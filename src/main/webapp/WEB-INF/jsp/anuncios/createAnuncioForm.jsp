<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<iteaching:layout pageName="anuncio nuevo">
    <h2>
        <c:if test="${anuncio['new']}">Nuevo </c:if> Anuncio
    </h2>
    <form:form modelAttribute="anuncio" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <iteaching:inputField label="Titulo" name="titulo"/>
            <iteaching:inputField label="Descripcion" name="descripcion"/>
            <iteaching:inputField label="Asignatura" name="asignatura"/>
            <iteaching:inputField label="Precio" name="precio"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${anuncio['new']}">
                        <button class="btn btn-default" type="submit">¡Publicar!</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</iteaching:layout>
