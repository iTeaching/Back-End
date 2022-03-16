<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<iteaching:layout pageName="salas">
    <h2>Mis Salas</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre</th>
			<sec:authorize access="hasAuthority('alumno')">
            <th style="width: 120px">Profesor</th>
            </sec:authorize>
            <sec:authorize access="hasAuthority('profesor')">
            <th style="width: 120px">Alumnos</th>
            </sec:authorize>
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
                <sec:authorize access="hasAuthority('alumno')">
                <td>
                    <c:out value="${sala.profesor.firstName}"/><c:out value=" "/> <c:out value="${sala.profesor.lastName}"/>    
                </td>
                </sec:authorize>
                <sec:authorize access="hasAuthority('profesor')">
                 <td>                    
                <c:forEach items="${alumnos}" var="alumno">
                     <c:out value="${alumno.firstName} "/>  <c:out value="${alumno.lastName}"/> 
                </c:forEach>
                </td>  
                </sec:authorize>           
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>
