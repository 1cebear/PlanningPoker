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

    <%--<div class="container">--%>
    <%--</div>--%>
</div>
<div class="mainArea">
    <div class="leftTopArea">
        <select class="storySetList"></select>
        <br>
        <br>
        <ul id="storyList"></ul>
    </div>
    <div class="leftBottomArea">
        <select class = "vote" name="vote">
            <option value="null"></option>
            <option value="0"/>0</option>
            <option value="1/2"/>1/2</option>
            <option value="1"/>1</option>
            <option value="2"/>2</option>
            <option value="3"/>3</option>
            <option value="5"/>5</option>
            <option value="8"/>8</option>
            <option value="13"/>13</option>
            <option value="20"/>20</option>
            <option value="40"/>40</option>
            <option value="100"/>100</option>
            <option value="?"/>?</option>
            <option value="Invalid"/>Invalid</option>
            <option value="Won't fix"/>Won't fix</option>
            <option value="Duplicate"/>Duplicate</option>
        </select>
        <br>
        <input type="button" id="target" value="Submit"/>
    </div>
    <form id="mainForm">

        <div class="rightTopArea">
            <textarea id="description" name="description"></textarea>


        </div>

    </form>
</div>
<div class="rightBottomArea"></div>
</body>
</html>
