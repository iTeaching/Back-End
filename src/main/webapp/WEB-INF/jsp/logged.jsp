<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>	
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="es">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>iTeaching</title>
<link rel="shortcut icon" href="resources/images/logo.ico">

<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap"
	rel="stylesheet">
<link href="../resources/style/estilos.css" rel="stylesheet"
	type="text/css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style type="text/css">
    <%@include file="bootstrap/css/bootstrap.min.css" %>
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<header>
		<img src="resources/images/logo.png" class="logo">

		<div class="wave" style="height: 150px; overflow: hidden;">
			<svg viewBox="0 0 500 150" preserveAspectRatio="none"
				style="height: 100%; width: 100%;">
                <path
					d="M0.00,49.98 C150.00,150.00 349.20,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
					style="stroke: none; fill: #fff;"></path>
            </svg>
		</div>
	</header>

	<main>
		<sec:authorize access="hasAnyAuthority('profesor')">
			<div class="row justify-content-center row-cols-1 row-cols-md-4 g-3 " style="margin:-16px 100px 0px">
				<div class="col">
					<div class="card h-100" style="width: 15rem; background-color:#FFDD33;">
						<a href="/asignaturas" class="btn btn-outline-link"> <img
							class="card-img-top" style="margin-top:10px;"
							src="resources/images/libros.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Mis asignaturas</h5> 
								<p class="card-text text-dark">Visualiza las asignaturas creadas
									por ti</p>

							</div>
						</a>
					</div>
				</div>
				
				<div class="col" >
					<div class="card h-100" style="width: 15rem; background-color:#FFDD33;">
						<a href="/profesores/miPerfil"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px;"
							src="resources/images/miperfil.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Mi perfil</h5>
								<p class="card-text text-dark">Visualiza el perfil del
									usuario</p>

							</div>
						</a>
					</div>
				</div>
				<div class="card h-100" style="width: 15rem;background-color:#FFDD33; margin-left:30px;">
				<center><img
							class="card-img-top"  style="margin-top:10px";
							src="resources/images/pngegg.png"
							alt="Card image cap">
							<div class="card-body">
								<a href="/promocionarme"><button>Mensual</button></a> <a href="/promocionarmeAnual"><button >Anual</button></a> <a href="/promocionarmeCuatrimestral"><button >Cuatrimestral</button></a>
								<h5 class="card-title text-primary">Mejora tu cuenta </h5>
								<p class="card-text text-dark">Mejora tu cuenta al servicio premium</p>
							</center>
							</div>
				<div class="col">
					<div class="card h-100" style="width: 15rem; background-color:#FFDD33;">
						<a name="logout" value="logout" href="/login"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px;"
							src="resources/images/log-out.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Cerrar sesión</h5>
								<p class="card-text text-dark">Cierra la sesión del usuario</p>

							</div>
						</a>
					</div>
				</div>
			</div>
		</sec:authorize>

		<sec:authorize access="hasAnyAuthority('alumno')">
			<div class="row justify-content-center row-cols-1 row-cols-md-5 g-3">
				<div class="col" style="">
					<div class="card h-100" style="width: 15rem;background-color:#FFDD33; margin-left:30px;">
						<a href="/asignaturas" class="btn btn-outline-link"> <img
							class="card-img-top"  style="margin-top:10px";
							src="resources/images/libros.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Asignaturas </h5>
								<p class="card-text text-dark">Visualiza las asignaturas cursadas</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 15rem; background-color:#FFDD33;margin-left:20px">
						<a href="/ofertas/find" class="btn btn-outline-link"> <img
							class="card-img-top" style="margin-top:10px";
							src="resources/images/buscar.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Buscar ofertas</h5>
								<p class="card-text text-dark">Visualiza las ofertas
									disponibles</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 15rem;background-color:#FFDD33;margin-left:20px">
						<a href="/alumnos/miPerfil"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px";
							src="resources/images/miperfil.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Mi perfil</h5>
								<p class="card-text text-dark">Visualiza el perfil del
									usuario</p>

							</div>
						</a>
					</div>
				</div>
				
				<div class="col">
					<div class="card h-100" style="width: 15rem;background-color:#FFDD33;margin-left:20px">
						<a href="/pagar"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px";
							src="resources/images/pagar.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Pagar</h5>
								<p class="card-text text-dark">Abonar mis clases</p>

							</div>
						</a>
					</div>
				</div>

				<div class="col">
					<div class="card h-100" style="width: 15rem;background-color:#FFDD33;margin-left:20px">
						<a href="/chat"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px"
							src="resources/images/chat.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Chat</h5>
								<p class="card-text text-dark">Empezar conversación</p>

							</div>
						</a>
					</div>
				</div>
			
			<div class="col">
					<div class="card h-100" style="width: 15rem; background-color:#FFDD33;margin-left:20px">
						<a name="logout" value="logout" href="/login"
							class="btn btn-outline-link"> <img class="card-img-top" style="margin-top:10px";
							src="resources/images/log-out.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Cerrar sesión</h5>
								<p class="card-text text-dark">Cierra la sesión del usuario</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col" style="">
					<div class="card h-100" style="width: 15rem;background-color:#FFDD33; margin-left:30px;">
<center><img
							class="card-img-top"  style="margin-top:10px";
							src="resources/images/pngegg.png"
							alt="Card image cap">							

							<div class="card-body">
								
								<a href="/suscribirme"><button>Mensual</button></a> <a href="/suscribirmeAnual"><button >Anual</button></a><a href="/suscribirmeCuatrimestral"><button >Cuatrimestral</button></a>
								<h5 class="card-title text-primary">Mejora tu cuenta </h5>
								<p class="card-text text-dark">Mejora tu cuenta al servicio premium</p>

							</div>
						</center>
					</div>
				</div>
		</sec:authorize>
	</main>


	<footer style="padding-top: 15px; background-color:#FFDD33; margin-top:40px; ">
        <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
    </footer>	
</body>