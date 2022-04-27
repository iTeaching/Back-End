<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<iteaching:layout pageName="profesores">
    <h2>
    Eliminar Profesor
    </h2>
    	
 
 	<div>
    <form:form modelAttribute="profesor">
        
            <p>¿Está seguro de que quiere eliminar su usuario del sistema?</p>
            
        <div class="form-group">      
                        <button type="submit" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Eliminar Usuario</button>
                        <br><br>
                        <a href="/profesores/miPerfil" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Cancelar</a>
        </div>
    </form:form>
    </div>
    
    
    
</iteaching:layout>