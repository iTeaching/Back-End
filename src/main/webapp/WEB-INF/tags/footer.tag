<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQuery" />
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js"
	var="jQueryUiCore" />
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"
	var="bootstrapJs" />
<script src="${bootstrapJs}"></script>
<head>
  <meta charset="UTF-8">
</head>

<footer style="background-color: #FFDD33; margin-bottom:0px;margin-top:290px;">
	<div class="contenedor-footer" style="background-color: #FFDD33;margin-bottom:0px;margin-top:156px;">
		<div class="footer-contact--email" >
			<img src="../resources/images/gmail.svg" class="footer_img">
		</div>
	</div>
	<h2 class="titulo-final">&copy; 2022,iTeaching</h2>
</footer>