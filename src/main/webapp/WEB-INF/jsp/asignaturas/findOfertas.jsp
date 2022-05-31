<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<iteaching:layout pageName="findOfertas">
    <h2>Encontrar asignaturas</h2>
    <head>
        <meta charset="UTF-8">
      </head>

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
		<button type="submit" class="btn btn-primary" style="background-color:#FFDD33; border-color:#FFDD33">Buscar</button>
		</div>

	</form>
	</div>
    <c:if test="${longitud!=0}">    
    <br>
    <h1>Mejores clases</h1>
    <table id="" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
            <th>Puntuación del profesor</th>
            <th>Apuntarme</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${asignaturasPromo}" var="asignaturasPromo">
            <tr>

                <td>
                <c:out value="${asignaturasPromo.titulo_anuncio}"/>                  
                </td>
                <td>
                    <c:out value="${asignaturasPromo.descripcion}"/>
                </td>
                <td>
                    <c:out value="${asignaturasPromo.nombre}"/>
                </td>
                <td>
                    <c:out value="${asignaturasPromo.precio}"/>
                </td>
                <td>
                <a href="/profesor/${asignatura.profesor.id}/perfil">
                    <c:out value="${asignaturasPromo.profesor.firstName} ${asignatura.profesor.lastName}"/></a>
                </td>

                <td>
                	<c:if test="${asignaturasPromo.profesor.division==0}">
                	<a href="/asignatura/${asignatura.id}/valoraciones/profesor/${asignatura.profesor.id}">
                    <c:out value="Sin evaluar"/></a>
                	</c:if>
                	<c:if test="${asignaturasPromo.profesor.division!=0}">
                	<a href="/asignatura/${asignatura.id}/valoraciones/profesor/${asignatura.profesor.id}">
                	<c:out value="${(asignatura.profesor.puntuacion/asignatura.profesor.division)*2}"/></a>
                	</c:if>

                </td>
                <td>
                    <spring:url value="/asignaturas/{asignaturaId}/apply" var="asignaturaId">
                    <spring:param name="asignaturaId" value="${asignatura.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(asignaturaId)}"><img src="..\resources\images\icons8-añadir-30.png" alt="+"></a>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
    <br>
    <h1>Todos los anuncios</h1>
    <br>

    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
            <th>Puntuación del profesor</th>
            <th>Apuntarme</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${asignaturas}" var="asignatura">
            <tr>

                <td>
                <c:out value="${asignatura.titulo_anuncio}"/>                  
                </td>
                <td>
                    <c:out value="${asignatura.descripcion}"/>
                </td>
                <td>
                    <c:out value="${asignatura.nombre}"/>
                </td>
                <td>
                    <c:out value="${asignatura.precio}"/>
                </td>
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
                    <spring:url value="/asignaturas/{asignaturaId}/apply" var="asignaturaId">
                    <spring:param name="asignaturaId" value="${asignatura.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(asignaturaId)}"><img src="..\resources\images\icons8-añadir-30.png" alt="+"></a>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</iteaching:layout> 