<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<iteaching:layout pageName="anuncios">
    <h2>Anuncios</h2>
    <head>
        <meta charset="UTF-8">
      </head>
      

    
     <form th:object="${anuncio}" th:action="@{/ofertas}" method="get"
    class="form-horizontal" id="search-owner-form">
    <div class="form-group">
      <div class="control-group" id="lastNameGroup">
        <label class="col-sm-2 control-label">Asignatura </label>
        <div class="col-sm-10">
          <input class="form-control" th:field="*{asignatura}" size="30"
            maxlength="80" /> <span class="help-inline"></span>
        </div>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-primary">Find
          Asignatura</button>
      </div>
    </div>
     </form>

   
    <table id="anunciosTable" class="table table-striped">
        <thead>
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Asignatura</th>
            <th>Precio/Hora</th>
            <th>Profesor</th>
            <th>Apuntarme</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${anuncios}" var="anuncio">
            <tr>
              
                <td>
                <c:out value="${anuncio.titulo}"/>                  
                </td>
                <td>
                    <c:out value="${anuncio.descripcion}"/>
                </td>
                <td>
                    <c:out value="${anuncio.asignatura}"/>
                </td>
                <td>
                    <c:out value="${anuncio.precio}"/>
                </td>
                <td>
                    <c:out value="${anuncio.profesor.firstName} ${anuncio.profesor.lastName}"/>
                </td>
                <td>
                    <spring:url value="/anuncio/{anuncioId}/apply" var="anuncioId">
                    <spring:param name="anuncioId" value="${anuncio.id}"/>
                    </spring:url>
                    <a href="${fn:escapeXml(anuncioId)}"><img src="..\resources\images\icons8-añadir-30.png"></span>
                    </a>                       
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</iteaching:layout>