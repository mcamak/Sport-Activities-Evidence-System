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

<my:pagetemplate title="New sport activity">
    <jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/activity/create${id != null ? '/'+id : ''}"
               modelAttribute="activity" class="form-horizontal">
        <fieldset>
            <legend>Login</legend>
            <div class="form-group ${name_error?'has-error':''}">
                <form:label path="name" class="col-lg-2 control-label">Name</form:label>
                <div class="col-lg-4">
                    <form:input path="name" class="form-control" value="${activity.name}"/>
                    <form:errors path="name" class="help-block"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-10 col-lg-offset-2">
                    <c:choose>
                        <c:when test="${id != null}">
                            <button class="btn btn-primary" type="submit">Update sport activity</button>
                        </c:when>
                        <c:otherwise>
                            <button class="btn btn-primary" type="submit">Create sport activity</button>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </fieldset>
    </form:form>
    </jsp:attribute>
</my:pagetemplate>