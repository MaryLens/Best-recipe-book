<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org" lang="en">

<head>
    <title> ${user.name} </title>
    <#include "blocks/settings.ftlh">
</head>
<body>

<div class="container">
    <#include "blocks/header.ftlh">
    <hr>
    <h4>Detailed info about user</h4>
    <hr>
    <b>Name: </b>${user.name}<br>

    <b>Date of Birth: </b>${user.dateOfBirth}<br>
    <b>Occupation: </b>${user.occupation}<br>

    <hr>
    <h4>Cooking steps:</h4>
    <#list recipes as recipe>
    <div>
        <p><b>${recipe.title}</b>  <a href="/recipe/${recipe.id}">Details...</a></p>
    </div>
    <#else>
        <h5>No recipes!</h5>
    </#list>
</div>

</body>
</html>