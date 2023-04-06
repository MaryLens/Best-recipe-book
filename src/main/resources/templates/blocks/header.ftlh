<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
    <div class="col-md-3 mb-2 mb-md-0">
        <a href="/" class="d-inline-flex link-body-emphasis text-decoration-none">
            <svg class="bi" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
        </a>
    </div>
    <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="/" class="nav-link px-2">Home</a></li>
        <li><a href="/recipes/my" class="nav-link px-2">My Recipes</a></li>
        <li><a href="/recipes" class="nav-link px-2">All Recipes</a></li>
        <li><a href="/recipe/create" class="nav-link px-2">Add Recipe</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <#if currentUser??>

            <div>Hello, <a href="/user/${currentUser.id}"  class="me-2">${currentUser.name}</a>!</div>

            <button type="button" onclick="window.location.href='/logout'" class="btn btn-danger me-2">Logout</button>
        <#else>
            <button type="button" onclick="window.location.href='/login'" class="btn btn-outline-primary me-2">Login</button>
            <button type="button" onclick="window.location.href='/registration'" class="btn btn-primary">Sign-up</button>
        </#if>

    </div>
</header>