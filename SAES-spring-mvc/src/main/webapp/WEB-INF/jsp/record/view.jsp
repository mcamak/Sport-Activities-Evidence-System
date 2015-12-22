<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Activity records">
<jsp:attribute name="body">

    <form method="post" action="${pageContext.request.contextPath}/record/delete/${record.id}">
        <button type="submit" class="btn btn-primary">Delete</button>
    </form>


    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>Sport activity</th>
            <th>Distance</th>
            <th>Time</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${record.id}</td>
            <td><c:out value="${record.activity}"/></td>
            <td><c:out value="${record.distance}"/></td>
            <td><c:out value="${record.timeSeconds}"/></td>
        </tr>
        </tbody>
    </table>

        <div class="row">
            <div class="col-xs-6">
                <table class="table">
                    <caption>Users</caption>
                    <thead>
                    <tr>
                        <th>Name</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${record.users}" var="user">
                        <tr>
                            <td><c:out value="${user.name}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
</jsp:attribute>
</my:pagetemplate>