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
			<div class="row justify-content-center row-cols-1 row-cols-md-4 g-3 ">
				<div class="col">
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a href="/asignaturas" class="btn btn-outline-link"> <img
							class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/157/157897.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Mis asignaturas</h5>
								<p class="card-text text-dark">Visualiza las asignaturas creadas
									por ti</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a href="/asignaturas/new" class="btn btn-outline-link"> <img
							class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/181/181621.png"
							alt="Card image cap">

							<div class="card-body">
								<h5 class="card-title text-primary">Crear asignatura</h5>
								<p class="card-text text-dark">Crea una nueva asignatura</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a href="/profesores/miPerfil"
							class="btn btn-outline-link"> <img class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/1250/1250689.png"
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
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a name="logout" value="logout" href="/login"
							class="btn btn-outline-link"> <img class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/56/56805.png"
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
				<div class="col">
					<div class="card h-100" style="width: 18rem;background-color:#dab305;">
						<a href="/asignaturas" class="btn btn-outline-link"> <img
							class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/157/157897.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Asignaturas</h5>
								<p class="card-text text-dark">Visualiza las asignaturas a 
								las que te has suscrito</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a href="/ofertas/find" class="btn btn-outline-link"> <img
							class="card-img-top"
							src="http://assets.stickpng.com/thumbs/59cfc4d2d3b1936210a5ddc7.png"
							alt="Card image cap">

							<div class="card-body">
								<h5 class="card-title text-primary">Ver ofertas</h5>
								<p class="card-text text-dark">Visualiza las ofertas
									disponibles</p>

							</div>
						</a>
					</div>
				</div>
				<div class="col">
					<div class="card h-100" style="width: 18rem;background-color:#dab305;">
						<a href="/alumnos/miPerfil"
							class="btn btn-outline-link"> <img class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/1250/1250689.png"
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
					<div class="card h-100" style="width: 18rem;background-color:#dab305;">
						<a href="/pagar"
							class="btn btn-outline-link"> <img class="card-img-top"
							src="https://i.pinimg.com/originals/4c/be/11/4cbe116c2ba74636d40d14490d306903.png"
							alt="Card image cap">
							<div class="card-body">
								<h5 class="card-title text-primary">Pagar</h5>
								<p class="card-text text-dark">Abonar mis clases</p>

							</div>
						</a>
					</div>
				</div>
			
			<div class="col">
					<div class="card h-100" style="width: 18rem; background-color:#dab305;">
						<a name="logout" value="logout" href="/login"
							class="btn btn-outline-link"> <img class="card-img-top"
							src="https://cdn-icons-png.flaticon.com/512/56/56805.png"
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
	</main>


	<footer style="padding-top: 15px;">
		<h2 class="titulo-final">&copy; 2022,iTeaching</h2>
	</footer>
</body>