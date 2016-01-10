<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 10. 1. 2016
  Time: 10:58
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
        <form class="form-inline text-center" method="post" action="${pageContext.request.contextPath}/login">
            <div class="form-group">
                <p class="help-block">Welcome to our <span
                        class=".bg-success">system for evidence of sport activities</span>. To start, log in as an
                    existing user,
                    or register a new user.</p>
                <button class="btn btn-primary btn-lg"
                        onclick="location.href = '${pageContext.request.contextPath}/login';">Login
                </button>
                <button class="btn btn-primary btn-lg"
                        onclick="location.href = '${pageContext.request.contextPath}/user/new';">Register new user
                </button>
            </div>
        </form>
        <div class="menu-button">

        </div>
    </sec:authorize>
</jsp:attribute>
</my:pagetemplate>