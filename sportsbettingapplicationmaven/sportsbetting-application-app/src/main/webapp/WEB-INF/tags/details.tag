<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<form:form action="dashboard" cssClass="rounded-lg border border-primary" method="post" modelAttribute="detailsRequest">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.AccountDetails"/></h6>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="name" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="name-label"><spring:message code="Label.Name"/></span>
            </div>
            <form:input type="text" path="name" cssClass="form-control" aria-describedby="name-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="birth" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="birth-label"><spring:message code="Label.BirthDate"/></span>
            </div>
            <form:input type='date' path="birth" cssClass="form-control datepicker" aria-describedby="birth-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="accountNumber" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="account-label"><spring:message code="Label.AccountNumber"/></span>
            </div>
            <form:input type="text" path="accountNumber" cssClass="form-control" aria-describedby="account-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="currency" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="currency-label"><spring:message code="Label.Currency"/></span>
            </div>
            <form:select cssClass="custom-select custom-select" path="currency" items="${currencies}"
                         aria-describedby="curency-label"/>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="balance" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="balance-label"><spring:message code="Label.Balance"/></span>
            </div>
            <form:input type="number" path="balance" cssClass="form-control" aria-describedby="balance-label"/>
        </div>
        <div class="form-group">
            <form:button type="submit" class="btn btn-primary"><spring:message code="Label.Save"/></form:button>
        </div>
    </div>
</form:form>