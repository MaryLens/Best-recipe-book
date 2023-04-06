<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org">

    <head>
        <title> All Recipes</title>
        <#include "blocks/settings.ftlh">
    </head>
    <body>

        <div class="container">
            <#include "blocks/header.ftlh">
            <h3>All Recipes</h3>
            <h1>Create your own emotions and tastes!</h1>
            <hr>
            <form action="/recipe" method="get">
<#--                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />-->
                Search by title: <input type="text" name="title"><br>
                <input type="submit" value="Search">
            </form>
            Filter by:<br><br>
            <form action="/recipe" method="get">
            Difficulty: <select name="difficulty">
                <#list difficulties as difficulty>
                    <option value="${difficulty}">${difficulty}</option>
                </#list>
            </select><br><br>
                <input type="submit" value="Filter">
            </form>
            <form action="/recipe" method="get">
            Category: <select name="category">
                <#list categories as category>
                    <option value="${category}">${category}</option>
                </#list>
            </select><br><br>
                <input type="submit" value="Filter">
            </form>
            <hr>
            <h4>List of all recipes:</h4>
            <#list recipes as recipe>
                <div>
                    <p><a href="/recipe/${recipe.id}"><b>${recipe.title}</b></a><br>
                        <#if recipe.image.size?has_content && recipe.image.size != 0>
                            <img src="/imageToRec/${recipe.image.id}" height="60px" alt="img ${recipe.title}"/><br>
                        </#if>
                        <a href="/user/${recipe.authorUser.id}"> ${recipe.authorUser.name}</a> </p>
                    <hr>
                </div>
            <#else>
                <h5>No recipes!</h5>
            </#list>
            <hr>
        </div>

    </body>
</html>