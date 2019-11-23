<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ tag import="com.example.sportsbetting.domain.Currency" %>
<%@ tag import="com.example.sportsbetting.dto.PlayerDto" %>
<%@ tag import="java.text.SimpleDateFormat" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<%
    PlayerDto player = (PlayerDto) request.getAttribute("player");

    String selectedHUF = (player.getCurrency() == Currency.HUF) ? "selected" : "";
    String selectedEUR = (player.getCurrency() == Currency.EUR) ? "selected" : "";
    String selectedUSD = (player.getCurrency() == Currency.USD) ? "selected" : "";

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
%>
<form action="<c:url value='/user/dashboard' />" method="post" class="rounded-lg border border-primary">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.AccountDetails"/></h6>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="name-label"><spring:message code="Label.Name"/></span>
            </div>
            <input type="text" name="name" value="<%=player.getName()%>" class="form-control"
                   aria-describedby="name-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="birth-label"><spring:message code="Label.BirthDate"/></span>
            </div>
            <input type='date' name="birth" value="<%=format.format(player.getBirth())%>"
                   class="form-control datepicker" aria-describedby="birth-label" required/>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="account-label"><spring:message code="Label.AccountNumber"/></span>
            </div>
            <input type="text" name="accountNumber" value="<%=player.getAccountNumber()%>" class="form-control"
                   aria-describedby="account-label" required>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="currency-label"><spring:message code="Label.Currency"/></span>
            </div>
            <select class="custom-select custom-select" name="currency" aria-describedby="curency-label">
                <option <%=selectedHUF%>>HUF</option>
                <option <%=selectedEUR%>>EUR</option>
                <option <%=selectedUSD%>>USD</option>
            </select>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="balance-label"><spring:message code="Label.Balance"/></span>
            </div>
            <input type="number" name="balance" value="<%=player.getBalance()%>" class="form-control"
                   aria-describedby="balance-label" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary"><spring:message code="Label.Save"/></button>
        </div>
    </div>
</form>