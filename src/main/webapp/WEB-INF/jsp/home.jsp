<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<h2> Welcome!</h2>
<% if (request.isUserInRole("ROLE_USER")) { %>
ROLE: USER </br>
<%} if (request.isUserInRole("ROLE_ADMIN")) { %>
ROLE: ADMIN </br>
<% } %>
<a href="/logout" />Logout</a>
</body>
</html>
