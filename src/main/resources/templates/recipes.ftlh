<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title> RECIPE BOOK </title>
</head>
<body>
<h1>Create your own emotions and tastes!</h1>
<hr>
<form action="/recipe/main" method="get">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    Search by title: <input type="text" name="title"><br>
    <input type="submit" value="Search">
</form>
<hr>
<h4>List of all recipes:</h4>
<#list recipes as recipe>
    <div>
        <p><b>${recipe.title}</b> ${recipe.author} | <a href="/recipe/${recipe.id}">Details...</a></p>
    </div>
<#else>
    <h5>No recipes!</h5>
</#list>
<hr>
<h3>Add you own recipe!</h3>
<form action="/recipe/create" method="post" enctype="multipart/form-data">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    Title: <input autocomplete="on" type="text" name="title"/>
    Upload photo : <input type="file" name="file"/><br><br>
    Author: <input autocomplete="on" type="text" name="author"/><br><br>
    Description: <input autocomplete="on" type="text" name="description"/><br><br>
    Rating: <input autocomplete="on" type="number" name="rating"/><br><br>
    Difficulty: <select name="difficulty">
        <#list difficulties as difficulty>
            <option value="${difficulty}">${difficulty}</option>
        </#list>
    </select><br><br>
    Category: <select name="category">
        <#list categories as category>
            <option value="${category}">${category}</option>
        </#list>
    </select><br><br>
    Cooking time, minutes: <input autocomplete="on" type="number" name="cookingTimeMinutes"/><br><br>
    <div id="steps">
        <div class="step">
            <p>Step 1:</p>
            <textarea name="cookingSteps[0].description" rows="3"></textarea>
            <br>
            Upload photo: <input type="file" name="stepPhotos"/><br><br>
        </div>
    </div>
    <button id="addStepBtn" type="button">Add new step</button>

    <script>
        var stepsCount = 1;
        var addStepBtn = document.getElementById('addStepBtn');
        var stepsDiv = document.getElementById('steps');

        addStepBtn.addEventListener('click', function() {
            stepsCount++;

            var step = document.createElement('div');
            step.classList.add('step');
            step.innerHTML = '<p>Step ' + stepsCount + ':</p>' +
                '<textarea name="cookingSteps[' + (stepsCount - 1) + '].description" rows="3"></textarea><br>' +
                'Upload photo: <input type="file" name="stepPhotos"/><br><br>';

            stepsDiv.appendChild(step);
        });
    </script>

    <input type="submit" value="Add product"/>
</form>
</body>
</html>