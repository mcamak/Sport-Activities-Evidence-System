<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Activity records">
<jsp:attribute name="body">

    <my:a href="/record/new" class="btn btn-primary btnNew">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New activity record
    </my:a>

    <table class="table table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Sport activity</th>
            <th>Burned calories</th>
            <th>Time</th>
            <th>Distance</th>
            <th>Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr class="tableRow"
                onclick="location.href = '${pageContext.request.contextPath}/record/update/${record.id}';">
                <td>
                    <c:out value="${record.id}"/>
                </td>
                <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
                    <td>
                        <form method="get"
                              action="${pageContext.request.contextPath}/activity/update/${record.activity.id}">
                            <button class="btn"><c:out value="${record.activity.name}"/></button>
                        </form>
                    </td>
                </sec:authorize>
                <sec:authorize access="hasAnyRole('ROLE_USER')">
                    <td>
                        <c:out value="${record.activity.name}"/>
                    </td>
                </sec:authorize>
                <td>
                    <c:out value="${record.burnedCalories}"/>
                </td>
                <td>
                    <c:out value="${record.time}"/>
                </td>
                <td>
                    <c:out value="${record.distance}"/>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/record/delete/${record.id}">
                        <button type="submit" class="btn btn-default">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>