<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<jsp:include page="menuBar.jsp" />
<h2>Hello <sec:authentication property="principal.username" />!</h2>

</body>
</html>


