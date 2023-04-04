<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title> Login </title>
</head>
<body>


<h3>Please Log-in</h3>
<form action="/login" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    Username: <input type="text" name="username"/><br><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="submit" value="Log-in"/>
    <div class="text-center">
        <p>Not a member? <a href="/registration">Sign-up</a></p>
    </div>
</form>
</body>
</html>