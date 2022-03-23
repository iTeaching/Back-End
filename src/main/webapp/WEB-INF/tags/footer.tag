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

<footer style=" background: #F9DA5C; height:25px;">
	<div class="contenedor-footer"style=" margin-top:-50px;">
		<div style=" font-size:26px;">
			<img src="../resources/images/gmail.svg" class="footer_img"style=" margin-left:-600px;">iteaching.sa@gmail.com
		</div>
	
		<h2 class="titulo-final" style="margin-right:-600px; margin-top:50px; font-size:18px;">&copy; 2022,iTeaching</h2>
	</div>
	
</footer>