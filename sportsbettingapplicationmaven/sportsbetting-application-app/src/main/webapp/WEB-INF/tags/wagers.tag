<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag import="com.example.sportsbetting.domain.*" %>
<%@ tag import="com.example.sportsbetting.service.SportsBettingService" %>
<%@ tag import="java.text.DateFormat" %>
<%@ tag import="java.util.Date" %>
<%@ tag import="java.util.List" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<%
    Integer playerId = (Integer) session.getAttribute("Id");
    Player player = SportsBettingService.GetInstance().findPlayer(playerId);

    List<Wager> wagers = SportsBettingService.GetInstance().findAllWagers();

    DateFormat formatter = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);
%>

<div class="rounded-lg border border-primary">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.Wagers"/></h6>
    </div>

    <table class="table p-3">
        <thead>
        <tr>
            <th scope="col"></th>
            <th scope="col">#</th>
            <th scope="col"><spring:message code="Label.EventTitle"/></th>
            <th scope="col"><spring:message code="Label.EventType"/></th>
            <th scope="col"><spring:message code="Label.BetType"/></th>
            <th scope="col"><spring:message code="Label.OutcomeValue"/></th>
            <th scope="col"><spring:message code="Label.OutcomeOdd"/></th>
            <th scope="col"><spring:message code="Label.WagerAmount"/></th>
            <th scope="col"><spring:message code="Label.Winner"/></th>
            <th scope="col"><spring:message code="Label.Processed"/></th>
        </tr>
        </thead>
        <tbody>
        <%
            Integer id = 0;
            for (Wager wager : wagers) {
                String win = wager.isProcessed() ? (wager.isWin() ? "Yes" : "No") : "-";
                String processed = wager.isProcessed() ? "Yes" : "No";

                OutcomeOdd odd = wager.getOutcomeOdd();
                Outcome outcome = odd.getOutcome();
                Bet bet = outcome.getBet();
                SportEvent event = bet.getEvent();
        %>
        <tr>
            <td>
                <% if (odd.getValidFrom().compareTo(new Date()) != -1 && player.getCurrency() == wager.getCurrency() && !wager.isProcessed()) { %>
                <a href="/user/wager/delete?wagerId=<%=wager.getId()%>">
                    <button type="button" class="btn btn-primary btn-sm"><spring:message code="Label.Remove"/></button>
                </a>
                <% } %>
            </td>
            <th scope="row"><%=++id%>
            </th>
            <td><%=event.getTitle()%> <%=formatter.format(event.getStartDate())%>
            </td>
            <td><%=event.getType()%>
            </td>
            <td><%=bet.getType()%>
            </td>
            <td><%=outcome.getDescription()%>
            </td>
            <td>1:<%=odd.getValue()%>
            </td>
            <td><%=wager.getAmount()%> <%=wager.getCurrency()%>
            </td>
            <td>
                <% if (!wager.isProcessed()) { %>
                -
                <% } else if (wager.isWin()) {%>
                <spring:message code="Label.Yes"/>
                <% } else { %>
                <spring:message code="Label.No"/>
                <% } %>
            </td>
            <td>
                <% if (wager.isProcessed()) { %>
                <spring:message code="Label.Yes"/>
                <% } else { %>
                <spring:message code="Label.No"/>
                <% } %>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>