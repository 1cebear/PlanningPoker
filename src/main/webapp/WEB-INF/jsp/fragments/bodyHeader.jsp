<%@ page session="false" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="main" class="navbar-brand">Planning poker</a>

        <div class="collapse navbar-collapse">
            <form:form class="navbar-form navbar-right" action="logout" method="post">
                <sec:authorize access="isAuthenticated()">
                    <button class="btn btn-primary" type="submit" title = "Logout">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                    </button>
                </sec:authorize>
            </form:form>
        </div>
    </div>
</div>