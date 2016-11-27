<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Person Page</title>
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
    Add a Person
</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\tsRun.jsp</h3>

<c:url var="addAction" value="/ts/run"></c:url>

<form:form action="${addAction}" commandName="ts">
    <table>
        <c:if test="${!empty ts.action}">
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
                    <spring:message text="Akce"/>
                </form:label>
            </td>
            <td>
                <form:input path="action" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="expected">
                    <spring:message text="Očekávané"/>
                </form:label>
            </td>
            <td>
                <form:input path="expected" readonly="true"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="result">
                    <spring:message text="Výsledek"/>
                </form:label>
            </td>
            <td>
                <form:input path="result"/>
            </td>
        </tr>
        <tr>

            <td>
                <%--<form:input path="tcInstance_id" readonly="true" size="8" disabled="true"/>--%>
                <form:hidden path="tcInstance_id"/>
            </td>
        </tr>

        <td colspan="2">
            <c:if test="${!empty ts.action}">
                <input type="submit"
                       value="<spring:message text="Edit Person"/>"/>
            </c:if>
            <c:if test="${empty ts.action}">
                <input type="submit"
                       value="<spring:message text="Add Person"/>"/>
            </c:if>
        </td>
        </tr>
    </table>
</form:form>

</body>
</html>