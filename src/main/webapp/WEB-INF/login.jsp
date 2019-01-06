<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container">
        <h1>Please Log In</h1>
        <%--displays error message if user has incorrect password--%>
        <c:if test="${incorrectPass != null}">
            <div class="alert alert-danger" role="alert">
                <p>Password did not match inputted user</p>
            </div>
        </c:if>
        <%--displays error message if no user with that username--%>
        <c:if test="${username != null}">
            <div class="alert alert-danger" role="alert">
                <p>No user with matching username</p>
            </div>
        </c:if>
        <form action="/login" method="POST">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" value="${username}">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
    </div>
</body>
</html>
