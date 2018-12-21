<%--
  Created by IntelliJ IDEA.
  User: kennethmcinturf
  Date: 12/20/18
  Time: 2:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Ad A Picture" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container text-center">
<h1>Ad title: ${ad.title}</h1>
    <form action="/count" method="post">
        <label for="picture">Add a link to a picture</label>
        <input type="hidden" name="title" value="${ad.title}">
        <input type="text" id="picture" name="picture">
        <input type="submit">
    </form>
</div>

</body>
</html>
