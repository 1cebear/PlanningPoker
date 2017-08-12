/**
 * Created by Icebear on 30.07.2017.
 */
var rootURL = "http://localhost:8080/rest/users";

alert("test");

findAll();

function findAll() {
    $.ajax({
        type: 'GET',
        url: rootURL,
        dataType: "json", // data type of response
        success: renderList
    });
}

function renderList(data) {
    // JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);

    $('#userList li').remove();
    $.each(list, function(index, user) {
        $('#userList').append('<li><a href="#" data-identity="' + user.id + '">'+user.name+'</a></li>');
    });
}

