<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/home">Home</a></li>

            <sec:authorize access="isAuthenticated()">
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">User<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/user/user1">User1</a></li>
                    <li><a href="/user/user2">User2</a></li>

                </ul>
            </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/admin/admin1">Admin1</a></li>
                    <li><a href="/admin/admin2">Admin2</a></li>
                </ul>
            </li>
            </sec:authorize>
            <li><a href="/logout">Logout</a></li>
        </ul>
    </div>
</nav>
</div>

</body>
</html>
