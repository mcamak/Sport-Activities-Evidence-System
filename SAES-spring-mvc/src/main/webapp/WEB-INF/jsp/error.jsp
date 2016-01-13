<%--
  Created by IntelliJ IDEA.
  User: MajoCAM
  Date: 13. 1. 2016
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<my:pagetemplate title="Ooooops! An error occured...">
<jsp:attribute name="body">
    <div class="container">
        <table class="tableError table nopadding">
            <tr class="tableRow">
                <th>Code:</th>
                <td>
                    <c:out value="${eCode}"/>
                </td>
            </tr>
            <tr class="tableRow">
                <th>Message:</th>
                <td>
                    <c:out value="${eMessage}"/>
                </td>
            </tr>
        </table>
    </div>
</jsp:attribute>
</my:pagetemplate>