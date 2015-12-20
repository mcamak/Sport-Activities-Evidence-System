<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsf/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tags:pagetemplate title="Sport Activity">
    <jsp:attribute name="body">


        <a href="${pageContext.request.contextPath}/sportActivity/new" class="btn btn-primary">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New Sport Activity
        </a>
        <hr>

        <table class="table table-hover table-condensed">
            <thead>
            <tr>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${sportActivity}" var="sportActivity">
                <tr>
                    <td class="col-md-2"><c:out value="${sportActivity.name}"/></td>
                    <td class="col-md-1">
                    <td>
                        <a href="${pageContext.request.contextPath}/sportActivity/detail/${sportActivity.id}" class="btn btn-info">View</a>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/sportActivity/remove/${sportActivity.id}">
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </jsp:attribute>
</tags:pagetemplate>
