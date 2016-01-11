<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${record.id != null ? 'Update' : 'New'} activity record">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/record/create"
               modelAttribute="record" cssClass="form-horizontal">
        <fieldset>
            <form:input path="id" class="form-control" value="${record.id}" type="hidden"/>
            <form:input path="user" class="form-control" value="${record.user.id}" type="hidden"/>
            <div class="form-group">
                <form:label path="activity" cssClass="col-sm-2 control-label">Sport activity</form:label>
                <div class="col-sm-10">
                    <form:select path="activity" cssClass="form-control">
                        <c:forEach items="${activities}" var="activity">
                            <c:choose>
                                <c:when test="${record.activity != null && record.activity.id == activity.id}">
                                    <form:option value="${activity.id}" selected="true">${activity.name}</form:option>
                                </c:when>
                                <c:otherwise>
                                    <form:option value="${activity.id}">${activity.name}</form:option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                    <form:errors path="activity" cssClass="error"/>
                </div>
            </div>
            <div class="form-group ${burnedCalories_error?'has-error':''}">
                <form:label path="burnedCalories" cssClass="col-sm-2 control-label">Burned calories</form:label>
                <div class="col-sm-10">
                    <form:input path="burnedCalories" cssClass="form-control" value="${record.burnedCalories}"
                                disabled="true" title="This value will be automatically calculated. "/>
                    <form:errors path="burnedCalories" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${time_error?'has-error':''}">
                <form:label path="time" cssClass="col-sm-2 control-label">Time</form:label>
                <div class="col-sm-10">
                    <form:input path="time" cssClass="form-control" value="${record.time}"/>
                    <form:errors path="time" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group ${distance_error?'has-error':''}">
                <form:label path="distance" cssClass="col-sm-2 control-label">Distance</form:label>
                <div class="col-sm-10">
                    <form:input path="distance" cssClass="form-control" value="${record.distance}"/>
                    <form:errors path="distance" cssClass="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <c:choose>
                        <c:when test="${record.id != null}">
                            <button class="btn btn-primary" type="submit">Update activity record</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-primary" type="submit">Create activity record</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>