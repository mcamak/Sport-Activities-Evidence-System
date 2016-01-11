<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Sport activities">
<jsp:attribute name="body">

    <my:a href="/activity/new" class="btn btn-primary btnNew">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New sport activity
    </my:a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${activities}" var="calorie">
            <tr class="tableRow">
                <td onclick="location.href = '${pageContext.request.contextPath}/activity/update/${calorie.id}';">
                    <c:out value="${calorie.id}"/>
                </td>
                <td onclick="location.href = '${pageContext.request.contextPath}/activity/update/${calorie.id}';">
                    <c:out value="${calorie.name}"/>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/activity/delete/${calorie.id}">
                        <button type="submit" class="btn btn-default">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>