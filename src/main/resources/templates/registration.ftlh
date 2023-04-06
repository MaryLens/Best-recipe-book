<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <title> Registration </title>
    <#include "blocks/settings.ftlh">
</head>
<body>

<div class="container">
    <#include "blocks/header.ftlh">

    <h3>Please Sign-up</h3>
    <form action="/registration" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <#if bindingResult??>
            <#if bindingResult.hasErrors()>
                <#list bindingResult.allErrors as error>
                    <span class="error">${error.defaultMessage}</span>
                </#list>
            </#if>
        </#if>

        <#if email_exists??>
            <span class="error" style="color: red;">User with this email exists</span><br><br>
        </#if>

        Name: <input type="text" name="name" autocomplete="off" minlength="6" required/><br><br>
        Date of Birth: <input type="date" id="dateOfBirth" name="dateOfBirth" autocomplete="off"><br><br>
        Occupation: <input type="text" name="occupation" autocomplete="off"/><br><br>
        Email: <input type="email" name="email" autocomplete="off" required/><br><br>
        Password: <input type="password" name="password" autocomplete="off" required minlength="6"/><br><br>
        <input type="submit" value="Sign-up"/>
        <div>
            <p>Have an account? <a href="/login">Log-in</a></p>
        </div>
    </form>

</div>

</body>
</html>

