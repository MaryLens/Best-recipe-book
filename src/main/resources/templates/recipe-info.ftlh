<!DOCTYPE html>
<html>
<head>
    <title> RECIPE BOOK </title>
</head>
<body>
<h1>Create your own emotions and tastes!</h1>
<hr>
<h4>Detailed info about recipe</h4>
<hr>
<#list images as img>
    <img src="/image/${img.id}" height="60px" alt="img ${recipe.title}"/><br><br>
</#list>
<b>Recipe title: </b>${recipe.title}<br>
<b>Author: </b>${recipe.author}<br>
<b>Recipe description: </b>${recipe.description}<br>
<b>Difficulty:</b> ${recipe.difficulty}<br>
<b>Category:</b> ${recipe.category}<br>
<b>Rating: </b>${recipe.rating}<br>
<b>Cooking time, minutes: </b>${recipe.cookingTimeMinutes}<br>
<hr>
<h3>Delete recipe</h3>
<form action = "/recipe/delete/${recipe.id}" method = "post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="submit" value="Delete recipe"/>
</form>
</body>
</html>