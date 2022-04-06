<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<petclinic:layout pageName="pay">
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
body {
  font-family: Arial;
  font-size: 17px;
  padding: 8px;
}

* {
  box-sizing: border-box;
}

.row {
  display: -ms-flexbox; /* IE10 */
  display: flex;
  -ms-flex-wrap: wrap; /* IE10 */
  flex-wrap: wrap;
  margin: 0 -16px;
}

.col-25 {
  -ms-flex: 25%; /* IE10 */
  flex: 25%;
}

.col-50 {
  -ms-flex: 50%; /* IE10 */
  flex: 50%;
}

.col-75 {
  -ms-flex: 75%; /* IE10 */
  flex: 75%;
}

.col-25,
.col-50,
.col-75 {
  padding: 0 16px;
}

.container {
  background-color: #ffff;
  padding: 5px 20px 15px 20px;
  border-radius: 3px;
}

input[type=text] {
  width: 100%;
  margin-bottom: 20px;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

label {
  margin-bottom: 10px;
  display: block;
}

.icon-container {
  margin-bottom: 20px;
  padding: 7px 0;
  font-size: 24px;
}

.btn {
  background-color: #000;
  color: white;
  padding: 12px;
  margin: 10px 0;
  border: none;
  width: 100%;
  border-radius: 3px;
  cursor: pointer;
  font-size: 17px;
}

.btn:hover {
  background-color: #FFDD33;
}

a {
  color: #2196F3;
}

hr {
  border: 1px solid lightgrey;
}

span.price {
  float: right;
  color: grey;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
@media (max-width: 800px) {
  .row {
    flex-direction: column-reverse;
  }
  .col-25 {
    margin-bottom: 20px;
  }
}
input[type="radio"], input[type="checkbox"] {
    display: none;
  }
  label {
    padding-left: 1.25em;
    background: url('https://s3-eu-west-1.amazonaws.com/static.oscargascon.es/wp-content/media/check-radio-sprites.png') no-repeat top left;
  }
  input[type="radio"] + label   {
    background-position: 0 -42px; width: 24px; height: 24px;
  }
  input[type="radio"]:checked + label {
    background-position: 0 -71px; width: 24px; height: 24px;
  }
  input[type="checkbox"] + label {
    background-position: 0 0; width: 16px; height: 16px;
  }
  input[type="checkbox"]:checked + label {
    background-position: 0 -21px; width: 16px; height: 16px;
  }

    </style>
</head>
<body>
<div class="row">
    <div class="col-75">
        <div class="container">
            <form method="post"  action="/pay">
                <div class="col-50">
                    <h1><b>FACTURACIÓN</b></h1>
                    <br>
                    <h3>Descripción del pago</h3>
                    <input type="text" id="description" name="description" value="CLASES">
                    <h3>Concepto del pago</h3>
                    <input type="radio" id="intent" name="intent" value="sale" checked>
                    <label for="radio1">Sale</label>
                    <h3>Divisa</h3>
                    <input type="radio" name="currency" value="EUR" id="radio1" checked /><label for="radio1">EUR</label>
                    <h3>Método de pago</h3>
                    <input type="radio" name="method" value="PAYPAL" id="radio1" checked /><label for="radio1">Paypal</label>
                    <h3><b><i><u>TOTAL</u></i></b></h3>
                    <input type="text" id="price" name="price" value="${orden.price}" readonly=True >
                </div>

                <input type="submit" value="Confirmar" class="btn">
            </form>
        </div>
    </div>
</div>
</body>
</petclinic:layout>