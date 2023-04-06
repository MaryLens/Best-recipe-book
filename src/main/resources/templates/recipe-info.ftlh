<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title> ${recipe.title} </title>
    <#include "blocks/settings.ftlh">
</head>
<body>

<div class="container">
    <#include "blocks/header.ftlh">
    <h1>Create your own emotions and tastes!</h1>
    <hr>
    <h4>Detailed info about recipe</h4>
    <hr>
    <b>Recipe title: </b>${recipe.title}<br>
    <#if recipe.image.size?has_content && recipe.image.size != 0>
        <img src="/imageToRec/${recipe.image.id}" height="60px" alt="img ${recipe.title}"/><br><br>
    </#if>
    <b>Author: </b>${recipe.authorUser.name}<br>
    <b>Recipe description: </b>${recipe.description}<br>
    <b>Difficulty:</b> ${recipe.difficulty}<br>
    <b>Category:</b> ${recipe.category}<br>
    <#--<b>Rating: </b>${recipe.rating}<br>-->
    <b>Cooking time, minutes: </b>${recipe.cookingTimeMinutes}<br>
    <b>Ingredients: </b>${recipe.ingredients}<br>
    <hr>
    <h4>Cooking steps:</h4>
    <#list recipe.cookingSteps as step>
        <h5>Step ${step.number}</h5>
        <p>${step.description}</p>
        <#if step.image.size?has_content && step.image.size != 0>
            <img src="/imageToStep/${step.image.id}" height="60px" alt="img ${recipe.title}"/><br><br>
        </#if>
    </#list>
    <hr>
    <#if currentUser??>
        <#if currentUser.id == recipe.authorUser.id>
            <h3>Delete recipe</h3>
            <form action = "/recipe/delete/${recipe.id}" method = "post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <input type="submit" value="Delete recipe"/>
            </form>
        </#if>
    </#if>


</div>

</body>
</html>

