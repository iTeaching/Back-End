<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="avatar">

	<div>
		<h2>Documentos</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th width="60%">Nombre</th>
					<th width="40%">Descargar</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${docs}" var="doc">
					<tr>

						<td><c:out value="${doc.docName}" /></td>
						<td><spring:url value="/downloadFile/{fileId}" var="fileId">
								<spring:param name="fileId" value="${doc.id}" />
							</spring:url> <a href="${fn:escapeXml(fileId)}"><img alt="Descargar"
								src="resources/images/descargar.png" width="20px"> </a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<form:form enctype="multipart/form-data" class="form-horizontal"
		action="/asignatura/${asignatura.id}/files" id="upload-file-form">

		<div class="form-group">
			<input type="file" name="files" class="btn btn-warning" multiple required />
		</div>
		
		<div class="form-group">
			<button class="btn btn-default" type="submit">Subir archivos</button>
		</div>
		<c:if test="${not empty errorMessage}">

		<div class="alert alert-danger" role="alert">
			${errorMessage}
		</div>
		</c:if>
	</form:form>
	
</petclinic:layout>