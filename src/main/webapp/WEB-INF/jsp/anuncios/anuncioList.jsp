<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<iteaching:layout pageName="anuncios">
    <h2>Anuncios</h2>

    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 120px;">Titulo</th>
            <th style="width: 150px; text-align:center;">Descripcion</th>
            <th style="width: 150px; text-align:center;">Asignatura</th>
            <th style="width: 150px; text-align:center;">Precio/Hora</th>
            <th style="width: 150px; text-align:center;">Profesor</th>
            <th style="width: 150px; text-align:center;">Apuntarme</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${anuncios}" var="anuncio">
            <tr>
              
                <td >
                <spring:url value="/anuncio/{anuncioUrl}/" var="anuncioUrl">
                <spring:param name="anuncioUrl" value="${anuncio.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(anuncioUrl)}"> <c:out value="${anuncio.titulo}"/></a>                       
                </td>
                <td align="center">
                    <c:out value="${anuncio.descripcion}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncio.asignatura}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncio.precio}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncio.profesor.firstName} ${anuncio.profesor.lastName}"/>
                </td>
                <td align="center">
                    <spring:url value="/anuncio/{anuncioId}/apply" var="anuncioId">
                    <spring:param name="anuncioId" value="${anuncio.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(anuncioId)}">Test</span>
                    </a>                       
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>