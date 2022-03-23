<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="anuncios">
    <h2>Anuncios</h2>

    <table id="ownersTable" class="table table-striped">
        <thead>
        <tr>
        	<th style="width: 120px;">Profesor</th>
            <th style="width: 150px; text-align:center;">Titulo</th>
            <th style="width: 200px; text-align:center;">Descripcion</th>
            <th style="width: 120px; text-align:center;">Asignatura</th>
            <th style="width: 120px; text-align:center;">Precio</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${anuncios}" var="anuncios">
            <tr>
            	<td >
                    <c:out value="${anuncios.profesor.firstName}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncios.titulo}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncios.descripcion}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncios.asignatura}"/>
                </td>
                <td align="center">
                    <c:out value="${anuncios.precio}"/>
                </td>
                
   
            </tr>
        </c:forEach>
        </tbody>
    </table>
</petclinic:layout>