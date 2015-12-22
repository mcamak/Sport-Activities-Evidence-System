<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Activity records">
<jsp:attribute name="body">

    <my:a href="/record/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New activity record
    </my:a>

    <table class="table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Sport activity</th>
            <th>Distance</th>
            <th>Time</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${records}" var="record">
            <tr>
                <td>${record.id}</td>
                <td><c:out value="${record.activity}"/></td>
                <td><c:out value="${record.distance}"/></td>
                <td><c:out value="${record.timeSeconds}"/></td>
                <td>
                    <my:a href="/record/view/${record.id}" class="btn btn-primary">View</my:a>
                </td>
                <td>
                    <form method="post" action="${pageContext.request.contextPath}/record/delete/${record.id}">
                        <button type="submit" class="btn btn-primary">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</jsp:attribute>
</my:pagetemplate>