<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Test Case Page</title>
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
    Add a Test Case
</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\tc.jsp</h3>

<c:url var="addAction" value="/tc/create"></c:url>

<form:form action="${addAction}" commandName="tc">
    <table>
        <c:if test="${!empty tc.id}">
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
                <form:label path="name">
                    <spring:message text="Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<form:label path="createdDateTime">--%>
                    <%--<spring:message text="Created"/>--%>
                <%--</form:label>--%>
            <%--</td>--%>
            <%--<td>--%>
                <%--<form:input path="createdDateTime"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <tr>
            <td>
                <form:label path="project_id">
                    <spring:message text="Test Project"/>
                </form:label>
            </td>
            <td>
                <select path="project_id" name="project_id">
                    <c:forEach var="item" items="${listProjects}">
                        <option value="${item.id}" selected="">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <c:if test="${!empty tc.name}">
                    <input type="submit"
                           value="<spring:message text="Edit TC"/>"/>
                </c:if>
                <c:if test="${empty tc.name}">
                    <input type="submit"
                           value="<spring:message text="Add TC"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>
<br>
<h3>TC List</h3>
<c:if test="${!empty listTCMusters}">
    <table class="tg">
        <tr>
            <th width="80">TC ID</th>
            <th width="120">Name</th>
            <th width="120">Created</th>
            <th width="190">Project</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listTCMusters}" var="tc">
            <tr>
                <td>${tc.id}</td>
                <td>${tc.name}</td>
                <td>${tc.createdDateTime}</td>
                <td>${tc.project_id}</td>
                <td><a href="<c:url value='edit/${tc.id}' />">Edit</a></td>
                <td><a href="<c:url value='remove/${tc.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>