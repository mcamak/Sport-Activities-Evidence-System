<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 10. 1. 2016
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="User">
<jsp:attribute name="body">

    <my:a href="/user/new" class="btn btn-primary btnNew">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New user
    </my:a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Weight</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr class="tableRow" onclick="location.href = '${pageContext.request.contextPath}/user/update/${user.id}';">
                <td>
                    <c:out value="${user.id}"/>
                </td>
                <td>
                    <c:out value="${user.username}"/>
                </td>
                <td>
                    <c:out value="${user.sex}"/>
                </td>
                <td>
                    <c:out value="${user.age}"/>
                </td>
                <td>
                    <c:out value="${user.weight}"/>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/user/delete/${user.id}">
                        <button type="submit" class="btn btn-default">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>