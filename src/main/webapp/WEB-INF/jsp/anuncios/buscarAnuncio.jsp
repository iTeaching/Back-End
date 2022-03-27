<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<iteaching:layout pageName="buscarAnuncios">
    <h2>Anuncios encontrados</h2>
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
            <th>Apuntarme</th>
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
                    <spring:url value="/anuncio/{anuncioId}/apply" var="anuncioId">
                    <spring:param name="anuncioId" value="${anuncio.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(anuncioId)}"><img src="..\resources\images\icons8-añadir-30.png"></span>
                    </a>                       
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>