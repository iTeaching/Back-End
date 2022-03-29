<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<iteaching:layout pageName="salas">
	<header>
	<nav>
    </nav>
    </header>
    <h2>${sala.nombre}</h2>

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
                    <spring:param name="salaId" value="${sala.id}"/>
                </spring:url>
                <iframe src="${sala.url}" 
                width="1000" height="1000" allow="camera; microphone; fullscreen; speaker; display-capture" ></iframe>
                <!-- <a href=${sala.url} >Ver fuera de la app</a> -->
            </td>
            <sec:authorize access="hasAuthority('alumno')">
            <td>
                <c:out value="${sala.profesor.firstName}"/><c:out value=" "/> <c:out value="${sala.profesor.lastName}"/>    
            </td>
            </sec:authorize>
            <sec:authorize access="hasAuthority('profesor')">
             <td>
             <ul>
             	
            <c:forEach items="${sala.alumnos}" var="alumno">
                 <li><c:out value="${alumno.firstName} "/>  <c:out value="${alumno.lastName}"/></li>
                 <br>
            </c:forEach>
             	
             </ul>

            </td>  
            </sec:authorize>
        </tr>
        </tbody>
    </table>
</iteaching:layout>
