<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Login template" pageEncoding="UTF-8" %>
<form:form action="login" cssClass="w-25 rounded-lg border border-primary" method="post" modelAttribute="loginRequest">
    <div class="text-light bg-primary p-2">
        <h4>Login</h4>
    </div>
    <div class="container p-3">
        <d:if test="${SPRING_SECURITY_LAST_EXCEPTION.message.length() > 0}">
            <div class="input-group alert alert-danger">
                    ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </d:if>
        <div class="input-group form-group">
            <form:input type="email" path="username" cssClass="form-control" placeholder="Email"/>
        </div>
        <div class="input-group form-group">
            <form:input type="password" path="password" cssClass="form-control" placeholder="Password"/>
        </div>
        <div class="form-group">
            <form:button type="submit" class="btn btn-primary">Login</form:button>
        </div>
    </div>
</form:form>