<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page import="com.example.sportsbetting.dto.SportEventDto" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="com.example.sportsbetting.dto.BetDto" %>
<%@ page import="com.example.sportsbetting.dto.OutcomeOddDto" %>
<%@ page import="com.example.sportsbetting.dto.OutcomeDto" %>
<%
    Iterable<SportEventDto> events = (Iterable<SportEventDto>) request.getAttribute("events");
    DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
%>

<div class="rounded-lg border border-primary mb-2">
    <div class="text-light bg-primary p-2">
        <h6><spring:message code="Label.Events"/></h6>
    </div>

    <table class="table p-3">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col"><spring:message code="Label.EventTitle"/></th>
            <th scope="col"><spring:message code="Label.EventType"/></th>
            <th scope="col"><spring:message code="Label.StartDate"/></th>
            <th scope="col"><spring:message code="Label.EndDate"/></th>
        </tr>
        </thead>
        <tbody>
        <%
            int id = 0;
            for (SportEventDto event : events) {
        %>

        <tr>
            <th scope="row"><%=++id%>
            </th>
            <td><a class="btn btn-primary btn-sm" data-toggle="collapse" href="#accordion-<%=id%>" role="button"
                   aria-expanded="false" aria-controls="accordion-<%=id%>"><%=event.getTitle()%>
            </a></td>
            <td><%=event.isTennisEvent() ? "Tennis" : "Football"%>
            </td>
            <td><%=formatter.format(event.getStartDate())%>
            </td>
            <td><%=formatter.format(event.getEndDate())%>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<%
    id = 0;
    for (SportEventDto event : events) {
%>
<div id="accordion-<%=++id%>" class="rounded-lg border border-primary collapse mt-2 mb-2">
    <div class="text-light bg-primary p-2">
        <h6><%=event.getTitle()%>
        </h6>
    </div>
    <div class="container">
        <% for (BetDto bet : event.getBets()) { %>
        <div class="container">
            <div class="p-2">
                <h6><spring:message code="Label.BetType"/>: <%=bet.getDescription()%>
                </h6>
            </div>
            <% for (OutcomeDto outcome : bet.getOutcomes()) { %>
            <div class="container">
                <div class="p-2">
                    <h6><spring:message code="Label.OutcomeValue"/>: <%=outcome.getDescription()%>
                    </h6>

                    <% for (OutcomeOddDto odd : outcome.getOutcomeOdds()) { %>
                    <div class="container">
                        <div class="p-2">
                            <spring:message code="Label.OutcomeOdd"/>: 1:<%=odd.getValue()%><br>
                            <%=formatter.format(odd.getValidFrom())%> - <%=formatter.format(odd.getValidUntil())%>
                        </div>
                    </div>
                    <% } %>
                </div>
            </div>
            <% } %>
        </div>
        <% } %>
    </div>
</div>
<%
    }
%>
