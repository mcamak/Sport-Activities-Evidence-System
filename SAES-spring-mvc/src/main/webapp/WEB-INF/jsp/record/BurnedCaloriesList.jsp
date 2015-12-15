<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsf/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tags:pagetemplate title="Burned Calories">
    <jsp:attribute name="body">


        <a href="${pageContext.request.contextPath}/burnedCalories/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Burned Calories
        </a>
        <hr>

        <table class="table table-hover table-condensed">
            <thead>
            <tr>
                <th>Sport Activity</th>
                <th>Body Weight</th>
                <th>Burned Calories</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${burnedCalories}" var="burnedCalories">
                <tr>
                    <td class="col-md-2"><c:out value="${burnedCalories.sportActivity}"/></td>
                    <td class="col-md-2"><c:out value="${burnedCalories.bodyWeight}"/></td>
                    <td class="col-md-2"><c:out value="${burnedCalories.caloriesBurned}"/></td>
                    <td class="col-md-1">
                        <a href="${pageContext.request.contextPath}/burnedCalories/detail/${burnedCalories.id}" class="btn btn-info">View</a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/burnedCalories/remove/${burnedCalories.id}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>
</tags:pagetemplate>
