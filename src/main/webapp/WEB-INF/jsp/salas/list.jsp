<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="salas">
    <h2>Mis Salas</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
            <th style="width: 120px">Profesor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${salas}" var="sala">
            <tr>
                <td>
                    <spring:url value="/salas/details" var="salaUrl">
                        <spring:param name="salaId" value="${sala.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(salaUrl)}"><c:out value="${sala.nombre}"/></a>
                </td>
                <td>
                    <c:out value="${sala.profesor.firstName}"/><c:out value=" "/> <c:out value="${sala.profesor.lastName}"/>    
                </td>               
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>
