var url = "/form/";
var editModalTarget = url;
var tableTarget = "/getBooks";

function showEditModal(index) {
    var editUrl = "/api/book/"+ index;
    loadEntity(editUrl);
}

// Ajax request for Something to populate the modal form.
function loadEntity(url) {
    $.getJSON(url, {}, function (data) {
        populateModal(data);
    });
}

// Assign the data values to the modal form.
function populateModal(data) {
    $('#update-id').val(data.id);
    $('#update-isbn').val(data.isbn);
    $('#update-title').val(data.title);
    $('#update-author').val(data.author);
    $('#update-review').val(data.review);
    $('#update-pages').val(data.pages);
    $('#update-rating').val(data.rating);
}
function clearModal() {
    $('#update-id').val('');
    $('#update-isbn').val('');
    $('#update-title').val('');
    $('#update-author').val('');
    $('#update-review').val('');
    $('#update-pages').val('');
    $('#update-rating').val('');
}
function closeModal(name) {
    $(name).modal('toggle');
}

function clearAndCloseModal(name) {
    clearModal();
    closeModal(name);
}

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        if (n['name'] !== "_method") {
            indexed_array[n['name']] = n['value'];
        }
    });
    return indexed_array;
}
// POST the edits to Something to the server.
function postEdit() {
    var z = getFormData($('#edit-form'));
    $.ajax({
        url: '/update',
        type: 'PUT',
        contentType: "application/json",
        data: JSON.stringify(z),
        success: function(data) {
            //play with data
            updateTable(data)
        },
        error: function(data) {
        }
    });
    clearAndCloseModal('#umodal');
}
function deleteEntity(entity) {
    var input = $('#delete-id');
    var url = '/delete/' + input.val();
    $.get(url, input.val(), function (data) {
        updateTable(data);
    });
    closeModal('#dmodal');
    input.val('');
}
function showDeleteModal(index) {
    $('#delete-id').val(index);
}
function updateTable(data) {
    $.ajax({
        dataType: "json",
        url: "/api/book",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'GET',
        success: function (response) {
            $('#table-body').empty();
            $.each(response, function (i, e) {
                var end = e.id + ");'";
                var edit = "'showEditModal(" + end;
                var del = "'showDeleteModal(" + end;
                var row = $('<tr>').append(
                    $('<td>').text(e.isbn),
                    $('<td>').text(e.title),
                    $('<td>').text(e.author),
                    $('<td>').text(e.review),
                    $('<td>').text(e.pages),
                    $('<td>').text(e.rating),
                    $('<td>').append(
                        "<a data-toggle='modal' data-target='#umodal' class='btn btn-outline-primary' onclick=" +
                        edit + ">Edit</a>"
                    ),
                    $('<td>').append(
                        "<a class='btn btn-outline-danger' data-toggle='modal' data-target='#dmodal' onclick=" + del + ">Delete</a>"
                    )
                );
                $('#book-table').append(row);
            });
        }
    });
}