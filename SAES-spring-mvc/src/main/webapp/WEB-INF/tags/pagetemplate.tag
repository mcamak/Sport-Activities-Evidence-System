<%@ tag pageEncoding="utf-8" dynamic-attributes="dynattrs" trimDirectiveWhitespaces="true" %>
<%@ attribute name="title" required="false" %>
<%@ attribute name="head" fragment="true" %>
<%@ attribute name="body" fragment="true" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="${pageContext.request.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><c:out value="${title}"/></title>
    <!-- bootstrap loaded from content delivery network -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"
          crossorigin="anonymous">
    <jsp:invoke fragment="head"/>
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-inverse navbar-static-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Sport Activity Evidence System</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <sec:authorize access="hasAnyRole('ADMIN')">
                    <li class="${pageContext.request.requestURI.contains("/activity") ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/activity">Sport Activity</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ADMIN','USER')">
                    <li class="${pageContext.request.requestURI.contains("/record") ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/record">Activity Record</a>
                    </li>
                    <li class="${pageContext.request.requestURI.contains("/user") ? 'active' : ''}">
                        <a href="${pageContext.request.contextPath}/user">User</a>
                    </li>
                </sec:authorize>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <sec:authorize access="isAnonymous()">
                        <a href="${pageContext.request.contextPath}/login">Login</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="${pageContext.request.contextPath}/logout">Logout <c:out
                                value="${pageContext.request.userPrincipal.name}"/></a>
                    </sec:authorize>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!--page headline starts here-->
<div class="container-fluid">
    <div class="page-header" id="banner">
        <div class="row">
            <div class="col-lg-8 col-md-7 col-sm-6">
                <h1><c:out value="${title}"/></h1>

                <p class="lead"></p>
            </div>
            <div class="col-lg-4 col-md-5 col-sm-6">
                <div class="sponsor">
                </div>
            </div>
        </div>
    </div>
</div>

<!-- alerts -->
<c:if test="${not empty alert_danger}">
    <div class="alert alert-danger" role="alert">
        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
        <c:out value="${alert_danger}"/></div>
</c:if>
<c:if test="${not empty alert_info}">
    <div class="alert alert-info" role="alert"><c:out value="${alert_info}"/></div>
</c:if>
<c:if test="${not empty alert_success}">
    <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
</c:if>
<c:if test="${not empty alert_warning}">
    <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
</c:if>

<!-- page body -->
<jsp:invoke fragment="body"/>

<!-- footer -->
<footer class="footer">
    <p>&copy;&nbsp;<%=java.time.Year.now().toString()%>&nbsp;Masaryk University</p>
</footer>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
