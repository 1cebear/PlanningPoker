<%@ page session="false" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <a href="main" class="navbar-brand">Planning poker</a>

        <div class="collapse navbar-collapse">
            <form class="navbar-form navbar-right">
                <sec:authorize access="isAuthenticated()">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <input type="button" class="btn btn-info" id="setAdmin" value="Set admin"/>
                    </sec:authorize>
                    <input type="button" class="btn btn-info" id="editProfile" value="Edit profile"/>
                    <a class="btn btn-primary" href="logout">
                        <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                    </a>
                </sec:authorize>
            </form>
        </div>
    </div>
</div>