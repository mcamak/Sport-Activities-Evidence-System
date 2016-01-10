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
        <p>You are logged in as <strong><c:out value="${pageContext.request.userPrincipal.name}"/></strong></p>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/logout">Logout</a>
    </sec:authorize>

    <sec:authorize access="isAnonymous()">
        <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">
            <fieldset>
                <legend>Login</legend>
                <div>
                    <p class="help-block">You can also log in with as one of three pre-created users: admin/admin,
                        erik/erik,
                        maria/maria.</p>
                </div>
                <div class="form-group">
                    <label for="textInput" class="col-lg-2 control-label">Username</label>
                    <div class="col-lg-4">
                        <input type="text" class="form-control" id="textInput" placeholder="Username" name="username">
                    </div>
                    <div class="col-lg-6">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                    <div class="col-lg-4">
                        <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                               name="password">
                    </div>
                    <div class="col-lg-6">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </sec:authorize>
</jsp:attribute>
</my:pagetemplate>