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
        <h1>Welcome, ${sessionScope.user.username}!</h1>
        <h2>Profile information</h2>
            <div>
                Name: <cd:out value="${username}"></cd:out>
            </div>
            <div>
    Email: <cd:out value="${email}"></cd:out>
            </div>
        <div>
            <form action="/editprofile" method="get">
                <input type="submit" value="Edit Profile"
                       name="Submit" id="editprofile_submit" />
            </form>
        </div>        <h2>Here are your ads</h2>
        <form action="/editprofile" method="get">
            <input type="submit" value="Edit Profile"
                   name="Submit" id="frm2_submit" />
        </form>
        <h2>Here are your ads</h2>
        <cd:forEach var="ad" items="${userAds}">
            <div class="col-md-6">
                <form name="submitForm${ad.id}" method="POST" action="/ads/indiv">
                    <input type="hidden" name="param1" value="${ad.title}" style="display: none" >
                    <A HREF="javascript:document.submitForm${ad.id}.submit()">${ad.title}</A>
                </form>

                <p>${ad.description}</p>
            </div>

            </cd:forEach>
    </div>



</body>
</html>
