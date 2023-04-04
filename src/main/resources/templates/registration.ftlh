<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title> Registration </title>
</head>
<body>


<h3>Please Sign-up</h3>
<form action="/registration" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    Username: <input type="text" name="username"/><br><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="submit" value="Sign-up"/>
    <div class="text-center">
        <p>Have an account? <a href="/login">Log-in</a></p>
    </div>
</form>
</body>
</html>

