<%-- 
    Document   : BurnedCalories
    Created on : 13.12.2015, 15:57:17
    Author     : Barborka
--%>

<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tags:pagetemplate title="Burned Calories">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/burnedCalories/create"
                   modelAttribute="burnedCaloriesCreate" cssClass="form-horizontal">
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="sportActivity" cssClass="col-sm-1 control-label">Sport Activity</form:label>
                <div class="col-sm-4">
                    <form:input path="sportActivity" cssClass="form-control"/>
                    <form:errors path="sportActivity" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${bodyWeight_error?'has-error':''}">
                <form:label path="bodyWeight" cssClass="col-sm-1 control-label">Body Weight</form:label>
                <div class="col-sm-4">
                    <form:input path="bodyWeight" cssClass="form-control"/>
                    <form:errors path="bodyWeight" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${caloriesBurned_error?'has-error':''}">
                <form:label path="caloriesBurned" cssClass="col-sm-1 control-label">Calories Burned</form:label>
                <div class="col-sm-4">
                    <form:input path="caloriesBurned" cssClass="form-control"/>
                    <form:errors path="caloriesBurned" cssClass="help-block"/>
                </div>
            </div>
            
            
            <a href="${pageContext.request.contextPath}/burnedCalories" class="btn btn-danger">Back</a>
            <button class="btn btn-primary" type="submit">Create burned calories</button>
        </form:form>

    </jsp:attribute>
</tags:pagetemplate>
