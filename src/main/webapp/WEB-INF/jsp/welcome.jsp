<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html lang="es">

<head>
    <meta charset="UTF-8">    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <!-- Google Tag Manager -->
    <script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
    new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
    j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
    'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
    })(window,document,'script','dataLayer','GTM-WRJT44J');</script>
    <!-- End Google Tag Manager -->

    <title>iTeaching</title>
    <link rel="shortcut icon" href="resources/images/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 
	<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css"> 
    <link href="../resources/style/cookies.css" rel="stylesheet" type="text/css"> 
    <script async src="https://www.googletagmanager.com/gtag/js?id=G-K4BDBJD0X0"></script>


    <!-- Google Tag Manager (noscript) -->
    <noscript><iframe src="https://www.googletagmanager.com/ns.html?id=GTM-WRJT44J"
    height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
    <!-- End Google Tag Manager (noscript) -->
    <header>
        <nav>
        	<a href="/login"><button class="nav_button" data-hover="¡Bienvenido!"><div>Aprende ya</div></button></a>
            <a href="mailto:iteaching.sa@gmail.com​"><button class="nav_button" data-hover="¡Hola!"><div>Contáctanos</div></button> </a>
            <h1 class="eslogan">UNA NUEVA FORMA DE CONECTAR CON EL PROFESOR</h1>
        </nav>
        <img src="resources/images/logo.png" class="logo">

        <div class="wave" style="height: 150px; overflow: hidden;"><svg viewBox="0 0 500 150" preserveAspectRatio="none"
                style="height: 100%; width: 100%;">
                <path d="M0.00,49.98 C150.00,150.00 349.20,-50.00 500.00,49.98 L500.00,150.00 L0.00,150.00 Z"
                    style="stroke: none; fill: #fff;"></path>
            </svg></div>
    </header>
    <main>
        <section class="contenedor sobre-nosotros">
            <h2 class="titulo">Nuestro objetivo</h2>
            <div class="contenedor-sobre-nosotros">
                <img src="resources/images/clases.png" alt="" class="imagen-sobre-nosotros">
                <div class="contenido-textos">
                    <h3><span>1</span>¿De qué sirve tener grandes conocimientos si no los compartes?</h3>
                    <p>Por este motivo hemos decidido crear esta aplicación, ya que pensamos que todos podemos aportar 
                        nuestro granito de arena para que logres un mejor aprendizaje</p>
                    <h3><span>2</span>Crear una comunidad de aprendizaje</h3>
                    <p>Pretendemos crear conexiones fluidas entres profesores y estudiantes, con la 
                        finalidad de poder ayudar a mejorar el aprendizaje. </p>
                </div>
            </div>
        </section>
        <section class="servicios">
            <div class="contenedor">
                <h2 class="titulo">Servicios</h2>
                <div class="serv-port">
                    <div class="imagen-port">
                        <img src="resources/images/img1.jpg" alt="">
                        <div class="hover-imagen">
                            <p class="textos">Posibilidad de usar aulas virtuales</p>
                        </div>
                    </div>
                    <div class="imagen-port">
                        <img src="resources/images/img2.jpg" alt="">
                        <div class="hover-imagen">
                            <p class="textos">Grupos para impartir asignaturas</p>
                        </div>
                    </div>
                    <div class="imagen-port">
                        <img src="resources/images/img3.jpg" alt="">
                        <div class="hover-imagen">
                            <p class="textos">Posibilidad de realizar videoconferencias para impartir clases</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section class="suscripciones">
            <div class="contenedor">
                <h2 class="titulo">Plan de Suscripciones</h2>
                <div class="columns">
			  <ul class="price">
			    <li class="header">Básico</li>
			    <li class="grey">GRATIS</li>
			    <li>Descarga de archivos</li>
			    <li>Acceso al chat de profesores</li>
                <li><b>Alumnos</b></li>
                <li>Pago de clases particulares con un 7% de comisión</li>
                <li><b>Profesor</b></li>
                <li>Publica anuncios<br></li>
			    <li class="hidden_text" style="height: 73.5px;">#</li>

			    <li class="grey"><a href="/login" class="empieza_button">Empieza ya!</a></li>
			  </ul>
			</div>
			<div class="columns">
			  <ul class="price">
			    <li class="header">Mensual</li>
			    <li class="grey">5,00€</li>
			    <li>Descarga de archivos</li>
			    <li>Acceso al chat de profesores</li>
                <li><b>Alumnos</b></li>
                <li>Pago de clases particulares con un 0% de comisión</li>
                <li><b>Profesor</b></li>
                <li>Publica anuncios de manera favorecida</li>
			    <li class="horas_mensuales">*Ventajas durante un mes
                </li>
			    <li class="grey"><a href="/login" class="empieza_button">Empieza ya!</a></li>
			  </ul>
			</div>
			<div class="columns">
			  <ul class="price">
			    <li class="header">Anual</li>
			    <li class="grey">40,00€</li>
			    <li>Descarga de archivos</li>
			    <li>Acceso al chat de profesores</li>
                <li><b>Alumnos</b></li>
                <li>Pago de clases particulares con un 0% de comisión</li>
                <li><b>Profesor</b></li>
                <li>Publica anuncios de manera favorecida</li>
			    <li class="horas_mensuales">*Ventajas durante un año</li>
			    <li class="grey"><a href="/login" class="empieza_button">Empieza ya!</a></li>
			  </ul>
			</div>
            </div>
        </section>

        <div class="aviso-cookies" id="aviso-cookies">
            <img class="galleta" src="resources/images/cookie.svg" alt="Cookie">
            <h3 class="titulo">Cookies</h3>
            <p class="parrafo">Utilizamos cookies propias y de terceros para mejorar nuestros servicios.</p>
            <button class="boton" id="btn-aceptar-cookies">De acuerdo</button>
            <a class="enlace" href="/GarantiaDePrivacidad">Aviso de Cookies</a>
        </div>
        <div class="fondo-aviso-cookies" id="fondo-aviso-cookies"></div>
    
        <script src="../resources/js/aviso-cookies.js"></script>
  
    <footer>
        <div class="contenedor-footer">
            <div class="footer-contact--email">
                <img src="resources/images/gmail.svg" class="footer_img">
            </div>
        </div>
        <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
    </footer>

</body>
