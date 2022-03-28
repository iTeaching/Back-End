<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<iteaching:layout pageName="findOfertas">
    <h2>Encontrar asignaturas</h2>
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
            <th>Apuntarme</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${asignaturas}" var="asignatura">
            <tr>
              
                <td>
                <c:out value="${asignatura.titulo_anuncio}"/>                  
                </td>
                <td>
                    <c:out value="${asignatura.descripcion}"/>
                </td>
                <td>
                    <c:out value="${asignatura.nombre}"/>
                </td>
                <td>
                    <c:out value="${asignatura.precio}"/>
                </td>
                <td>
                    <c:out value="${asignatura.profesor.firstName} ${asignatura.profesor.lastName}"/>
                </td>
                
                <td>
                	<c:if test="${asignatura.profesor.division==0}">
                	<c:out value="Sin evaluar"/>
                	</c:if>
                	<c:if test="${asignatura.profesor.division!=0}">
                	<c:out value="${(asignatura.profesor.puntuacion/asignatura.profesor.division)*2}"/>
                	</c:if>
                    
                </td>
                <td>
                    <spring:url value="/asignaturas/{asignaturaId}/apply" var="asignaturaId">
                    <spring:param name="asignaturaId" value="${asignatura.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(asignaturaId)}"><img src="..\resources\images\icons8-añadir-30.png"></a>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>