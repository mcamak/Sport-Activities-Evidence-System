<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Activity record">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/record/update"
               modelAttribute="recordUpdate" cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="activity" cssClass="col-sm-2 control-label">Sport activity</form:label>
            <div class="col-sm-10">
                <form:select path="activity" cssClass="form-control" itemValue="${record.activity.id}"
                             itemLabel="${record.activity.name}">
                    <c:forEach items="${activities}" var="c">
                        <form:option value="${c.id}">${c}</form:option>
                    </c:forEach>
                </form:select>
                <form:errors path="activities" cssClass="error"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="burnedCalories" cssClass="col-sm-2 control-label">Burned calories</form:label>
            <div class="col-sm-10">
                <form:input path="distance" cssClass="form-control" disabled="true"
                            title="Value will be re-calculated after updating. "/>
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

        <button class="btn btn-primary" type="submit">Update activity record</button>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>