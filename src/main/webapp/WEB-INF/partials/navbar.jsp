<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
    <div class="container-fluid glad-nav">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header glad-nav">
            <a class="navbar-brand glad-nav" href="/">Adlister</a>
        </div>
        <ul class="nav navbar-nav navbar-right glad-nav ">
            <c:choose>

            <c:when test="${user != null}">
                <li><a href="/ads/create">Create Glads</a></li>
                <li><a href="/profile">User Profile</a></li>
                <li><a href="/ads/search">Search Glads</a></li>
                <li><a href="/ads">All Glads</a></li>
                <li><a href="/logout">Logout</a></li>
            </c:when>

            <c:otherwise>
                <li><a class="text-light" href="/ads/search">Search Glads</a></li>
                <li><a href="/ads">All Glads</a></li>
                <li><a href="/register">Register</a></li>
                <li><a href="/login">Login</a></li>
            </c:otherwise>

            </c:choose>
        </ul>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


