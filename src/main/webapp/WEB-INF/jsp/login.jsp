

<html>
<head>
    <title>Login</title>
</head>
<br>


<% if(request.getParameter("logout")!= null){ %>
    <h5 style="color:blue;"> You have been logged out </h5>
<% } %>
<form action="/login" method='POST'>
    Username: <input type="text" name="username" /><br />
    Password: <input type="password" name="password" /><br />
    <% if(request.getParameter("error")!=null){ %>
        <h5 style="color:red;"> Wrong login data </h5>
    <% } %>
    <input type="submit" value="Login "/>
</form>


</body>
</html>


