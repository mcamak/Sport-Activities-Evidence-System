<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Burned calories">
<jsp:attribute name="body">

    <my:a href="/calorie/new" class="btn btn-primary btnNew">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New burned calorie
    </my:a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Sport activity</th>
            <th>Body weight</th>
            <th>Burned calories</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${calories}" var="calorie">
            <tr class="tableRow"
                onclick="location.href = '${pageContext.request.contextPath}/calorie/update/${calorie.id}';">
                <td>
                    <c:out value="${calorie.id}"/>
                </td>
                <td>
                    <form method="get"
                          action="${pageContext.request.contextPath}/activity/update/${calorie.activity.id}">
                        <button type="submit" class="btn"><c:out value="${calorie.activity.name}"/></button>
                    </form>
                </td>
                <td>
                    <c:out value="${calorie.bodyWeight}"/>
                </td>
                <td>
                    <c:out value="${calorie.caloriesBurned}"/>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/calorie/delete/${calorie.id}">
                        <button type="submit" class="btn btn-default">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>