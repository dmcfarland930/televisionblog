/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var tagData = JSON.stringify({
            name: $('#name-input').val()

        });
        $('#add-tag-validation-errors').empty();
        $.ajax({
            url: contextRoot + "/admin/tag/create/",
            type: "POST",
            data: tagData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                alert("Success!");
                $("#name-input").val("");
                $("#createTagModal").modal("hide");
                var tableRow = buildTagRow(data);

                $('#tag-table').append($(tableRow));
            },
            error: function (data, status) {
                alert("Error!");
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, validationError) {
                    $('#add-tag-validation-errors').append(validationError.message).append("<br/>");
                });
            }
        });

        //alert("alert after ajax");
    });
    $('#editTagModal').on('show.bs.modal', function (e) {
        var link = $(e.relatedTarget);

        var tagId = link.data('tag-id');

        $.ajax({
            url: contextRoot + "/admin/tag/" + tagId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#edit-tag-name').val(data.name);
                $('#edit-tag-id').val(data.id);
            },
            error: function (data, status) {

            }

        });
    });

    $('#edit-tag-button').on('click', function (e) {

        e.preventDefault();

        var tagData = JSON.stringify({
            name: $('#edit-tag-name').val(),
            id: $('#edit-tag-id').val()
        });

        $.ajax({
            url: contextRoot + "/admin/tag/update/",
            type: "PUT",
            data: tagData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editTagModal').modal('hide');
                var tableRow = buildTagRow(data);

                $('#tag-row-' + data.id).replaceWith($(tableRow));
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, validationError) {
                    $('#edit-tag-validation-errors').append(validationError.message).append("<br/>");
                });
            }
        });

        //alert("alert after ajax");

    });
    $(document).on('click', '.delete-tag-link', function (e) {
        e.preventDefault();

        var tagId = $(e.target).data('tag-id');

        $.ajax({
            type: "DELETE",
            url: contextRoot + "admin/tag/delete/" + tagId,
            success: function (data, status) {
                $('#tag-row-' + tagId).remove();
            },
            error: function (data, status) {

            }
        });
    });
    function buildTagRow(data) {
        return "<tr id='tag-row-" + data.id + "'> \n\
                    <td>" + data.name + "</td> \n\
                    <td><a href='' class='glyphicon glyphicon-edit' style='color:green;' data-toggle='modal' data-tag-id='" + data.id + "' data-target='#editTagModal'></a></td> \n\
                <td><a href='' data-tag-id='" + data.id + "' class='glyphicon glyphicon-remove delete-tag-link' style='color:red;'></a></td> \n\
                </tr>";
    }
});

