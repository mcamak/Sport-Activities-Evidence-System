
<%@page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="true"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<tags:pagetemplate title="Book ${book.id} detail">
<jsp:attribute name="body">

        <table class="table">
            <tr>
                <td class="col-md-2"><b>Id</b></td>
                <td>${book.id}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Isbn</b></td>
                <td>${book.isbn}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Author</b></td>
                <td>${book.author}</td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Title</b></td>
                <td><c:out value="${book.title}"/></td>
            </tr>
            <tr>
                <td class="col-md-2"><b>Is available?</b></td>
                <td><c:out value="${available ? \"Yes \" : \"No \"}"/></td>
            </tr>
            <tr>
                <td><a href="${pageContext.request.contextPath}/book" class="btn btn-danger">Back</a></td>
            </tr>
        </table>
</jsp:attribute>
</tags:pagetemplate>
