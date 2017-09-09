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
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/main.js"></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">

    <%--<div class="container">--%>
    <%--</div>--%>
    <div class="mainArea">
        <div class="leftTopArea">
            <select class="storySetList"></select>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="createSet" value="Create story set"/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="updateSet" value="Edit story set"/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="deleteSet" value="Delete story set"/>
            </sec:authorize>
            <br>
            <br>
            <ul id="storyList"></ul>
            <br>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="createStory" value="Create story"/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="updateStory" value="Edit story"/>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <input type="button" id="deleteStory" value="Delete story"/>
            </sec:authorize>
        </div>
        <div class="leftBottomArea">
            Current vote: <textarea id="currentVote" class="voteTextArea" readonly></textarea>
            <br>
            <br>
            <select class="vote" name="vote" id="vote">
                <option value="null"></option>
                <option value="0"/>
                0</option>
                <option value="1/2"/>
                1/2</option>
                <option value="1"/>
                1</option>
                <option value="2"/>
                2</option>
                <option value="3"/>
                3</option>
                <option value="5"/>
                5</option>
                <option value="8"/>
                8</option>
                <option value="13"/>
                13</option>
                <option value="20"/>
                20</option>
                <option value="40"/>
                40</option>
                <option value="100"/>
                100</option>
                <option value="?"/>
                ?</option>
                <option value="Invalid"/>
                Invalid</option>
                <option value="Won't fix"/>
                Won't fix</option>
                <option value="Duplicate"/>
                Duplicate</option>
            </select>
            <input type="button" id="target" value="Vote"/>
            <input type="button" id="showResults" value="Show results"/>
        </div>
        <form id="mainForm">

            <div class="rightTopArea">
                <textarea id="description" name="description" readonly></textarea>


            </div>

        </form>
    </div>
    <div class="rightBottomArea"></div>

</div>

<div class="modal fade" id="editSet">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleSet"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="editSetForm">
                    <input type="hidden" id="setId" name="setId">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="setName" name="setName"
                                   placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveSet()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editStory">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleStory"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="editSetForm">
                    <input type="hidden" id="storyId" name="storyId">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Summary</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="storySummary" name="storySummary"
                                   placeholder="Summary">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="storyDescription" name="storyDescription"
                                   placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Link</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="storyLink" name="storyLink"
                                   placeholder="Link">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveStory()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="voteResults">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleResults"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="resultsForm">
                    <table id="resultsTable">
                        <thead>
                        <tr>
                            <th>Vote</th>
                            <th>Count</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="userProfile">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleProfile"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="editSetForm">
                    <input type="hidden" id="userId" name="userId">
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="userName" name="userName"
                                   placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="userEmail" name="userEmail"
                                   placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="userPassword" name="userPassword"
                                   placeholder="Password">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveUser()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="setAdmins">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title" id="modalTitleAdmins"></h2>
            </div>
            <div class="modal-body">
                <form:form class="form-horizontal" id="resultsForm">
                    <table id="adminsTable" class="users-admins">
                        <thead>
                        <tr>
                            <th>User</th>
                            <th>Admin</th>
                        </tr>
                        </thead>

                    </table>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveAdmins()">
                                <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
