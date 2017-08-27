<%--
  Created by IntelliJ IDEA.
  User: Icebear
  Date: 30.07.2017
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <p/>

    </div>
</div>
<div class="mainArea">
<div class="leftArea">
    <select class="storySetList"></select>
    <br>
    <br>
    <br>
    <ul id="storyList"></ul>
    <br>
    <br>
    <br>
    <input type="radio" name="vote" value="0"/>0
    <input type="radio" name="vote" value="1/2"/>1/2
    <input type="radio" name="vote" value="1"/>1
    <input type="radio" name="vote" value="2"/>2
    <input type="radio" name="vote" value="3"/>3
    <input type="radio" name="vote" value="5"/>5
    <input type="radio" name="vote" value="8"/>8
    <input type="radio" name="vote" value="13"/>13
    <input type="radio" name="vote" value="20"/>20
    <input type="radio" name="vote" value="40"/>40
    <input type="radio" name="vote" value="100"/>100
    <input type="radio" name="vote" value="?"/>?
    <input type="radio" name="vote" value="Invalid"/>Invalid
    <input type="radio" name="vote" value="Won't fix"/>Won't fix
    <input type="radio" name="vote" value="Duplicate"/>Duplicate
</div>

<form id="mainForm">

    <div class="rightArea">
test

        <label>Id:</label>

        <input type="button" id="target" value="Submit"/>
    </div>

</form>
</div>

</body>
</html>
