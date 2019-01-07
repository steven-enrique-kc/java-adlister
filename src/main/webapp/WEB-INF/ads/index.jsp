<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
    </jsp:include>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <div class="row mb-5" style="margin-bottom: 30px">
        <h1>Here Are all the Glads!</h1>
    </div>
    <div class="row justify-content-center">
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6 ads">
            <form name="submitForm${ad.id}" method="POST" action="/ads/indiv" class="noMargin">
                <input type="hidden" name="param1" value="${ad.title}" style="display: none" >
                <a class="noMargin" href="javascript:document.submitForm${ad.id}.submit()">${ad.title}</a>
            </form>
            <p class="ads">${ad.description}</p>
        </div>
    </c:forEach>
    </div>
</div>


</body>
</html>
