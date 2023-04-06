<!DOCTYPE HTML>

<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Add recipe</title>
    <#include "blocks/settings.ftlh">
</head>
<body>
<div class="container">
    <#include "blocks/header.ftlh">
    <p> Create your own emotions and tastes!</p>
    <hr>
    <h3>Add you own recipe!</h3>
    <form action="/recipe/create" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        Title: <input autocomplete="on" type="text" name="title"/><br><br>
        <#--    Rating: <input autocomplete="on" type="number" name="rating"/><br><br>-->
        Upload photo : <input type="file" name="fileRecipe"/><br><br>
<#--    Author: <input autocomplete="on" type="text" name="author"/><br><br>-->
        Description: <input autocomplete="on" type="text" name="description"/><br><br>
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
        Cooking time, minutes: <input autocomplete="on" type="number" name="cookingTimeMinutes" min="1"/><br><br>
        Ingredients: <input autocomplete="on" type="text" name="ingredients" rows="3"/><br><br>
        <div id="steps">
            <div class="step">
                <p>Step 1:</p>
                <textarea name="cookingStepsDesc[]" rows="3"></textarea>
                <br>
                Upload photo: <input type="file" name="filesStep[]"/><br><br>
            </div>
        </div>
        <button id="addStepBtn" type="button">Add new step</button>

        <script>
            let stepsCount = 1;
            const addStepBtn = document.getElementById('addStepBtn');
            const stepsDiv = document.getElementById('steps');

            addStepBtn.addEventListener('click', function() {

                const step = document.createElement('div');
                stepsCount++;
                step.classList.add('step');
                step.innerHTML = '<p>Step ' + stepsCount + ':</p>' +
                    '<textarea name="cookingStepsDesc[]" rows="3"></textarea><br>' +
                    'Upload photo: <input type="file" name="filesStep[]"/><br><br>';

                stepsDiv.appendChild(step);
            });
        </script>

        <input type="submit" value="Add recipe"/>
    </form>
    </div>

</body>
</html>