<%--
  Created by IntelliJ IDEA.
  User: kennethmcinturf
  Date: 12/17/18
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="${ads.title}" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/adContent.jsp">
    <jsp:param name="title" value="${ads.title}" />
    <jsp:param name="description" value="${ads.description}" />
    <jsp:param name="user" value="${user}" />
</jsp:include>

</body>
</html>
