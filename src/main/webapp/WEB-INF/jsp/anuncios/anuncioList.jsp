<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="anuncios">
    <h2>Anuncios</h2>

    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${anuncios}" var="anuncio">
            <tr>
                <td>
                    <c:out value="${anuncio.titulo}"/>
                </td>
                <td>
                    <c:out value="${anuncio.descripcion}"/>
                </td>
                <td>
                    <c:out value="${anuncio.asignatura}"/>
                </td>
                <td>
                    <c:out value="${anuncio.precio}"/>
                </td>
                <td>
                    <c:out value="${anuncio.profesor.firstName} ${anuncio.profesor.lastName}"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a class="btn btn-default"
			href='<spring:url value="/ofertas/new" htmlEscape="true"/>'>Añadir</a>
</petclinic:layout>