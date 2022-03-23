<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="sala nueva">
    <h2>
        <c:if test="${salas['new']}">New </c:if> Sala
    </h2>
    <form:form modelAttribute="salas" class="form-horizontal" id="add-room-form">
    	<input type="hidden" name="id" value="${sala.id}"/>
        <input type="hidden" name="version" value="${sala.version}"/>
        <input type="hidden" name="version" value="${sala.version}"/>

        <div class="form-group has-feedback">
            <petclinic:inputField label="Nombre" name="nombre"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${salas ['new']}">
                        <button class="btn btn-default" type="submit"style="background-color:#F9DA5C; margin-top:5px">Crear Sala</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualiza tu perfil</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>