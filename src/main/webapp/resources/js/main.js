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

refresh();

function refresh() {
    findAllStorySets();
    findUser();
}

function findUser() {
    console.log('findUser:');
    $.ajax({
        type: 'GET',
        url: userURL + "/1",
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

    $('#storySetList option').remove();
    $.each(list, function (index, storySet) {
        $option = $('<option/>');
        $option.attr('value', storySet.id);
        $option.append(storySet.name);
        $('select.storySetList').append($option);
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
    $('#description').val(currentStory.description);
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
        }
    });
}

function renderListVotes(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    var $radios = $('input:radio[name=vote]');

    if (list.length == 0) {
        $radios.prop('checked', false);
        currentVoteId = null;
    }
    else {
        var vote = list[0];
        $radios.value(vote.vote);
        // $radios.filter('[value=' + vote.vote + ']').prop('checked', true);
        currentVoteId = vote.id;
    }
}

function voteToJSON() {
    return JSON.stringify({
        "id": currentVoteId == "" ? null : currentVoteId,
        "vote": $('input:radio[name=vote]:checked').val(),
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
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('createVote error: ' + textStatus);
        }
    });
}
