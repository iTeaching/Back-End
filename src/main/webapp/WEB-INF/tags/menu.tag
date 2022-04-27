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

<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #FFDD33;"> 
  <a class="navbar-brand" href="/logged"><img src="../resources/images/logo.png" width="30" height="30" class="d-inline-block align-top" alt="">iTeaching</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <sec:authorize access="hasAnyAuthority('profesor')">
    
				<li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Asignaturas</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="background-color: #FFDD33;">
          		<a class="dropdown-item" href="/asignaturas/new">Crear asignatura</a>
          		<a class="dropdown-item" href="/asignaturas">Mis asignaturas</a>
         		
       			 </div>
      			</li>
      			
      			
      			<!-- <li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">Anuncios</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown2" style="background-color: #dab305;">
          		<a class="dropdown-item" href="/ofertas/new">Crear Anuncios</a>
          		<a class="dropdown-item" href="/ofertas/misOfertas">Mis ofertas</a>
         		
       			 </div>
      			</li> -->
      			
      			<li class="nav-item active">
					<a class="nav-link" href="/profesores/miPerfil">Mi perfil</a>
				</li>
      			
				<li class="nav-item active">
					<a name="logout" value="logout" class="nav-link" href="/login">Cerrar sesión</a>
				</li>
				
		</sec:authorize>
    
    			
    	<sec:authorize access="hasAnyAuthority('alumno')">
				
				<li class="nav-item active">
					<a class="nav-link" href="/asignaturas">Mis asignaturas</a>
				</li>
				
				<li class="nav-item active">
					<a class="nav-link" href="/ofertas/find">Ver ofertas</a>
				</li>
				
				
			<!-- <li class="nav-item dropdown">
        		<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button" data-bs-toggle="dropdown" aria-expanded="false">Anuncios</a>
        		<div class="dropdown-menu" aria-labelledby="navbarDropdown2" style="background-color: #dab305;">
          		<a class="dropdown-item" href="/ofertas/find">Ver Ofertas</a>
          		<a class="dropdown-item" href="/anuncios/anunciosAplicados">Anuncios aplicados</a> -->
				
				<li class="nav-item active">
					<a class="nav-link" href="/alumnos/miPerfil">Mi perfil</a>
				</li>
				
				<li class="nav-item active">
					<a class="nav-link" href="/pagar">Pagar</a>
				</li>
      			<li class="nav-item active">
					<a class="nav-link" href="/GarantiaDePrivacidad">Garantia De Privacidad</a>
				</li>
				<li class="nav-item active">
					<a name="logout" value="logout" class="nav-link" href="/login">Cerrar sesión</a>
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









  