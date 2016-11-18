<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>TestProject Page</title>
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
<h1>
    Add a Test Step
</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\ts.jsp</h3>

<c:url var="addAction" value="/ts/create"></c:url>

<form:form action="${addAction}" commandName="ts">
    <table>
        <c:if test="${!empty ts.id}">
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
        </c:if>
        <tr>
            <td>
                <form:label path="action">
                    <spring:message text="Action"/>
                </form:label>
            </td>
            <td>
                <form:input path="action"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="expected">
                    <spring:message text="Expected"/>
                </form:label>
            </td>
            <td>
                <form:input path="expected"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author_id">
                    <spring:message text="Autor kroku"/>
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
                <c:if test="${!empty ts.action}">
                    <input type="submit"
                           value="<spring:message text="Edit TS"/>"/>
                </c:if>
                <c:if test="${empty ts.action}">
                    <input type="submit"
                           value="<spring:message text="Add TS"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>
<br>
<h3>TS List</h3>
<c:if test="${!empty listTSMusters}">
    <table class="tg">
        <tr>
            <th width="80">TS ID</th>
            <th width="120">Action</th>
            <th width="120">Expected</th>
            <th width="190">Autor</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listTSMusters}" var="ts">
            <tr>
                <td>${ts.id}</td>
                <td>${ts.action}</td>
                <td>${ts.expected}</td>
                <td>${ts.author_id}</td>
                <td><a href="<c:url value='edit/${ts.id}' />">Edit</a></td>
                <td><a href="<c:url value='remove/${ts.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>