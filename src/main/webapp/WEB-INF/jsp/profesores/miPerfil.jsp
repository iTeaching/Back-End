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

 
  
 		<div class="col-sm-4" style="display: flex; flex-direction: column">

				
			<c:choose>
			    <c:when test="${profesor.avatar == null}">
			        <img src="/resources/images/profile/avatar_defecto.jpg"
					style="width: 150px;" />
			        <br />
			    </c:when>    
			    <c:otherwise>
			        <img src="/resources/images/profile/${profesor.avatar}"
					style="width: 150px;" />
			        <br />
			    </c:otherwise>
			</c:choose>
			</div>

 

 
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
                    <td><ul>
    				<c:forEach var="asignatura" items="${profesor.asignaturas}">
    						<li>${asignatura.nombre}</li>
					</c:forEach>
    					</ul>
					</td>
                  </tr>
                  
                  
                 
               </tbody>
              
              </table>  
              <a class="button" href="/profesor/${profesor.id}/nuevaClase">Solicitar nueva clase</a>
              <input value="Editar" class="btn btn-default" type="button" onclick="window.location.href='/profesores/${profesor.id}/edit'">
               </form>
    
    
    
    
  
</petclinic:layout>