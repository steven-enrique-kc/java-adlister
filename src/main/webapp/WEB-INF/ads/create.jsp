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
            <p style="color: red">Sorry, title has already been used.</p>
        </c:if>
        <c:if test="${allValues != null}">
            <p style="color: red">All fields must be filled in.</p>
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
                    <input name="1" value="1" type="checkbox"> For Sale
                    <input name="1" value="0" type="hidden">
                    <input name="2" value="1" type="checkbox"> Electronics
                    <input name="2" value="0" type="hidden">
                    <input name="3" value="1" type="checkbox"> Sports Equipment
                    <input name="3" value="0" type="hidden">
                    <input name="4" value="1" type="checkbox"> Outdoors
                    <input name="4" value="0" type="hidden">
                    <input name="5" value="1" type="checkbox"> Vehicles
                    <input name="5" value="0" type="hidden">
                    <input name="6" value="1" type="checkbox"> Free
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
