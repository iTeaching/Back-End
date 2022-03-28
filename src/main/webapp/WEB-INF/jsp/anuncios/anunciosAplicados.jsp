<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<iteaching:layout pageName="anuncios">
    <h2>Anuncios</h2>
    <head>
        <meta charset="UTF-8">
      </head>
    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
            <th>Puntuación del profesor</th>
            <th>Valorar</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${anuncios}" var="anuncio">
            <tr>
              
                <td>
                <c:out value="${anuncio.titulo}"/>                  
                </td>
                <td>
                    <c:out value="${anuncio.descripcion}"/>
                </td>
                <td>
                    <c:out value="${anuncio.asignatura}"/>
                </td>
                <td>
                    <c:out value="${anuncio.precio}"/>
                </td>
                <td>
                    <c:out value="${anuncio.profesor.firstName} ${anuncio.profesor.lastName}"/>
                </td>
                 
				<td>
                    <c:if test="${anuncio.profesor.division==0}">
                	<c:out value="Sin evaluar"/>
                	</c:if>
                	<c:if test="${anuncio.profesor.division!=0}">
                	<c:out value="${(anuncio.profesor.puntuacion/anuncio.profesor.division)*2}"/>
                	</c:if>
                </td>
				
				<td>
				
				<spring:url value="/anuncio/{anuncioId}/valoraciones/new"
							var="editUrl">
							<spring:param name="anuncioId" value="${anuncio.id}" />

						</spring:url> <a href="${fn:escapeXml(editUrl)}"
						class="btn btn-outline-warning">Valorar</a>
					</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>