<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="Mi perfil">
    <div>
    	<p>Nombre: ${profesor.firstName} ${profesor.lastName }</p>
    	<p>Teléfono: ${profesor.telephone}</p>
    	<p>Correo: ${profesor.email}</p>
    	<span>Asignaturas
    		<ul>
    		<c:forEach var="anuncio" items="${profesor.anuncios}">
    			<li>${anuncio.asignatura}</li>
			</c:forEach>
    		</ul>
    	</span>
    	<p><a href="/profesores/${profesor.id}/edit">Editar perfil</a></p>
    </div>
</petclinic:layout>