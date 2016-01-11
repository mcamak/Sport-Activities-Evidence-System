<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 10. 1. 2016
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="${calorie.id != null ? 'Update' : 'New'} burned calorie">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/calorie/create"
               modelAttribute="calorie" class="form-horizontal">
        <fieldset>
            <form:input path="id" class="form-control" value="${calorie.id}" type="hidden"/>
            <div class="form-group">
                <form:label path="activity" cssClass="col-sm-2 control-label">Sport activity</form:label>
                <div class="col-sm-10">
                    <form:select path="activity" cssClass="form-control">
                        <c:forEach items="${activities}" var="activity">
                            <c:choose>
                                <c:when test="${calorie.activity != null && calorie.activity.id == activity.id}">
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
            <div class="form-group ${bodyWeight_error?'has-error':''}">
                <form:label path="bodyWeight" class="col-lg-2 control-label">Body weight</form:label>
                <div class="col-lg-4">
                    <form:input path="bodyWeight" class="form-control" value="${calorie.bodyWeight}"/>
                    <form:errors path="bodyWeight" class="help-block"/>
                </div>
            </div>
            <div class="form-group ${caloriesBurned_error?'has-error':''}">
                <form:label path="caloriesBurned" class="col-lg-2 control-label">Burned calories</form:label>
                <div class="col-lg-4">
                    <form:input path="caloriesBurned" class="form-control" value="${calorie.caloriesBurned}"/>
                    <form:errors path="caloriesBurned" class="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <c:choose>
                        <c:when test="${calorie.id != null}">
                            <button class="btn btn-primary" type="submit">Update burned calorie</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-primary" type="submit">Create burned calorie</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>