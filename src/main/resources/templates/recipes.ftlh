<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <title> All Recipes</title>
        <#include "blocks/settings.ftlh">
    </head>
    <body>

        <div class="container">
            <#include "blocks/header.ftlh">
            <h3>Add you own recipe!</h3>
            <h1>Create your own emotions and tastes!</h1>
            <hr>
            <form action="/recipe/search" method="get">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                Search by title: <input type="text" name="title"><br>
                <input type="submit" value="Search">
            </form>
            <hr>
            <h4>List of all recipes:</h4>
            <#list recipes as recipe>
                <div>
                    <p><b>${recipe.title}</b> ${recipe.authorUser.name} | <a href="/recipe/${recipe.id}">Details...</a></p>
                </div>
            <#else>
                <h5>No recipes!</h5>
            </#list>
            <hr>
        </div>

    </body>
</html>