<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New activity record">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/record/new"
               modelAttribute="recordCreate" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="activity" cssClass="col-sm-2 control-label">currency</form:label>
            <div class="col-sm-10">
                <form:select path="activity" cssClass="form-control">
                    <c:forEach items="${activities}" var="c">
                        <form:option value="${c}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="activities" cssClass="error"/>
            </div>
        </div>
        <div class="form-group ${time_error?'has-error':''}">
            <form:label path="time" cssClass="col-sm-2 control-label">Time</form:label>
            <div class="col-sm-10">
                <form:input path="time" cssClass="form-control"/>
                <form:errors path="time" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group ${distance_error?'has-error':''}">
            <form:label path="distance" cssClass="col-sm-2 control-label">Distance</form:label>
            <div class="col-sm-10">
                <form:input path="distance" cssClass="form-control"/>
                <form:errors path="distance" cssClass="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="userId" cssClass="col-sm-2 control-label">Users</form:label>
            <div class="col-sm-10">
                <form:select path="userId" cssClass="form-control">
                    <c:forEach items="${users}" var="c">
                        <form:option value="${c.id}">${c.name}</form:option>
                    </c:forEach>
                </form:select>
                <p class="help-block"><form:errors path="userId" cssClass="error"/></p>
            </div>
        </div>

        <button class="btn btn-primary" type="submit">Create activity record</button>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>