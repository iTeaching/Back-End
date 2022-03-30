<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<iteaching:layout pageName="asignaturas">
	
	<nav>
    </nav>
    
    
    <h2>Mis Asignaturas</h2>
	<sec:authorize access="hasAnyAuthority('alumno')">
	 <div class=row>
      <div class="col-sm-4" style="display: flex; align-items: center; flex-direction: column">
      <form  action="/ofertas/findAsignatura/" method="get">

		<div class="form-group">
			<label></label> 


			<input
				type="text" class="form-control" id="asignaturaBuscar" name="asignaturaBuscar" style="width:300px"
				placeholder="Introduce la asignatura">
			</div>
		</div>
		<div class="col-sm-8" style="margin-top:23px; align-items: left; flex-direction: column">
		<button type="submit" class="btn btn-primary" style="background-color:#dab305; border-color:#dab305">Submit</button>
		</div>
		

	</form>
	</div>
	</sec:authorize>
    <table id="asignaturaTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Nombre de la asignatura</th>
            <th style="width: 120px">Acción</th>
            <sec:authorize access="hasAnyAuthority('alumno')">
            <th style="width: 120px">Puntuación del profesor</th>
            <th style="width: 120px">Valorar</th>
            </sec:authorize>
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
                    <c:if test="${asignatura.profesor.division==0}">
                	<c:out value="Sin evaluar"/>
                	</c:if>
                	<c:if test="${asignatura.profesor.division!=0}">
                	<c:out value="${(asignatura.profesor.puntuacion/asignatura.profesor.division)*2}"/>
                	</c:if>
                </td>
                <td>
				
				<spring:url value="/asignatura/{asignaturaId}/valoraciones/new"
							var="editUrl">
							<spring:param name="asignaturaId" value="${asignatura.id}" />

						</spring:url> <a href="${fn:escapeXml(editUrl)}"
						class="btn btn-outline-warning">Valorar</a>
					</td>
				</sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
 
</iteaching:layout>
