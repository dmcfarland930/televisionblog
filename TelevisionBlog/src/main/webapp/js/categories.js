/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $('#create-submit').on('click', function (e) {

        e.preventDefault();

        var categoryData = JSON.stringify({
            name: $('#name-input').val()

        });
        $('#add-category-validation-errors').empty();
        $.ajax({
            url: contextRoot + "/admin/category/create/",
            type: "POST",
            data: categoryData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                $("#name-input").val("");
                $("#createCategoryModal").modal("hide");
                var tableRow = buildCategoryRow(data);

                $('#category-table').append($(tableRow));
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, validationError) {
                    $('#add-category-validation-errors').append(validationError.message).append("<br/>");
                });
            }
        });

        //alert("alert after ajax");
    });
    $('#editCategoryModal').on('show.bs.modal', function (e) {
        var link = $(e.relatedTarget);

        var categoryId = link.data('category-id');

        $.ajax({
            url: contextRoot + "/admin/category/" + categoryId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {

                $('#edit-category-name').val(data.name);
                $('#edit-category-id').val(data.id);
            },
            error: function (data, status) {

            }

        });
    });

    $('#edit-category-button').on('click', function (e) {

        e.preventDefault();

        var categoryData = JSON.stringify({
            name: $('#edit-category-name').val(),
            id: $('#edit-category-id').val()
        });

        $.ajax({
            url: contextRoot + "/admin/category/update/",
            type: "PUT",
            data: categoryData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#editCategoryModal').modal('hide');
                var tableRow = buildCategoryRow(data);

                $('#category-row-' + data.id).replaceWith($(tableRow));
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, validationError) {
                    $('#edit-category-validation-errors').append(validationError.message).append("<br/>");
                });
            }
        });

        //alert("alert after ajax");

    });

    $(document).on('click', '.delete-category-link', function (e) {
        e.preventDefault();

        var categoryId = $(e.target).data('category-id');
        var categoryDefault = $(e.target).data('category-default');

        $('#category-delete-button').val(categoryId);
        console.log(categoryId);
        console.log($('#category-delete-button').val());
        $.ajax({
            type: "DELETE",
            url: contextRoot + "/category/delete/" + categoryId,
            success: function (data, status) {
                $('#category-row-' + categoryId).remove();
            },
            error: function (data, status) {

                if (categoryDefault === true) {
                    $('#defaultCategoryDelete').modal('show');
                } else {
                    $('#deleteCategoryModal').modal('show');
                }


            }
        });
    });

    $(document).on('click', '#category-delete-button', function (e) {
        e.preventDefault();

        var categoryId = $("#category-delete-button").val();

        $.ajax({
            type: "DELETE",
            url: contextRoot + "/admin/category/reassign/" + categoryId,
            success: function (data, status) {
                $('#category-row-' + categoryId).remove();
            },
            error: function (data, status) {

                console.log("error deleting category");

            }
        });
    });


    function buildCategoryRow(data) {
        return "<tr id='category-row-" + data.id + "'> \n\
                    <td>" + data.name + "</td> \n\
                    <td><a href='' class='glyphicon glyphicon-edit' style='color:green;' data-toggle='modal' data-category-id='" + data.id + "' data-target='#editCategoryModal'></a></td> \n\
                <td><a href='' data-category-id='" + data.id + "' class='glyphicon glyphicon-remove delete-category-link' style='color:red;'></a></td> \n\
                </tr>";
    }
});


