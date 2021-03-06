<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<iteaching:layout pageName="asignaturas">
	<header>
	<nav>
    </nav>
    </header>
    <h2>${asignatura.nombre}</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;"></th>
			<sec:authorize access="hasAuthority('alumno')">
            <th style="width: 120px">Profesor</th>
            </sec:authorize>
            <sec:authorize access="hasAuthority('profesor')">
            <th style="width: 120px">Alumnos</th>
            </sec:authorize>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <spring:url value="/salas/details" var="salaUrl">
                    <spring:param name="salaId" value="${asignatura.id}"/>
                </spring:url>
                <iframe src="${asignatura.url}" 
                width="1000" height="1000" allow="camera; microphone; fullscreen; speaker; display-capture" ></iframe>
                <!-- <a href=${sala.url} >Ver fuera de la app</a> -->
            </td>
            <sec:authorize access="hasAuthority('alumno')">
            <td>
	            <c:choose>
	            	<c:when test="${asignatura.profesor.user.enabled==true }">
	            		<c:out value="${asignatura.profesor.firstName}"/><c:out value=" "/> <c:out value="${asignatura.profesor.lastName}"/>
	            	</c:when>
	            	<c:otherwise>
	            		<c:out value="El profesor se ha dado de baja de la plataforma o está temporalmente no disponible"/>
	            	</c:otherwise>
	            </c:choose>
            </td>
            </sec:authorize>
            <sec:authorize access="hasAuthority('profesor')">
             <td>
             <ul>
             	
            <c:forEach items="${asignatura.alumnos}" var="alumno">
	            <c:if test="${alumno.user.enabled==true }">
	                 <li><c:out value="${alumno.firstName} "/>  <c:out value="${alumno.lastName}"/></li>
	                 <br>
	            </c:if>
            </c:forEach>
             	
             </ul>

            </td>  
            </sec:authorize>
        </tr>
        </tbody>
    </table>
</iteaching:layout>
