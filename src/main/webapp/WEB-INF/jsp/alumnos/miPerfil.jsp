<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="Mi perfil">
    <div>
    	<p>Nombre: ${alumno.firstName} ${alumno.lastName }</p>
    	<p>Teléfono: ${alumno.telephone}</p>
    	<p>Correo: ${alumno.email}</p>
    	<p><a href="/alumnos/${alumno.id}/edit">Editar perfil</a></p>
    </div>
</petclinic:layout>