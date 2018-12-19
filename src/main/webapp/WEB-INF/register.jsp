<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Register For Our Site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="partials/navbar.jsp" />
    <div class="container">
        <h1>Please fill in your information.</h1>
        <c:if test="${passwordNoMatch != null}">
            <p style="color: red; font-weight: bold">Email must have a Capital Letter and One Number</p>
        </c:if>
        <c:if test="${notFormatEmail != null}">
            <p style="color: red; font-weight: bold">Must enter valid email address</p>
        </c:if>
        <c:if test="${passNotMatch != null}">
            <p style="color: red; font-weight: bold">Sorry Passwword Did not Match</p>
        </c:if>
        <c:if test="${notFilled != null}">
            <p style="color: red; font-weight: bold">All Inputs Must be Filled!!</p>
        </c:if>
        <c:if test="${hasDuplicate != null}">
            <p style="color: red; font-weight: bold">Sorry, Username Taken</p>
        </c:if>
        <form action="/register" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input id="username" name="username" class="form-control" type="text" value="${username}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input id="email" name="email" class="form-control" type="text" value="${email}">
            </div>
            <div class="form-group">
                <h5 style="font-weight: bold; text-decoration: underline">Password must have a Capital Letter and One Number</h5>
                <label for="password">Password</label>
                <input id="password" name="password" class="form-control" type="password" value="${password}">
            </div>
            <div class="form-group">
                <label for="confirm_password">Confirm Password</label>
                <input id="confirm_password" name="confirm_password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" id="registerSubmit">
        </form>
    </div>
</body>
</html>
