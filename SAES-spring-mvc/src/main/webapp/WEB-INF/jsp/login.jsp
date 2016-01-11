<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 9. 1. 2016
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true" %>
<%-- declare my own tags --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%-- declare JSTL libraries --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="title" value="${\"Sport Activity Evidence System\"}"/>

<%-- call my own tag defined in /WEB-INF/tags/pagetemplate.tag, provide title attribute --%>
<my:pagetemplate title="${title}">
<jsp:attribute name="body"><%-- provide page-fragment attribute to be rendered by the my:layout tag --%>

    <c:if test="${param.error != null}">
        <p class="text-danger">
            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p class="text-success">You have been logged out.</p>
    </c:if>

    <sec:authorize access="isAuthenticated()">
        <div class="text-center">
            <p>You are logged in as <strong><c:out value="${pageContext.request.userPrincipal.name}"/></strong></p>
            <button class="btn btn-primary btn-lg"
                    onclick="location.href = '${pageContext.request.contextPath}/logout';">
                Logout
            </button>
        </div>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
        <div>
            <p class="help-block">Welcome to our <span class=".bg-success">system for evidence of sport
                        activities</span>. To start, log in as an existing user, or register a new user. You can
                also log in with as one of three pre-created users: <strong>admin/admin</strong>,
                <strong>erik/erik</strong>, <strong>maria/maria</strong>.</p>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <form:form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login"
                               modelAttribute="userLogin">
                        <fieldset>
                            <legend>Login</legend>
                            <div class="form-group ${username_error?'has-error':''}">
                                <form:label path="username" class="col-lg-2 control-label">Username</form:label>
                                <div class="col-lg-4">
                                    <form:input path="username" cssClass="form-control"/>
                                    <form:errors path="username" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="form-group ${password_error?'has-error':''}">
                                <form:label path="password" class="col-lg-2 control-label">Password</form:label>
                                <div class="col-lg-4">
                                    <form:input path="password" cssClass="form-control" type="password"
                                                placeholder="Password"/>
                                    <form:errors path="password" cssClass="help-block"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </div>
                            </div>
                        </fieldset>
                    </form:form>
                </div>
                <div class="col-sm-6">
                    <form class="form-horizontal" method="get" action="${pageContext.request.contextPath}/user/new">
                        <fieldset>
                            <legend>Register</legend>
                            <div class="form-group text-center">
                                <div class="col-lg-10 col-lg-offset-2">
                                    <button type="submit" class="btn btn-primary btn-lg">Register new user</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </sec:authorize>
</jsp:attribute>
</my:pagetemplate>