<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="avatar">

	<form:form enctype="multipart/form-data" class="form-horizontal"
		action="/uploadFiles" id="upload-file-form">
		<div class="form-group">
			<input type="file" name="files" multiple required/>
		</div>
		<div class="form-group">
			<button class="btn btn-default" type="submit">Subir archivos</button>
		</div>
	</form:form>
	
	<div>
     <h3>List of Documents</h3>
     <table>
       <thead>
         <tr>
           <th>Name</th>
           <th>Download Link</th>
         </tr>
       </thead>
       <tbody>
       <c:forEach items="${docs}" var="doc">
       <tr>
              
                <td>
                <c:out value="${doc.docName}"/>                  
                </td>
                <td>
                   <spring:url value="/downloadFile/{fileId}" var="fileId">
                   <spring:param name="fileId" value="${doc.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(fileId)}">Download
                    </a>                       
                </td>
         </tr>
         </c:forEach>
       </tbody>
     </table>
   </div>
	
</petclinic:layout>