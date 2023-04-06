<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <title> Login </title>
    <#include "blocks/settings.ftlh">
</head>
<body>
<div class="container">
    <#include "blocks/header.ftlh">

    <main class="w-100 p-4 d-flex justify-content-center pb-4">
        <form action="/login" method="post" enctype="multipart/form-data">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <h1 class="h4 mb-4">Please Log-in</h1>
            <!-- Email input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="username">Email address</label>
                <input type="email" name="username" autocomplete="off" required class="form-control" />

            </div>

            <!-- Password input -->
            <div class="form-outline mb-4">
                <label class="form-label" for="password">Password</label>
                <input type="password" name="password" autocomplete="off" required class="form-control" />

            </div>

            <div class="form-outline mb-2">
                <button type="submit" class="btn btn-primary btn-block mb-4">Log-in</button>
            </div>



            <!-- Register buttons -->
            <div class="text-center">
                <p>Not a member? <a href="/registration">Sign-up</a></p>
            </div>
        </form>
    </main>


</div>
</body>
</html>