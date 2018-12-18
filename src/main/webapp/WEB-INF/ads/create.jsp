<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
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
                <select name="category-select">
                    <option value="For Sale">Volvo</option>
                    <option value="Electronics">Saab</option>
                    <option value="Sports Equipment">Opel</option>
                    <option value="audi">Audi</option>
                </select>
                <label for="category">Category</label>
                <input id="category" name="category" class="form-control" type="text">
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
