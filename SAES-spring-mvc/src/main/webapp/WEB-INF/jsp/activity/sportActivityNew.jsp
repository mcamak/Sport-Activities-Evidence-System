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


<tags:pagetemplate title="New Sport Activity">
    <jsp:attribute name="body">

        <form:form method="post" action="${pageContext.request.contextPath}/sportActivity/create"
                   modelAttribute="sportActivityCreate" cssClass="form-horizontal">
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="sportActivity" cssClass="col-sm-1 control-label">Sport Activity</form:label>
                <div class="col-sm-4">
                    <form:input path="sportActivity" cssClass="form-control"/>
                    <form:errors path="sportActivity" cssClass="help-block"/>
                </div>
            </div>
            
            <a href="${pageContext.request.contextPath}/sportActivity" class="btn btn-danger">Back</a>
            <button class="btn btn-primary" type="submit">Create sport activity</button>
        </form:form>

    </jsp:attribute>
</tags:pagetemplate>

