<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:genericuserpage>
    <jsp:body>
        <div class="container mt-4">
            <jsp:include page="../includes/user/detailsForm.jsp" />
        </div>

        <div class="container">
            <jsp:include page="../includes/user/wagersContent.jsp" />
        </div>
    </jsp:body>
</t:genericuserpage>