/**
 * Created by Icebear on 30.07.2017.
 */
var storySetURL = "http://localhost:8080/rest/storyset";

var votesURL = "http://localhost:8080/rest/votes";

var userURL = "http://localhost:8080/rest/users";


var currentStorySet;

var currentVoteId;

var currentUser;

var currentStory;

var isSetCreate;

var setElements;

var isStoryCreate;

var storyElements;

refresh();

function refresh() {
    setElements = [];
    storyElements = [];
    findAllStorySets();
    findUser();
}

function findUser() {
    console.log('findUser:');
    $.ajax({
        type: 'GET',
        url: userURL + "/active",
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findUser success: ' + data.name);
            currentUser = data;
        }
    });
}

function findAllStorySets() {
    console.log('findAllStorySets:');
    $.ajax({
        type: 'GET',
        url: storySetURL,
        dataType: "json", // data type of response
        success: renderListStorySet
    });
}

function renderListStorySet(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);


    for (var i = 0; i < setElements.length; i++) {
        setElements[i].detach();
    }
    setElements = [];
    for (var i = 0; i < storyElements.length; i++) {
        storyElements[i].detach();
    }
    storyElements = [];
    $('#description').val("");
    $.each(list, function (index, storySet) {
        $option = $('<option/>');
        $option.attr('value', storySet.id);
        $option.append(storySet.name);
        $('select.storySetList').append($option);
        setElements.push($option);
    });

    $('select.storySetList').change(function () {
        findStorySetById($('select.storySetList').val());
    }).change();
}

$(document).on('click', "li", function () {
    if ($(this).parent().get(0) == $('#storyList').get(0)) {
        findVotes($(this).data('identity'));
    }
})


function findStorySetById(id) {
    console.log('findStorySetById: ' + id);

    $.ajax({
        type: 'GET',
        url: storySetURL + '/' + id,
        dataType: "json",
        success: function (data) {
            console.log('findById success: ' + data.name);
            currentStorySet = data;
            renderDetailsStorySet(currentStorySet);
        }
    });

    $.ajax({
        type: 'GET',
        url: storySetURL + '/' + id + '/stories',
        dataType: "json",
        success: renderListStory
    });

}

function renderDetailsStorySet(storySet) {
    $('#storySetId').val(storySet.id);
}

function renderListStory(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $('#storyList li').remove();
    $('#votesList li').remove();
    $.each(list, function (index, story) {
        $li = $('<li/>');
        $li.attr('data-identity', story.id);
        $li.append("<a href='main#''>" + story.summary + "</a>");
        $('#storyList').append($li);
    });
}

function findVotes(id) {
    console.log('findVotes: ' + id);
    findStory(id);
    $.ajax({
        type: 'GET',
        url: votesURL + '/foruserandstory/' + id + '/' + currentUser.id,
        dataType: "json",
        success: renderListVotes
    });
}

function findStory(id) {
    console.log('findStory:');
    $.ajax({
        type: 'GET',
        url: storySetURL + "/" + currentStorySet.id + "/stories/" + id,
        dataType: "json", // data type of response
        success: function (data) {
            console.log('findStory success: ' + data.name);
            currentStory = data;
            $('#description').val(currentStory.description);
        }
    });
}

function renderListVotes(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);


    if (list.length == 0) {
        $('#currentVote').val("");
        currentVoteId = null;
    }
    else {
        var vote = list[0];
        $('#currentVote').val(vote.vote);
        currentVoteId = vote.id;
    }
}

function voteToJSON() {
    return JSON.stringify({
        "id": currentVoteId == "" ? null : currentVoteId,
        "vote": $('#vote').val(),
        "user": currentUser,
        "story": currentStory
    });
}


function updateVote() {
    console.log('updateVote');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: votesURL + '/foruserandstory/' + currentStory.id + '/' + currentUser.id + "/" + currentVoteId,
        dataType: "text",
        data: voteToJSON(),
        success: function () {
            alert('Vote updated successfully');
            $('#currentVote').val($('#vote').val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateVote error: ' + textStatus);
        }
    });
}

$(document).ready(function () {
    $("#target").click(function () {
        currentVoteId == null ? createVote() : updateVote();
    });
    $("#createSet").click(function () {
        isSetCreate = true;
        $('#editSet').modal();
    });
    $("#updateSet").click(function () {
        isSetCreate = false;
        $('#setName').val(currentStorySet.name);
        $('#editSet').modal();
    });
    $("#createStory").click(function () {
        isStoryCreate = true;
        $('#editStory').modal();
    });
    $("#updateStory").click(function () {
        isStoryCreate = false;
        $('#storyDescription').val(currentStory.description);
        $('#storySummary').val(currentStory.summary);
        $('#storyLink').val(currentStory.link);
        $('#editStory').modal();
    });
    $("#deleteSet").click(function () {
        deleteSet();
    });
    $("#deleteStory").click(function () {
        deleteStory();
    });
    $("#showResults").click(function () {
        showResults();
    });
    $("#editProfile").click(function () {
        $('#userName').val(currentUser.name);
        $('#userEmail').val(currentUser.email);
        $('#userPassword').val(currentUser.password);
        $('#userProfile').modal();
    });
    $("#setAdmin").click(function () {
        setAdmins();
    });
});

function showResults() {
    $('#resultsTable tbody tr').remove()
    $.ajax({
        type: 'GET',
        url: votesURL + '/forset/' + currentStorySet.id,
        dataType: "json",
        success: function (data) {
            var x;
            for (x in data) {
                var markup = "<tr><td>" + x + "</td><td>" + data[x] + "</td></tr>";
                $("table tbody").append(markup);
            }
        }
    });

    $('#voteResults').modal();
}

function setAdmins() {
    $('#adminsTable tbody tr').remove()
    $.ajax({
        type: 'GET',
        url: userURL,
        dataType: "json",
        success: function (data) {
            var $tbody = $('#adminsTable').append('<tbody />').children('tbody');
            for (x in data) {
                $tbody.append('<tr class="item"/>').children('tr:last')
                    .append("<td id='columnUserId' style='display: none'>" + data[x].id + "</td>")
                    .append("<td >" + data[x].name + "</td>")
                    .append("<td><input class='record' type='checkbox' name='record' " + (data[x].roles.includes("ROLE_ADMIN") ? "checked" : "") + "></td>")
                    .append("<td style='display: none'><input class='oldRecord' type='checkbox' name='oldRecord' " + (data[x].roles.includes("ROLE_ADMIN") ? "checked" : "") + "></td>");
            }
        },
    });
    $('#setAdmins').modal();
}

function saveAdmins() {
    var adminsArray = [];
    var usersArray = [];
    $('.users-admins tr').each(function (i, row) {
        if (i > 0) {
            var $row = $(row);
            var id = $($row.children().get(0)).html();
            if (($($row.children().get(2)).find("input[name='record']").is(":checked")) &&
                !($($row.children().get(3)).find("input[name='oldRecord']").is(":checked"))) {
                adminsArray.push(parseInt(id));
            }
            else if (!($($row.children().get(2)).find("input[name='record']").is(":checked")) &&
                ($($row.children().get(3)).find("input[name='oldRecord']").is(":checked"))) {
                usersArray.push(parseInt(id));
            }
        }
    });
    if (adminsArray.length > 0) {
        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: userURL + "/setadmins",
            dataType: "text",
            data: JSON.stringify(adminsArray),
            success: function () {
                alert('User updated successfully');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('updateUser error: ' + textStatus);
            }
        });
    }
    if (usersArray.length > 0) {
        $.ajax({
            type: 'PUT',
            contentType: 'application/json',
            url: userURL + "/setusers",
            dataType: "text",
            data: JSON.stringify(usersArray),
            success: function () {
                alert('User updated successfully');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('updateUser error: ' + textStatus);
            }
        });
    }

    $('#setAdmins').modal('toggle');
}

function createVote() {
    console.log('createVote');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: votesURL + '/foruserandstory/' + currentStory.id + '/' + currentUser.id,
        dataType: "text",
        data: voteToJSON(),
        success: function () {
            alert('Vote created successfully');
            $('#currentVote').val($('#vote').val());
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createVote error: ' + textStatus);
        }
    });
}

function saveSet() {
    isSetCreate ? createSet() : updateSet();
}

function createSet() {
    console.log('createSet');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: storySetURL,
        dataType: "text",
        data: setToJSON(null),
        success: function () {
            alert('Set created successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createSet error: ' + textStatus);
        }
    });
}

function updateSet() {
    console.log('updateSet');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: storySetURL + "/" + currentStorySet.id,
        dataType: "text",
        data: setToJSON(currentStorySet.id),
        success: function () {
            alert('Set updated successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateSet error: ' + textStatus);
        }
    });
}

function saveStory() {
    isStoryCreate ? createStory() : updateStory();
}

function createStory() {
    console.log('createStory');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: storySetURL + '/' + currentStorySet.id + '/stories',
        dataType: "text",
        data: storyToJSON(null),
        success: function () {
            alert('Story created successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createStory error: ' + textStatus);
        }
    });
}

function updateStory() {
    console.log('updateStory');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: storySetURL + '/' + currentStorySet.id + '/stories' + "/" + currentStory.id,
        dataType: "text",
        data: storyToJSON(currentStory.id),
        success: function () {
            alert('Story updated successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateStory error: ' + textStatus);
        }
    });
}

function deleteSet() {
    console.log('deleteSet');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: storySetURL + "/" + currentStorySet.id,
        dataType: "text",
        data: setToJSON(currentStorySet.id),
        success: function () {
            alert('Set deleted successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteSet error: ' + textStatus);
        }
    });
}

function deleteStory() {
    console.log('deleteStory');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: storySetURL + '/' + currentStorySet.id + '/stories' + "/" + currentStory.id,
        dataType: "text",
        data: storyToJSON(currentStory.id),
        success: function () {
            alert('Story deleted successfully');
            findAllStorySets();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('deleteStory error: ' + textStatus);
        }
    });
}

function setToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "name": $('#setName').val()
    });
}

function storyToJSON(id) {
    return JSON.stringify({
        "id": id == null ? null : id,
        "description": $('#storyDescription').val(),
        "summary": $('#storySummary').val(),
        "link": $('#storyLink').val()
    });
}

function saveUser() {
    console.log('updateUser');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: userURL + "/" + currentUser.id,
        dataType: "text",
        data: userToJSON(),
        success: function () {
            alert('User updated successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateUser error: ' + textStatus);
        }
    });
}

function userToJSON() {
    return JSON.stringify({
        "id": currentUser.id,
        "name": $('#userName').val(),
        "email": $('#userEmail').val(),
        "password": $('#userPassword').val()
    });
}