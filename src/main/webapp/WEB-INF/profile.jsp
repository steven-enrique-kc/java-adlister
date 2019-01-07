<%@ taglib prefix="cd" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <div class="row text-center justify-content-center">
            <h1>Welcome, ${sessionScope.user.username}!</h1>
        </div>
        <div class="row text-center justify-content-center">
            <h2>Profile information</h2>
            <div>
                Name: <cd:out value="${username}"></cd:out>
                |
                Email: <cd:out value="${email}"></cd:out>
            </div>
            <br>
            <form action="/editprofile" method="get">
                <input type="submit" value="Edit Profile"
                       name="Submit" id="editprofile_submit"
                       class="btn btn-primary"/>
            </form>
        </div>
        <div class="row text-center justify-content-center">
            <div class="col-4">
                <h2 style="text-decoration: underline">Here are your Glads</h2>
                <br>
            </div>
        </div>
        <div class="row justify-content-center">
            <cd:forEach var="ad" items="${userAds}">
                <div class="col-md-4 ads">
                    <form name="submitForm${ad.id}" method="POST" action="/ads/indiv" class="noMargin">
                        <input type="hidden" name="param1" value="${ad.title}" style="display: none" >
                        <A HREF="javascript:document.submitForm${ad.id}.submit()">${ad.title}</A>
                    </form>
                    <p class="ads">${ad.description}</p>
                </div>
            </cd:forEach>
        </div>
    </div>



</body>
</html>
