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

refresh();

function refresh() {
    setElements = [];
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
});

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