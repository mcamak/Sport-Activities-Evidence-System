<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsf/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tags:pagetemplate title="Burned Calories detail">
<jsp:attribute name="body">

        <table class="table">
            <tr>
                <td class="col-md-2"><b>Id</b></td>
                <td>${burnedCalories.id}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Sport Activity</b></td>
                <td>${burnedCalories.sportActivity}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Body Weight</b></td>
                <td>${burnedCalories.bodyWeight}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Calories Burned</b></td>
                <td><c:out value="${burnedCalories.caloriesBurned}"/></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/book" class="btn btn-danger">Back</a></td>
            </tr>
        </table>
</jsp:attribute>
</tags:pagetemplate>
