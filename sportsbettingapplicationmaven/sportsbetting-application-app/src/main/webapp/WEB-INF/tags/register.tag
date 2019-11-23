<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<form action="<c:url value='/register' />" method="post" class="w-50 rounded-lg border border-primary">
    <div class="text-light bg-primary p-2">
        <h4>Register</h4>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="name-label">Name</span>
            </div>
            <input type="text" name="name" class="form-control" aria-describedby="name-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="birth-label">Birth</span>
            </div>
            <input type='date' name="birth" value="1990-01-01" class="form-control datepicker"
                   aria-describedby="birth-label" required/>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="account-label">Account number</span>
            </div>
            <input type="text" name="accountNumber" class="form-control" aria-describedby="account-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="currency-label">Currency</span>
            </div>
            <select class="custom-select custom-select" name="currency" aria-describedby="currency-label">
                <option>HUF</option>
                <option>EUR</option>
                <option>USD</option>
            </select>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="balance-label">Balance</span>
            </div>
            <input type="number" name="balance" class="form-control" aria-describedby="balance-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="email-label">Email</span>
            </div>
            <input type="email" name="email" class="form-control" aria-describedby="email-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="password-label">Password</span>
            </div>
            <input type="password" name="password" class="form-control" aria-describedby="password-label" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </div>
</form>