<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>



<petclinic:layout pageName="listaValoraciones">
<h2>Numero de Valoraciones </h2>
	 
        
    <table id="usersTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 150px;">Alumno</th>
            <th style="width: 150px;">Puntuacion</th>
            <th style="text-align: center;">Comentario</th>
            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${valoraciones}" var="valoracion">
            <tr>
                <td>${valoracion.alumno.firstName}</td>
                <td>${valoracion.puntuacion*2}</td>
                <td style="text-align: center;">${valoracion.comentario}</td>
                
                
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </petclinic:layout>