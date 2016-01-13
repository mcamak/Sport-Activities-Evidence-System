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

<my:pagetemplate title="${user.id != null ? 'Update' : 'New'} user">
    <jsp:attribute name="body">
        <div class="container">
            <div class="row">
                <div class="col-sm-8">
                    <form:form method="post" action="${pageContext.request.contextPath}/user/create"
                               modelAttribute="user" class="form-horizontal">
                        <fieldset>
                            <form:input path="id" class="form-control" value="${user.id}" type="hidden"/>
                            <div class="form-group ${username_error?'has-error':''}">
                                <form:label path="username" class="col-lg-2 control-label">Username</form:label>
                                <div class="col-lg-4">
                                    <form:input path="username" class="form-control" value="${user.username}"/>
                                    <form:errors path="username" class="help-block"/>
                                </div>
                            </div>
                            <c:choose>
                            <c:when test="${user.id != null}">
                            <div class="form-group ${password_error?'has-error':''}" hidden>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group ${password_error?'has-error':''}">
                                        </c:otherwise>
                                        </c:choose>
                                        <form:label path="password" class="col-lg-2 control-label">Password</form:label>
                                    <div class="col-lg-4">
                                        <form:input path="password" class="form-control" type="password"
                                                    value="${user.id != null ? 'neverBeUsed' : user.password}"/>
                                        <form:errors path="password" class="help-block"/>
                                    </div>
                                </div>
                                <div class="form-group ${sex_error?'has-error':''}">
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
                                            <c:when test="${user.id != null}">
                                                <button class="btn btn-primary" type="submit">Update user</button>
                                            </c:when>
                                            <c:otherwise>
                                                <button class="btn btn-primary btnNew" type="submit">Create user
                                                </button>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                        </fieldset>
                    </form:form>
                </div>
                <div class="col-sm-4">
                    <sec:authorize access="isAuthenticated()">
                        <c:if test="${user.id != null}">
                            <form:form method="post" action="${pageContext.request.contextPath}/user/delete/${user.id}"
                                       class="form-horizontal">
                                <fieldset>
                                    <legend>Delete user</legend>
                                    <div class="form-group">
                                        <div class="col-lg-10 col-lg-offset-2">
                                            <button type="submit" class="btn btn-primary btn-lg">Delete</button>
                                        </div>
                                    </div>
                                </fieldset>
                            </form:form>
                        </c:if>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </jsp:attribute>
</my:pagetemplate>