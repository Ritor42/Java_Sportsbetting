<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ tag import="com.example.sportsbetting.dto.BetDto" %>
<%@ tag import="com.example.sportsbetting.dto.OutcomeDto" %>
<%@ tag import="com.example.sportsbetting.dto.OutcomeOddDto" %>
<%@ tag import="com.example.sportsbetting.dto.SportEventDto" %>
<%@ tag import="java.text.DateFormat" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<%
    Iterable<OutcomeOddDto> odds = (Iterable<OutcomeOddDto>) request.getAttribute("odds");
    DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
%>
<form action="<c:url value='/user/wager' />" method="post" class="rounded-lg border border-primary">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.Wager"/></h6>
    </div>
    <div class="container p-3">
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="event-label"><spring:message code="Label.EventTitle"/></span>
            </div>
            <select class="custom-select custom-select" name="oddId" aria-describedby="event-label">
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
            </select>
        </div>
        <div class="input-group form-group">
            <div class="input-group-prepend">
                <span class="input-group-text" id="amount-label"><spring:message code="Label.WagerAmount"/></span>
            </div>
            <input type="number" name="amount" class="form-control" placeholder="Amount" aria-describedby="amount-label"
                   required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary"><spring:message code="Label.Add"/></button>
        </div>
    </div>
</form>