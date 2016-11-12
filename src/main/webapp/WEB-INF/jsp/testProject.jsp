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
    Add a Test Project
</h1>
<h3>C:\workspace\IntelliJ\diplomka\src\main\webapp\WEB-INF\jsp\testProject.jsp</h3>

<c:url var="addAction" value="/project/create"></c:url>

<form:form action="${addAction}" commandName="testProject">
    <table>
        <c:if test="${!empty testProject.name}">
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
                <form:label path="projectOwner_id">
                    <spring:message text="projectOwner_id"/>
                </form:label>
            </td>
            <td>
                <select path="projectOwner_id" name="projectOwner_id">
                    <c:forEach var="item" items="${listPersons}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="projectMembers_id">
                    <spring:message text="projectMembers_id"/>
                </form:label>
            </td>
            <td>
                <select path="projectMembers_id" name="projectMembers_id" multiple size="12">
                    <c:forEach var="item" items="${listPersons}">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
            <%--<tr>
                <td>
                    <form>
                        <select name="projectMembers_id" multiple size="5">
                            <option>15</option>
                            <option>20</option>
                            <option>40</option>
                            <option>41</option>
                            <option>42</option>
                            <option>43</option>
                            <option>44</option>
                        </select>
                    </form>
                </td>--%>
        </tr>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<form:label path="login">--%>
            <%--<spring:message text="login"/>--%>
            <%--</form:label>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<form:input path="login"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
        <tr>
            <td colspan="2">
                <c:if test="${!empty testProject.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Project"/>"/>
                </c:if>
                <c:if test="${empty testProject.name}">
                    <input type="submit"
                           value="<spring:message text="Add Project"/>"/>
                </c:if>
            </td>
        </tr>

    </table>
</form:form>
<br>
<h3>Projects List</h3>
<c:if test="${!empty listProjects}">
    <table class="tg">
        <tr>
            <th width="80">Project ID</th>
            <th width="120">Project Name</th>
            <th width="120">project owner</th>
            <th width="190">project members</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listProjects}" var="testProject">
            <tr>
                <td>${testProject.id}</td>
                <td>${testProject.name}</td>
                <td>${testProject.projectOwner_id}</td>
                <td>
                    <c:forEach items="${testProject.projectMembers_id}" var="projectMember">
                ${projectMember},
                </c:forEach>
                </td>
                    <%--<td>${testProject.login}</td>--%>
                    <%--<td><a href="<c:url value='/edit/${person.id}' />" >Edit</a></td>--%>
                    <%--<td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>