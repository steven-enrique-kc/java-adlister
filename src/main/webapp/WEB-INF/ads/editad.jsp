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
    <h1>Edit the Glad</h1>
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
                <label for="1"> For Sale|<br><input name="1" value="1" type="checkbox" id="1"></label>
                <input name="1" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="1"> For Sale|<br><input name="1" value="1" type="checkbox" id="1" checked></label>
                <input name="1" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Electronics\") == -1}">
                <label for="2"> Electronics |<br><input name="2" value="1" type="checkbox" id="2"></label>
                <input name="2" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="2"> Electronics |<br><input name="2" value="1" type="checkbox" id="2" checked></label>
                <input name="2" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Sports Equipment\") == -1}">
                <label for="3"> Sports Equipment |<br><input name="3" value="1" type="checkbox" id="3"></label>
                <input name="3" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="3"> Sports Equipment |<br><input name="3" value="1" type="checkbox" id="3" checked></label>
                <input name="3" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Outdoors\") == -1}">
                <label for="4"> Outdoors |<br><input name="4" value="1" type="checkbox" id="4"></label>
                <input name="4" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="4"> Outdoors |<br><input name="4" value="1" type="checkbox" id="4" checked></label>
                <input name="4" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Vehicles\") == -1}">
                <label for="5"> Vehicles |<br><input name="5" value="1" type="checkbox" id="5"></label>
                <input name="5" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="5"> Vehicles |<br><input name="5" value="1" type="checkbox" id="5" checked></label>
                <input name="5" value="0" type="hidden">
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${categories.indexOf(\"Free Stuff\") == -1}">
                <label for="6"> Free<br><input name="6" value="1" type="checkbox" id="6"></label>
                <input name="6" value="0" type="hidden">
            </c:when>
            <c:otherwise>
                <label for="6"> Free<br><input name="6" value="1" type="checkbox" id="6" checked></label>
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
