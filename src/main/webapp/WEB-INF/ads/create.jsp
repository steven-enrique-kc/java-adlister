<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container text-center">
        <h1>Create a new Ad</h1>
        <c:if test="${duplicateTitle != null}">
            <div class="alert alert-danger" role="alert">
                <p>Sorry, title has already been used.</p>
            </div>
        </c:if>
        <c:if test="${allValues != null}">
            <div class="alert alert-danger" role="alert">
                <p>All fields must be filled in.</p>
            </div>
        </c:if>
        <form action="/ads/create" method="post">
            <div class="form-group">
                <label for="title">Title</label>
                <input id="title" name="title" class="form-control" type="text" value="${title}">
            </div>
            <div class="form-group">
                <label>Category</label>
                    <br>
                <p>(Please select at least one category)</p>
                    <label for="1" class="border-right">For Sale |<br><input name="1" value="1" type="checkbox" id="1"></label>
                    <input name="1" value="0" type="hidden">
                    <label for="2"> Electronics | <br><input name="2" value="1" type="checkbox" id="2"></label>
                    <input name="2" value="0" type="hidden">
                    <label for="3"> Sports Equipment | <br><input name="3" value="1" type="checkbox" id="3"></label>
                    <input name="3" value="0" type="hidden">
                    <label for="3"> Outdoors | <br><input name="4" value="1" type="checkbox" id="4"></label>
                    <input name="4" value="0" type="hidden">
                    <label for="5"> Vehicles | <br><input name="5" value="1" type="checkbox" id="5"></label>
                    <input name="5" value="0" type="hidden">
                    <label for="6"> Free<br><input name="6" value="1" type="checkbox" id="6"></label>
                    <input name="6" value="0" type="hidden">
                    <br>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description" class="form-control" type="text">${description}</textarea>
            </div>
            <input type="submit" class="btn btn-block btn-primary">
        </form>
    </div>
</body>
</html>
