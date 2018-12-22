<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="partials/head.jsp">
        <jsp:param name="title" value="Edit Your Profile" />
    </jsp:include>
</head>
<body>
<jsp:include page="partials/navbar.jsp" />
<div class="container">
    <h1>Edit Your Profile Information</h1>
    <c:if test="${passwordNoMatch != null}">
        <div class="alert alert-danger" role="alert">
            <p>Email must have a Capital Letter and One Number</p>
        </div>
    </c:if>
    <c:if test="${notFormatEmail != null}">
        <div class="alert alert-danger" role="alert">
            <p>Must enter valid email address</p>
        </div>
    </c:if>
    <c:if test="${passNotMatch != null}">
        <div class="alert alert-danger" role="alert">
            <p>Sorry Passwword Did not Match</p>
        </div>
    </c:if>
    <c:if test="${notFilled != null}">
        <div class="alert alert-danger" role="alert">
            <p>All Inputs Must be Filled!!</p>
        </div>
    </c:if>
    <c:if test="${hasDuplicate != null}">
        <div class="alert alert-danger" role="alert">
            <p>Sorry, Username Taken</p>
        </div>
    </c:if>
    <form action="/editprofile" method="post">
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
        <input type="submit" class="btn btn-primary btn-block">
    </form>
</div>
</body>
</html>
