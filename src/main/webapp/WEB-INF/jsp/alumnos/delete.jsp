<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<iteaching:layout pageName="alumnos">
    <h2>
    Eliminar Alumno
    </h2>
    	
 
 	<div>
    <form:form modelAttribute="alumno">
        
            <p>¿Está seguro de que quiere eliminar su usuario del sistema?</p>
            
        <div class="form-group">      
                        <button type="submit" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Eliminar Usuario</button>
                        <br><br>
                        <a href="/alumnos/miPerfil" class="btn btn-warning" style="font-size:16px; color:white; border-color: #dab305; background-color: #dab305;">Cancelar</a>
        </div>
    </form:form>
    </div>
    
    
    
</iteaching:layout>