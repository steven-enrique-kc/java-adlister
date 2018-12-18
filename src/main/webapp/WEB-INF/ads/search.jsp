<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<jsp:include page="/WEB-INF/partials/head.jsp">
		<jsp:param name="title" value="Search for Ads" />
	</jsp:include>
</head>
<body>
<div class="container">
	<h1>Search for Ads</h1>
	<form>
		<div class="form-group">
			<label for="search">Search</label>
			<input id="search" name="search" class="form-control" type="text">
		</div>
		<input type="submit" class="btn btn-block btn-primary">
	</form>
	<c:forEach var="ad" items="${ads}">
		<div class="col-md-6">
			<h2>${ad.title}</h2>
			<p>${ad.description}</p>
		</div>
	</c:forEach>

</div>
</body>
</html>
