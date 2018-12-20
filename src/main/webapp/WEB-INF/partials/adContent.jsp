<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card" style="width: 18rem;">
    <c:choose>

    <c:when test="${param.picture == \"\"}">
        <img class="card-img-top" src="https://d2rormqr1qwzpz.cloudfront.net/photos/2013/07/02/49514-c26-b005gsyxhw-1-l.jpg" alt="Happy Pic">
    </c:when>

    <c:otherwise>
        <img class="card-img-top" src="${param.picture}" alt="Happy Pic" style="width: 600px; height: 300px">
    </c:otherwise>

        </c:choose>

    <%--<h1>User ID : ${sessionScope.user.id}</h1>--%>
    <%--<h1>Ad user ID : ${param.userId}</h1>--%>
    <%--<h1>Ad ID : ${sessionScope.thisAdd.id}</h1>--%>


    <div class="card-body">
        <h5 class="card-title">${param.title}</h5>
        <p class="card-text">${param.description}</p>
        <c:forEach var="catagory" items="${param.categories}">
            <p>Categories: ${catagory}</p>
        </c:forEach>
        <form action="/ads" method="get">
            <input type="submit" value="Return to Ads"
                   name="Submit" id="frm1_submit" />
        </form>
        <form action="/ads/search" method="get">
            <input type="submit" value="Return to Ads Search"
                   name="Submit" id="frm2_submit" />
        </form>
    </div>

    <c:if test="${sessionScope.user.id == param.userId}">
        <form name="submitForm" method="post" action="/editad">
            <input type="hidden" name="title" value="${param.title}">
            <input type="submit" name="param1" value="Edit Ad">
            <A HREF="javascript:document.submitForm.submit()"></A>
        </form>    </c:if>

    <form name="submitPic" method="post" action="/ads/pic">
        <input type="hidden" name="title" value="${param.title}">
        <input type="submit" name="param1" value="Add Picture">
        <A HREF="javascript:document.submitPic.submit()"></A>
    </form>


</div>



