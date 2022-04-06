<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@page pageEncoding="UTF-8"%>

<petclinic:layout pageName="avatar">
	<h2>Edit ${alumno.id}</h2>
	<form:form enctype="multipart/form-data" class="form-horizontal"
		action="/alumnos/miPerfil/changeAvatar" id="edit-avatar-form">
		<div class="form-group">
			<input type="file" name="avatar" />
		</div>
		<div class="form-group">
			<button class="btn btn-default" type="submit">Actualiza tu avatar</button>
		</div>
	</form:form>
</petclinic:layout>