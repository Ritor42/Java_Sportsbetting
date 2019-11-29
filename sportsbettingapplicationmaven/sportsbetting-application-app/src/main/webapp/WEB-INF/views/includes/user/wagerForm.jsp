<%@ page import="com.example.sportsbetting.dto.OutcomeOddDto" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="com.example.sportsbetting.dto.OutcomeDto" %>
<%@ page import="com.example.sportsbetting.dto.BetDto" %>
<%@ page import="com.example.sportsbetting.dto.SportEventDto" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%
    Iterable<OutcomeOddDto> odds = (Iterable<OutcomeOddDto>) request.getAttribute("odds");
    DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
%>
<form:form action="wager" cssClass="rounded-lg border border-primaryy" method="post" modelAttribute="wagerRequest">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.Wager"/></h6>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="event-label"><spring:message code="Label.EventTitle"/></span>
            </div>
            <form:select class="custom-select custom-select" path="oddId" aria-describedby="event-label">
                <% for (OutcomeOddDto odd : odds) {
                    OutcomeDto outcome = odd.getOutcome();
                    BetDto bet = outcome.getBet();
                    SportEventDto event = bet.getEvent();
                %>
                <option value="<%=odd.getId()%>"><%=event.getTitle()%> (<%=formatter.format(event.getStartDate())%>
                    ) <%=bet.getDescription()%> <%=outcome.getDescription()%> Odds 1:<%=odd.getValue()%>
                </option>
                <%
                    }
                %>
            </form:select>
        </div>
        <div class="input-group form-group">
            <div class="input-group">
                <form:errors path="amount" cssClass="alert alert-danger"/>
            </div>
            <div class="input-group-prepend">
                <span class="input-group-text" id="amount-label"><spring:message code="Label.WagerAmount"/></span>
            </div>
            <form:input type="number" path="amount" cssClass="form-control" placeholder="Amount"
                        aria-describedby="amount-label"/>
        </div>
        <div class="form-group">
            <form:button type="submit" class="btn btn-primary"><spring:message code="Label.Add"/></form:button>
        </div>
    </div>
</form:form>