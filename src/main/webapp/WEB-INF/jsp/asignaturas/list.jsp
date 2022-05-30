<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<iteaching:layout pageName="asignaturas">
	<nav>
    </nav>
    <h2>Mis Asignaturas</h2>
    <table id="asignaturaTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de la asignatura</th>
            <th style="width: 120px">Clase</th>
            <sec:authorize access="hasAnyAuthority('alumno')">
            <th style="width: 120px">Profesor</th>
            <th style="width: 120px">Puntuación del profesor</th>
            <th style="width: 120px">Valorar</th>
            <th style="width: 120px">Tutoría</th>
            </sec:authorize>
            <th style="width: 120px">Ver archivos</th>
            <th style="width: 120px">Chat</th>
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
                <sec:authorize access="hasAnyAuthority('alumno')">
                <td>
                 <a href="/profesor/${asignatura.profesor.id}/perfil">
                    <c:out value="${asignatura.profesor.firstName} ${asignatura.profesor.lastName}"/></a>
                </td>
                <td>
                    <c:if test="${asignatura.profesor.division==0}">
                	<a href="/asignatura/${asignatura.id}/valoraciones/profesor/${asignatura.profesor.id}">
                    <c:out value="Sin evaluar"/></a>
                	</c:if>
                	<c:if test="${asignatura.profesor.division!=0}">
                	<a href="/asignatura/${asignatura.id}/valoraciones/profesor/${asignatura.profesor.id}">
                	<c:out value="${(asignatura.profesor.puntuacion/asignatura.profesor.division)*2}"/></a>
                	</c:if>
                </td>
                <td>
				<spring:url value="/asignatura/{asignaturaId}/valoraciones/new"
							var="editUrl">
							<spring:param name="asignaturaId" value="${asignatura.id}" />

						</spring:url> <a href="${fn:escapeXml(editUrl)}"
						class="btn btn-outline-warning">Valorar</a>
					</td>
                    <td>
                        <spring:url value="alumnos/nuevaClase/{asignaturaId}"
                                    var="editUrl">
                                    <spring:param name="asignaturaId" value="${asignatura.id}" />
        
                                </spring:url> <a href="${fn:escapeXml(editUrl)}"
                                class="btn btn-outline-warning">Solicitar</a>
                            </td>
				</sec:authorize>
				 <td>
				<spring:url value="/asignatura/{asignaturaId}/files"
							var="editUrl">
							<spring:param name="asignaturaId" value="${asignatura.id}" />

						</spring:url> <a href="${fn:escapeXml(editUrl)}"
						class="btn btn-outline-warning">Archivos</a>
					</td>
					 <td>
				<spring:url value="/chatProf/{asignaturaId}"
							var="editUrl">
							<spring:param name="asignaturaId" value="${asignatura.id}" />

						</spring:url> <a href="${fn:escapeXml(editUrl)}"
						class="btn btn-outline-warning">Chat</a>
					</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</iteaching:layout>
