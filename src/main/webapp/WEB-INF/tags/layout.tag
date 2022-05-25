<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>

<!doctype html>
<html>
<iteaching:htmlHeader/>
<head>
<link rel="icon" href="/resources/images/logo.ico" type="image/x-icon">
<meta charset="UTF-8">
</head>
<body>
<iteaching:bodyHeader menuName="${pageName}"/>

<div class="container-fluid">
    <div class="container xd-container">
	<c:if test="${not empty message}" >
	<div class="alert alert-${not empty messageType ? messageType : 'info'}" role="alert">
  		<c:out value="${message}"></c:out>
   		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
    		<span aria-hidden="true">&times;</span>
  		</button> 
	</div>
	</c:if>

        <jsp:doBody/>

        <iteaching:pivotal/>
    </div>
</div>
<iteaching:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
