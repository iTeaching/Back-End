<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<petclinic:layout pageName="Mi perfil">

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/lib/bootstrap.min.css">
  <script src="/lib/jquery-1.12.2.min.js"></script>
  <script src="/lib/bootstrap.min.js"></script>

 <form>

<table class=   "table table-user-information">
                <tbody>
                  <tr>
                    <td>Nombre:</td>
                    <td>${profesor.firstName}&nbsp${profesor.lastName }</td>
                  </tr>
                  <tr>
                    <td>Teléfono:</td>
                    <td>${profesor.telephone}</td>
                  </tr>

                   <tr>
                    <td>Email</td>
                    <td>${profesor.email}</td>
                  </tr>
                  
                  <tr>
                    <td>Anuncios</td>
                    <td><c:forEach var="anuncio" items="${profesor.asignaturas}">
    					${anuncio.titulo_anuncio}, &nbsp
						</c:forEach>
					</td>
                  </tr>
                  
                  
                 
               </tbody>
              
              </table>  
              <input value="Editar" class="btn btn-default" type="button" onClick="/profesores/${profesor.id}/edit">
               </form>
    
    
    
    
</petclinic:layout>