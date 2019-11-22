<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>SportsBetting</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand mb-0 h1" href="#">SportsBetting</a>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/user/dashboard"><spring:message code="Nav.Home"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/user/wager"><spring:message code="Nav.AddWager"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/user/events"><spring:message code="Nav.Events"/></a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/user/calculate"><spring:message code="Nav.Calculate"/></a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link active dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <spring:message code="Nav.Language"/>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?language=en">English</a>
                    <a class="dropdown-item" href="?language=hu">Magyar</a>
                </div>
            </li>
        </ul>
        <a href="/user/logout">
            <button type="button" class="btn btn-dark btn-outline-light my-2 my-sm-0"><spring:message
                    code="Nav.Logout"/></button>
        </a>
    </div>
</nav>

<div id="body">
    <jsp:doBody/>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>
</html>