<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>TestSuite Page</title>
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
    Add a Test Suite
</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\suite.jsp</h3>

<c:url var="addAction" value="/suite/create"></c:url>

<form:form action="${addAction}" commandName="suiteDTO">
    <table>
        <c:if test="${!empty suiteDTO.name}">
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
        <tr>
            <td>
                <form:label path="project_id">
                    <spring:message text="project_id"/>
                </form:label>
            </td>
            <td>
                <select path="project_id" name="project_id">
                    <c:forEach var="item" items="${listProjects}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="tcMusters_id">
                    <spring:message text="tcMusters_id"/>
                </form:label>
            </td>
            <td>
                <select path="tcMusters_id" name="tcMusters_id" multiple size="12">
                    <c:forEach var="item" items="${listTcMusters}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty suiteDTO.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Test Suite"/>"/>
                </c:if>
                <c:if test="${empty suiteDTO.name}">
                    <input type="submit"
                           value="<spring:message text="Add Test Suite"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>
<br>

<c:if test="${!empty listSuitesDTO}">
<h3>Test Suite List</h3>
    <table class="tg">
        <tr>
            <th width="80">Suite ID</th>
            <th width="120">Suite Name</th>
            <th width="80">Suite project id</th>
            <th width="80">Suite created</th>
            <th width="120">Suite TC musters</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listSuitesDTO}" var="suite">
            <tr>
                <td>${suite.id}</td>
                <td>${suite.name}</td>
                <td>${suite.project_id}</td>
                <td>${suite.createdDateTime}</td>
                <td>
                    <c:forEach items="${suite.tcMusters_id}" var="tCMuster">
                        ${tCMuster},
                    </c:forEach>
                </td>
                <td><a href="<c:url value='edit/${suite.id}' />">Edit</a></td>
                <td><a href="<c:url value='remove/${suite.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>