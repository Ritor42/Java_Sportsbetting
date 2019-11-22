<%@tag description="Login template" pageEncoding="UTF-8" %>
<form action="login" method="post" class="w-25 rounded-lg border border-primary">
    <div class="text-light bg-primary p-2">
        <h4>Login</h4>
    </div>
    <div class="container p-3">
        <div class="form-group">
            <input type="email" name="email" class="form-control" aria-describedby="emailHelp" placeholder="Email">
        </div>
        <div class="form-group">
            <input type="password" name="password" class="form-control" placeholder="Password">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </div>
</form>