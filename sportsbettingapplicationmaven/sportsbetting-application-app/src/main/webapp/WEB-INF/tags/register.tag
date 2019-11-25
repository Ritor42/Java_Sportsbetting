<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<form:form action="register" cssClass="w-50 rounded-lg border border-primary" method="post"
           modelAttribute="registerRequest">
    <div class="text-light bg-primary p-2">
        <h4>Register</h4>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="name" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="name-label">Name</span>
            </div>
            <form:input type="text" path="name" cssClass="form-control" aria-describedby="name-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="birth" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="birth-label">Birth</span>
            </div>
            <form:input type='date' path="birth" cssClass="form-control datepicker" aria-describedby="birth-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="accountNumber" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="account-label">Account number</span>
            </div>
            <form:input type="text" path="accountNumber" cssClass="form-control" aria-describedby="account-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="currency" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="currency-label">Currency</span>
            </div>
            <form:select cssClass="custom-select custom-select" path="currency" aria-describedby="currency-label">
                <option>HUF</option>
                <option>EUR</option>
                <option>USD</option>
            </form:select>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="balance" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="balance-label">Balance</span>
            </div>
            <form:input type="number" path="balance" cssClass="form-control" aria-describedby="balance-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="email" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="email-label">Email</span>
            </div>
            <form:input type="email" path="email" cssClass="form-control" aria-describedby="email-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="password" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="password-label">Password</span>
            </div>
            <form:input type="password" path="password" cssClass="form-control" aria-describedby="password-label"/>
        </div>
        <div class="form-group">
            <form:button type="submit" class="btn btn-primary">Register</form:button>
        </div>
    </div>
</form:form>