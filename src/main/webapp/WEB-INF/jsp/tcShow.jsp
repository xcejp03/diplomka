<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Run TC</title>

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
<%--
tCInstance
listTSInstances
--%>
<c:url var="addAction" value="/tc/run/${tc.id}"></c:url>
<h1>Spuštěný test: ${tcInstance.name} - ${tcInstance.id}</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\tcShow.jsp</h3>

<c:if test="${!empty listTSInstances}">
    <h3>Test stepy List</h3>
    <p></p><a href="<c:url value='/tc/history/${tcInstance.tcMusters_id}'/>">Historie TC</a></p>

    <p>Není empty</p>
    <table class="tg">
        <tr>
            <th width="40">TS ID</th>
            <th width="80">Akce</th>
            <th width="120">Očekávaný výsledek</th>
            <th width="80">Výsledek</th>
            <th width="40">Edit</th>
            <th width="50">Delete</th>
        </tr>
        <c:forEach items="${listTSInstances}" var="ts">
            <tr>
                <td>${ts.id}</td>
                <td>${ts.action}</td>
                <td>${ts.expected}</td>
                <td>${ts.result}</td>
                    <%--<td>--%>
                    <%--<c:forEach items="${project.projectMembers_id}" var="projectMember">--%>
                    <%--${projectMember},--%>
                    <%--</c:forEach>--%>
                    <%--</td>--%>
                <td><a href="<c:url value='/ts/run/${ts.id}' />">Edit</a></td>
                <td><a href="<c:url value='/ts/instance/remove/${ts.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>


</body>
</html>
