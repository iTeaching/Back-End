<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
<style>
#form {
  width: 250px;
  margin: 0 auto;
  height: 50px;
}

#form p {
  text-align: center;
}

#form label {
  font-size: 20px;
}

input[type="radio"] {
  display: none;
}

label {
  color: grey;
}

.clasificacion {
  direction: rtl;
  unicode-bidi: bidi-override;
}

label:hover,
label:hover ~ label {
  color: orange;
}

input[type="radio"]:checked ~ label {
  color: orange;
}
</style>
</head>

<iteaching:layout pageName="valoracion nueva">
    <h2>
        <c:if test="${valoracion['new']}">Nueva </c:if> Valoración
    </h2>
    <form:form modelAttribute="valoracion" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
      <div align="left" class="clasificacion">
    <input id="radio1" type="radio" name="puntuacion" value="5.0"><!--
    --><label for="radio1">★</label><!--
    --><input id="radio2" type="radio" name="puntuacion" value="4.0"><!--
    --><label for="radio2">★</label><!--
    --><input id="radio3" type="radio" name="puntuacion" value="3.0"><!--
    --><label for="radio3">★</label><!--
    --><input id="radio4" type="radio" name="puntuacion" value="2.0"><!--
    --><label for="radio4">★</label><!--
    --><input id="radio5" type="radio" name="puntuacion" value="1.0"><!--
    --><label for="radio5">★</label>
  </div>
  
  

  
   <div class="form-group has-feedback">
            <iteaching:inputField label="Comentario" name="comentario"/>
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${valoracion['new']}">
                    
                        <button class="btn btn-default" type="submit">¡Valorar!</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualizar</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</iteaching:layout>
