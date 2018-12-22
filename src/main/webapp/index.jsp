<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Welcome to my site!" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />
    <div class="container text-center">
        <div class="row justify-content-center text-center">
            <div class="justify-content-center text-center">
                <h1>Welcome to the Gladlister!</h1>
                <h6>The Place for All your Postive Afirmations!!</h6>
                <br>
                <img src="http://4.bp.blogspot.com/_iBcEv0GPMy0/TDonaiJpQTI/AAAAAAAAB2w/3Tq_62RXwOo/s1600/glad.jpg" alt="Happy Man">
            </div>
        </div>
    </div>
</body>
</html>
