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
<script type="text/javascript" src="resources/js/main.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <select class="storySetList"></select>
    <%--<div class="container">--%>
    <%--</div>--%>
</div>
<div class="mainArea">
<div class="leftArea">

    <br>
    <br>
    <br>
    <ul id="storyList"></ul>
    <br>
    <br>
    <br>
    <input type="radio" name="vote" value="0"/>0<br>
    <input type="radio" name="vote" value="1/2"/>1/2<br>
    <input type="radio" name="vote" value="1"/>1<br>
    <input type="radio" name="vote" value="2"/>2<br>
    <input type="radio" name="vote" value="3"/>3<br>
    <input type="radio" name="vote" value="5"/>5<br>
    <input type="radio" name="vote" value="8"/>8<br>
    <input type="radio" name="vote" value="13"/>13<br>
    <input type="radio" name="vote" value="20"/>20<br>
    <input type="radio" name="vote" value="40"/>40<br>
    <input type="radio" name="vote" value="100"/>100<br>
    <input type="radio" name="vote" value="?"/>?<br>
    <input type="radio" name="vote" value="Invalid"/>Invalid<br>
    <input type="radio" name="vote" value="Won't fix"/>Won't fix<br>
    <input type="radio" name="vote" value="Duplicate"/>Duplicate<br>
    <br>
    <input type="button" id="target" value="Submit"/>
</div>

<form id="mainForm">

    <div class="rightArea">
        <textarea id="description" name="description"></textarea>


    </div>

</form>
</div>

</body>
</html>
