<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<!DOCTYPE>
<link rel="icon" href="/resources/images/logo.ico" type="image/x-icon">
<html>
<iteaching:menu name="pay"/>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
<title>iTeaching</title>
<link href="../resources/style/estilos.css" rel="stylesheet" type="text/css">
<link href="../resources/style/pay.css" rel="stylesheet" type="text/css">
</head>
<body>
          <div class="container">
              <form method="post"  action="/pay">
                  <div class="col-50">
                      <br>
                      <h1><b><u>FACTURACIÓN</u></b></h1>
                      <br>
                      <h3>Descripción del pago</h3>
                      <input type="text" id="description" name="description" value="{orden.description}">
                      <br>
                      <h3>Concepto del pago</h3>
                      <input type="radio" id="intent" name="intent" value="sale" checked>
                      <label for="radio1">Clase</label>
                      <h3>Divisa</h3>
                      <input type="radio" name="currency" value="EUR" id="radio1" checked /><label for="radio1">EUR</label>
                      <h3>Método de pago</h3>
                      <input type="radio" name="method" value="PAYPAL" id="radio1" checked /><label for="radio1">Paypal</label>
                      <br>
                      <h3><b><i><u>TOTAL</u></i></b></h3>
                      <input type="text" id="price" name="price" value="${orden.price}" readonly=True >
                  </div>
                  <br>
                  <input type="submit" value="Confirmar" class="btn">
              </form>
          </div>
  </body>
  <footer>
    <div class="contenedor-footer">
      <div class="footer-contact--email">
        <img src="resources/images/gmail.svg" class="footer_img">
      </div>
    </div>
    <h2 class="titulo-final">&copy; 2022,iTeaching</h2>
  </footer>
  </html>