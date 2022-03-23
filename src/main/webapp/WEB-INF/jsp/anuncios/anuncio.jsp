<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<iteaching:layout pageName="anuncios">

   <h2>Anuncio de <c:out value="${anuncio.titulo}"/></h2> 
  
  
 
    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
        </tr>
        </thead>
        <tbody>
            <tr>
              
                <td>
                <spring:url value="/anuncio/{anuncioUrl}/" var="anuncioUrl">
                <spring:param name="anuncioUrl" value="${anuncio.id}"/>
                </spring:url>
                <c:out value="${anuncio.titulo}"/></a>                       
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
            </tr>
        </tbody>
    </table>
</iteaching:layout>
