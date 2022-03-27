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
    <h2>Mis Asignaturas</h2>

    <table id="asignaturaTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de la asignatura</th>
            <th style="width: 120px">Acción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${asignaturas}" var="asignatura">
            <tr>
                <td>
                    <c:out value="${asignatura.nombre}"/>
                </td>
                 <td>
                     
					<a href="/asignaturas/${asignatura.id }"><img src="resources/images/video.svg"></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
 
</iteaching:layout>
