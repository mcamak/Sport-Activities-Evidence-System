<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 10. 1. 2016
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="New user">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/user/create${id != null ? '/'+id : ''}"
               modelAttribute="user" class="form-horizontal">
        <fieldset>
            <legend>Login</legend>
            <div class="form-group ${username_error?'has-error':''}">
                <form:label path="username" class="col-lg-2 control-label">Username</form:label>
                <div class="col-lg-4">
                    <form:input path="username" class="form-control" value="${user.username}"/>
                    <form:errors path="username" class="help-block"/>
                </div>
            </div>
            <div class="form-group ${password_error?'has-error':''}">
                <form:label path="password" class="col-lg-2 control-label">Password</form:label>
                <div class="col-lg-4">
                    <form:input path="password" class="form-control" value="${user.password}" type="password"/>
                    <form:errors path="password" class="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <form:label path="sex" class="col-lg-2 control-label">Sex</form:label>
                <div class="col-lg-4">
                    <form:select path="sex" class="form-control">
                        <form:option value="MALE">Male</form:option>
                        <form:option value="FEMALE">Female</form:option>
                    </form:select>
                </div>
            </div>
            <div class="form-group ${weight_error?'has-error':''}">
                <form:label path="weight" class="col-lg-2 control-label">Weight</form:label>
                <div class="col-lg-4">
                    <form:input path="weight" class="form-control" value="${user.weight}"/>
                    <form:errors path="weight" class="help-block"/>
                </div>
            </div>
            <div class="form-group ${age_error?'has-error':''}">
                <form:label path="age" class="col-lg-2 control-label">Age</form:label>
                <div class="col-lg-4">
                    <form:input path="age" class="form-control" value="${user.age}"/>
                    <form:errors path="age" class="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <c:choose>
                        <c:when test="${id != null}">
                            <button class="btn btn-primary" type="submit">Update user</button>
                            <sec:authorize access="isAuthenticated()">
                                <button class="btn btn-primary btn-lg"
                                        onclick="location.href = '${pageContext.request.contextPath}/user/delete/${id}';">
                                    Delete
                                </button>
                            </sec:authorize>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-primary" type="submit">Create user</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>