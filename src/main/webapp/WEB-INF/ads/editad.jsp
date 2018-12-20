<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Create a new Ad" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
    <h1>Edit the Ad</h1>
    <form action="/ads" method="post">
        <div class="form-group">
            <label for="title">New Title</label>
            <input id="title" name="title" class="form-control" type="text" value="${Ad.title}">
        </div>
        <div class="form-group">
            <label for="description">New Description</label>
            <textarea id="description" name="description" class="form-control">${Ad.description}</textarea>
        </div>
        <input type="hidden" name="oldName" value="${Ad.title}">
        <input type="hidden" name="oldId" value="${Ad.id}">
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</body>
</html>
