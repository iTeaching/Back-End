<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="iteaching" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<iteaching:layout pageName="profesor nuevo">
    <h2>
        <c:if test="${profesor['new']}">New </c:if> Profesor
    </h2>
    <form:form modelAttribute="profesor" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <iteaching:inputField label="First Name" name="firstName"/>
            <iteaching:inputField label="Last Name" name="lastName"/>
            <iteaching:inputField label="Telephone" name="telephone"/>
            <iteaching:inputField label="Email" name="email"/>
            <iteaching:inputField label="Username" name="user.username"/>
            <iteaching:inputField label="Password" name="user.password"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${profesor ['new']}">
                        <button class="btn btn-default" type="submit">¡Únete a nuestro plantel!</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Actualiza tu perfil</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</iteaching:layout>