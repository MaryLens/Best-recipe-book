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

    <h4>List of users recipes:</h4>
    <#list recipes as recipe>
        <div>
            <p><a href="/recipe/${recipe.id}"><b>${recipe.title}</b></a><br>
                <#if recipe.image.size?has_content && recipe.image.size != 0>
                    <img src="/imageToRec/${recipe.image.id}" height="60px" alt="img ${recipe.title}"/><br>
                </#if>
            <hr>
        </div>

    <#else>
        <h5>No recipes!</h5>
    </#list>
</div>

</body>
</html>

</body>
</html>