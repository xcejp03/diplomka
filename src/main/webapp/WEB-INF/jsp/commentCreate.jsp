<%--
  Created by IntelliJ IDEA.
  User: pcejk
  Date: 05.11.2016
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Komentáře</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<h1>Komentáře k defektům v systemu</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\commentCreate.jsp</h3>

<c:url var="addAction" value="/comment/create"></c:url>

<form:form action="${addAction}" commandName="comment">
    <table>
        <c:if test="${!empty comment.commentText}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
       <%--     <tr>
                 <td>
                <form:label path="createdDateTime">
                    <spring:message text="createdDateTime"/>
                </form:label>
            </td>
                <td>
                    <form:input path="createdDateTime" readonly="true" size="8" disabled="false"/>
                    <form:hidden path="createdDateTime"/>
                </td>
            </tr>--%>
        </c:if>
        <tr>
            <td>
                <form:label path="commentText">
                    <spring:message text="text komentáře"/>
                </form:label>
            </td>
            <td>
                <form:input path="commentText"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="defect_id">
                    <spring:message text="defect_id"/>
                </form:label>
            </td>
            <td>
                <select path="defect_id" name="defect_id">
                    <c:forEach var="item" items="${listDefects}">
                        <option value="${item.id}">${item.description}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author_id">
                    <spring:message text="author_id"/>
                </form:label>
            </td>
            <td>
                <select path="author_id" name="author_id">
                    <c:forEach var="item" items="${listPersons}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>


        <tr>
            <td colspan="2">
                <c:if test="${!empty comment.commentText}">
                    <input type="submit"
                           value="<spring:message text="Edit comment"/>"/>
                </c:if>
                <c:if test="${empty comment.commentText}">
                    <input type="submit"
                           value="<spring:message text="Add comment"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


<h3>Comment List</h3>
<c:if test="${!empty listComments}">
    <table class="tg">
        <tr>
            <th width="80">Comment ID</th>
            <th width="80">Comment createdDateTime</th>
            <th width="80">Author id</th>
            <th width="120">Comment text</th>
            <th width="40">Edit</th>
            <th width="50">Delete</th>
        </tr>
        <c:forEach items="${listComments}" var="comment">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.createdDateTime}</td>
                <td>${comment.author_id}</td>
                <td>${comment.commentText}</td>
                <td><a href="<c:url value='/comment/edit/${comment.id}' />">Edit</a></td>
                <td><a href="<c:url value='/comment/remove/${comment.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
