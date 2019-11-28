<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:body>
        <div class="container">
            <h3><a href="login" class="text-primary">Login</a> or <a href="register" class="text-primary">Register</a>
                to start!</h3>
            <jsp:include page="loginForm.jsp" />
        </div>
    </jsp:body>
</t:genericpage>