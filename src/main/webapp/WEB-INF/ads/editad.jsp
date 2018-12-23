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
        <c:choose>
            <c:when test="${categories.indexOf(\"For Sale\") == -1}">
                <input name="1" value="1" type="checkbox"> For Sale
                <input name="1" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="1" value="1" type="checkbox" checked> For Sale
                <input name="1" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Electronics\") == -1}">
                <input name="2" value="1" type="checkbox"> Electronics
                <input name="2" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="2" value="1" type="checkbox" checked> Electronics
                <input name="2" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Sports Equipment\") == -1}">
                <input name="3" value="1" type="checkbox"> Sports Equipment
                <input name="3" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="3" value="1" type="checkbox" checked> Sports Equipment
                <input name="3" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Outdoors\") == -1}">
                <input name="4" value="1" type="checkbox"> Outdoors
                <input name="4" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="4" value="1" type="checkbox" checked> Outdoors
                <input name="4" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Vehicles\") == -1}">
                <input name="5" value="1" type="checkbox"> Vehicles
                <input name="5" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="5" value="1" type="checkbox" checked> Vehicles
                <input name="5" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Free Stuff\") == -1}">
                <input name="6" value="1" type="checkbox"> Free
                <input name="6" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <input name="6" value="1" type="checkbox" checked> Free
                <input name="6" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <input type="hidden" name="oldName" value="${Ad.title}">
        <input type="hidden" name="oldId" value="${Ad.id}">
        <input type="submit" class="btn btn-block btn-primary">
    </form>
</div>
</body>
</html>
