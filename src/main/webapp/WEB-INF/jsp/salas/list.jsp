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
    <h2>Mis Salas</h2>

    <table id="salasTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de la sala</th>
            <th style="width: 120px">Acción</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${salas}" var="sala">
            <tr>
                <td>
                    <c:out value="${sala.nombre}"/>
                </td>
                 <td>
                     
					<a href="/salas/${sala.id }"><img src="resources/images/video.svg"></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout>
