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
	<form action="/ads/search" method="post">
		<div class="form-group">
			<label for="search">Search</label>
			<input id="search" name="search" class="form-control" type="text">
		</div>
		<input type="submit" class="btn btn-block btn-primary">
	</form>
</div>
</body>
</html>
