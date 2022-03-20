<%@ tag pageEncoding='UTF-8' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>	
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-kQtW33rZJAHjgefvhyyzcGF3C5TFyBQBA13V1RKPf4uH+bwyzQxZ6CmMZHmNBEfJ" crossorigin="anonymous"></script>
<script src="http://code.jquery.com/jquery.js"></script>


<style type="text/css">
    <%@include file="../jsp/bootstrap/css/bootstrap.min.css" %>
</style>

<script type="text/javascript">
$(document).ready(function() {
    $('.dropdown-toggle').dropdown()
});
</script> 



<%--
<nav>
<ul class="nav navbar-nav">

				<sec:authorize access="hasAnyAuthority('profesor')">
				<iteaching:menuItem active="${name eq 'anuncios'}" url="/ofertas/misOfertas">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Mis Anuncios</span>
				</iteaching:menuItem>
				<iteaching:menuItem active="${name eq 'anuncios'}" url="/salas/new">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Crear Sala</span>
				</iteaching:menuItem>
				<iteaching:menuItem active="${name eq 'anuncios'}" url="/salas">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Mis Salas</span>
				</iteaching:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAnyAuthority('alumno')">
				<iteaching:menuItem active="${name eq 'anuncios'}" url="">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Ver ofertas</span>
				</iteaching:menuItem>
				
				</sec:authorize>
				
				<sec:authorize access="!isAuthenticated()">
				<iteaching:menuItem active="${name eq 'anuncios'}" url="/login">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Login</span>
				</iteaching:menuItem>
				</sec:authorize>
				
				<sec:authorize access="!isAuthenticated()">
				<iteaching:menuItem active="${name eq 'anuncios'}" url="mailto:iteaching.sa@gmail.com​"
					title="Contacto">
					<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
					<span>Contacto</span>
				</iteaching:menuItem>
				</sec:authorize>
				
				
			</ul>
        </nav>
        <img src="../resources/images/logo.png" class="logo">
        
        --%>
        
        
        
        
<%--

		<nav class="navbar navbar-light" style="background-color: #dab305;">
  		<a class="navbar-brand" href="#">iTeaching</a>
  		
  		
   		<ul class="navbar-nav">
    
    	<sec:authorize access="hasAnyAuthority('profesor')">
    
				<li class="nav-item active">
					<a class="nav-link" href="/ofertas/misOfertas">Mis Anuncios</a>
				</li>
				
				<li class="nav-item active">
					<a class="nav-link" href="/salas/new">Crear Sala</a>
				</li>
				
				<li class="nav-item active">
					<a class="nav-link" href="/salas">Mis Salas</a>
				</li>
		</sec:authorize>
    
    			
    	<sec:authorize access="hasAnyAuthority('alumno')">
				
				<li class="nav-item active">
					<a class="nav-link" href="">Ver ofertas</a>
				</li>
				
		</sec:authorize>
    
    
    	<sec:authorize access="!isAuthenticated()">
				
				<li class="nav-item active">
					<a class="nav-link" href="mailto:iteaching.sa@gmail.com">Contacto</a>
				</li>
				
		</sec:authorize>
    
    </ul>
 
</nav>
		
<img src="../resources/images/logo.png" class="logo">
  --%>
        


<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #dab305;"> 
  <a class="navbar-brand" href="/logged"><img src="../resources/images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">iTeaching</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <sec:authorize access="hasAnyAuthority('profesor')">
    
				<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Salas</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="background-color: #dab305;">
          		<a class="dropdown-item" href="/salas/new">Crear Salas</a>
          		<a class="dropdown-item" href="/salas">Mis salas</a>
         		
       			 </div>
      			</li>
      			
      			
      			<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">Anuncios</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown2" style="background-color: #dab305;">
          		<a class="dropdown-item" href="#">Crear Anuncios</a>
          		<a class="dropdown-item" href="/ofertas/misOfertas">Mis ofertas</a>
         		
       			 </div>
      			</li>
      			
      			<li class="nav-item active">
					<a class="nav-link" href="#">Mi perfil</a>
				</li>
      			
				<li class="nav-item active">
					<a class="nav-link" href="#">Cerrar sesión</a>
				</li>
				
		</sec:authorize>
    
    			
    	<sec:authorize access="hasAnyAuthority('alumno')">
				
				<li class="nav-item active">
					<a class="nav-link" href="">Ver salas</a>
				</li>
				
				
				<li class="nav-item active">
					<a class="nav-link" href="">Ver ofertas</a>
				</li>
				
				
				<li class="nav-item active">
					<a class="nav-link" href="#">Mi perfil</a>
				</li>
      			
				<li class="nav-item active">
					<a class="nav-link" href="#">Cerrar sesión</a>
				</li>
				
				
				
				
		</sec:authorize>
    
    
    	<sec:authorize access="!isAuthenticated()">
				
				<li class="nav-item active">
					<a class="nav-link" href="mailto:iteaching.sa@gmail.com">Contacto</a>
				</li>
				
		</sec:authorize>
    </ul>
  </div>
</nav>









  