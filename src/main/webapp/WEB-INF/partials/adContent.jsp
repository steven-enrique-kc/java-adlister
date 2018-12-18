<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="card" style="width: 18rem;">
    <c:choose>

    <c:when test="${param.picture == null}">
        <img class="card-img-top" src="https://d2rormqr1qwzpz.cloudfront.net/photos/2013/07/02/49514-c26-b005gsyxhw-1-l.jpg" alt="Happy Pic">
    </c:when>

    <c:otherwise>
        <img class="card-img-top" src="${param.picture}" alt="Happy Pic">
    </c:otherwise>

        </c:choose>

    <div class="card-body">
        <h5 class="card-title">${param.title}</h5>
        <p class="card-text">${param.description}</p>
        <form action="/ads" method="get">
            <input type="submit" value="Return to Ads"
                   name="Submit" id="frm1_submit" />
        </form>
        <form action="/ads/search" method="get">
            <input type="submit" value="Return to Ads Search"
                   name="Submit" id="frm2_submit" />
        </form>
    </div>
</div>



