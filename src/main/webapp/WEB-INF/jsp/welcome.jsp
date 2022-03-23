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
    <title>iTeaching</title>
    <link rel="shortcut icon" href="resources/images/logo.ico">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,700,800&display=swap" rel="stylesheet"> 
	<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css">
</head>

<body>
    <header>
        <nav>
            <a href="/login">¡Aprende ya!</a>
            <a href="mailto:iteaching.sa@gmail.com​">Contáctanos </a>
            <h1 class="eslogan">Una nueva forma de conectar con el profesor</h1>
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
                <div class="serv-port1">
                    <div class="imagen-port1">
                        <img src="resources/images/plan-basico.png" alt="">
                        <div class="hover-imagen1">
                            <p class="textosS">Básico</p>
                            <p class="textosS">GRATIS</p>
                        </div>
                    </div>
                    <div class="imagen-port1">
                        <img src="resources/images/plan-es.png" alt="">
                        <div class="hover-imagen1">
                            <p class="textosS">Estándar</p>
                            <p class="textosS">14,99€</p>
                        </div>
                    </div>
                    <div class="imagen-port1">
                        <img src="resources/images/plan-ava.png" alt="">
                        <div class="hover-imagen1">
                            <p class="textosS">Avanzado</p>
                            <p class="textosS">29,99€</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    <footer>
        <div class="contenedor-footer">
            <div class="footer-contact--email">
                <img src="resources/images/gmail.svg" class="footer_img">
            </div>
        </div>
        <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
    </footer>
</body>
