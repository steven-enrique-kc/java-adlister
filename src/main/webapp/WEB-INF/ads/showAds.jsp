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
        <jsp:param name="title" value="Indiv Add" />
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <jsp:include page="/WEB-INF/partials/adContent.jsp">
        <jsp:param name="title" value="${thisAdd.userId}" />
        <jsp:param name="title" value="${thisAdd.title}" />
        <jsp:param name="description" value="${thisAdd.description}" />
        <jsp:param name="userId" value="${thisAdd.userId}" />
    </jsp:include>
</div>
</body>
</html>
