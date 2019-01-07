<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<jsp:include page="/WEB-INF/partials/head.jsp">
		<jsp:param name="title" value="Search for Ads" />
	</jsp:include>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />
<div class="container">
	<div class="row text-center justify-content-center">
		<h1>Search for Glads</h1>
	</div>
	<br>
	<div class="row text-center justify-content-center">
	<form action="/ads/search" method="post">
		<div class="form-group">
			<label for="search">Search</label>
			<br>
			<input id="search" name="search" class="form-control" type="text">
		</div>
		<br>
		<input type="submit" class="btn btn-block btn-primary">
	</form>
	</div>
	<c:if test="${noResult != null}">
		<div class="alert alert-danger" role="alert">
			<p>Sorry, no results found...</p>
		</div>
	</c:if>
	<div class="row justify-content-center">
	<c:forEach var="ad" items="${ads}">
		<div class="col-md-6 ads">
			<form name="submitForm${ad.id}" method="POST" action="/ads/indiv" class="noMargin">
				<input type="hidden" name="param1" value="${ad.title}" style="display: none" >
				<A HREF="javascript:document.submitForm${ad.id}.submit()">${ad.title}</A>
			</form>
			<p class="ads">${ad.description}</p>
		</div>
	</c:forEach>
	</div>
</div>
</body>
</html>
