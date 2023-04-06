<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title> My Recipes</title>
    <#include "blocks/settings.ftlh">
</head>
<body>

<div class="container">
    <#include "blocks/header.ftlh">
    <h3>My Recipes</h3>
    <#list recipes as recipe>
        <div>
            <p><a href="/recipe/${recipe.id}"><b>${recipe.title}</b></a><br>
                <#if recipe.image.size?has_content && recipe.image.size != 0>
                    <img src="/imageToRec/${recipe.image.id}" height="60px" alt="img ${recipe.title}"/><br>
                </#if>
            <hr>
        </div>
        <form method="post" action="/recipe/delete/${recipe.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <button type="submit">Delete</button>
        </form>
    <#else>
        <h5>No recipes!</h5>
    </#list>
    <hr>
</div>

</body>
</html>