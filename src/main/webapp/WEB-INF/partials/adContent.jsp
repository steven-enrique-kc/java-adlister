<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="container">
    <c:choose>

    <c:when test="${param.picture == \"\"}">
        <div class="row text-center justify-content-center">
            <img src="https://d2rormqr1qwzpz.cloudfront.net/photos/2013/07/02/49514-c26-b005gsyxhw-1-l.jpg" alt="Glad Pic">
        </div>
    </c:when>

    <c:otherwise>
        <div class="row text-center justify-content-center">
            <img src="${param.picture}" alt="Happy Pic" style="width: 600px; height: 300px">
        </div>
    </c:otherwise>

        </c:choose>

        <div class="row text-center justify-content-center">
            <h1 class="card-title">${param.title}</h1>
            <p class="card-text">${param.description}</p>
            <p>Categories: ${param.categories}</p>
        </div>
    <div class="row text-center justify-content-center">
            <form action="/ads" method="get">
                <input type="submit" value="Return to Glads"
                       name="Submit" id="frm1_submit"
                       class="btn btn-primary btn-block"/>
            </form>
            <form action="/ads/search" method="get">
                <input type="submit" value="Return to Glads Search"
                       name="Submit" id="frm2_submit"
                       class="btn btn-success btn-block"/>
            </form>
        <form name="submitPic" method="post" action="/ads/pic">
            <input type="hidden" name="title" value="${param.title}">
            <input type="submit" name="param1" value="Add Picture"
                   class="btn btn-info btn-block">
            <A HREF="javascript:document.submitPic.submit()"></A>
        </form>
            <c:if test="${sessionScope.user.id == param.userId}">
                <form name="submitForm" method="post" action="/editad">
                    <input type="hidden" name="title" value="${param.title}">
                    <input type="submit" name="param1" value="Edit Glad"
                           class="btn btn-warning btn-block">
                    <A HREF="javascript:document.submitForm.submit()"></A>
                </form>

                <form name="submitForm" method="post" action="/deletead">
                    <input type="hidden" name="title" value="${param.title}">
                    <input type="submit" name="param1" value="Delete Glad"
                           class="btn btn-danger btn-block">
                    <A HREF="javascript:document.submitForm.submit()"></A>
                </form></c:if>
    </div>
    </div>



