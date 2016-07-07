/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    //Change active tab
    $("#error-delete-modal").on("hide.bs.modal", function () {
       location.reload();

    });

    //Disables Admin checkboxes.
    $(document).ready(function () {
        $("input[name='Administrator']").prop("disabled", true);
//        $("input[name='ROLE_ADMIN']").prop("checked", true);
        $("input[name='Custom']").parent().parent().hide();
    });

    //Hides extra Custom Options
//    $(document).ready(function () {
//
//        var userOptions = $(".user");
//        $.each(userOptions, function (key, value) {
//            if ($(this).val() === "Custom") {
//                $(userOptions).closest("tr").find(".user-checkbox").prop("disabled", false);
//            }
//        });
//
//    });



    //Disables all checkboxes that are not custom
    $(document).ready(function () {
        var currentRole = $("select[name='user-role']");

        $.each(currentRole, function (key, value) {
            if ($(this).find("option:selected").attr("name") === "Custom") {
                $(this).closest("tr").find(".checkbox").prop("disabled", false);
            } else {
                $(this).closest("tr").find(".checkbox").prop("disabled", true);
            }
        });

    });


    //When changing to custom Role, checkbboxes are enabled.
    $(".user-role-select").on('change', function (e) {

        var userRole = $(this).find("option:selected").attr("name");
        if (userRole === "Custom") {
            $(this).closest("tr").find(".user-checkbox").prop("disabled", false);
            $(this).closest("tr").find(".user-checkbox").prop("checked", false);
        } else {
            $(this).closest("tr").find(".user-checkbox").prop("disabled", true);
        }

        var roleId = $(this).val();
        var userId = $(this).data("user-id");

        $.ajax({
            url: contextRoot + "/admin/uac/change-role/" + roleId + "/" + userId,
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                location.reload();
            },
            error: function (data, status) {

            }
        });

    });

    //When checking a box, save changes
    $(".role-checkbox").on("change", function (e) {

        if ($(this).is(":checked")) {
            var roleId = $(e.target).data("role-id");
            var userRightId = $(this).val();

            $.ajax({
                url: contextRoot + "/admin/uac/add/" + roleId + "/" + userRightId,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                },
                success: function (data, status) {

                },
                error: function (data, status) {

                }
            });

        } else {
            var roleId = $(e.target).data("role-id");
            var userRightId = $(this).val();

            $.ajax({
                url: contextRoot + "/admin/uac/remove/" + roleId + "/" + userRightId,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-type", "application/json");
                },
                success: function (data, status) {

                },
                error: function (data, status) {

                }
            });
        }

    });

    //When checking a box, save changes **CUSTOM**
    $(".user-checkbox").on("change", function (e) {

        if ($(this).is(":checked")) {

            var userId = $(this).data("user-id");
            var userRightId = $(this).val();
            var roleId = $("#user-role-" + userId).val();

            $.ajax({
                url: contextRoot + "/admin/uac/add/" + roleId + "/" + userRightId + "/?userId=" + userId,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                },
                success: function (data, status) {

                },
                error: function (data, status) {

                }
            });

        } else {
            var userId = $(this).data("user-id");
            var userRightId = $(this).val();
            var roleId = $("#user-role-" + userId).val();

            $.ajax({
                url: contextRoot + "/admin/uac/remove/" + roleId + "/" + userRightId,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                },
                success: function (data, status) {

                },
                error: function (data, status) {

                }
            });
        }

    });

    //Create Role
    $("#create-role-submit").on("click", function (e) {

//        e.preventDefault();
        $("#add-role-validation-errors").empty();

        var roleData = JSON.stringify({
            name: $("#name-input").val()
        });

        $.ajax({
            url: contextRoot + "/admin/role/create/",
            type: "POST",
            data: roleData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                location.reload();
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, error) {
                    $("#add-role-validation-errors").append(error.message + "<br />");
                });
            }
        });
    });

    //Edit Show
    $("#edit-role-modal").on("show.bs.modal", function (e) {

        var link = e.relatedTarget;
        var roleId = $(link).data("role-id");

        $.ajax({
            url: contextRoot + "/admin/role/get/" + roleId,
            type: "GET",
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $("#edit-role-id").val(data.id);
                $("#edit-role-name").val(data.name);
            },
            error: function (data, status) {

            }
        });

    });

    //Edit Submit
    $("#edit-role-button").on("click", function (e) {

        var roleData = JSON.stringify({
            id: $("#edit-role-id").val(),
            name: $("#edit-role-name").val(),
        });

        $.ajax({
            url: contextRoot + "/admin/role/update/",
            type: "PUT",
            data: roleData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                location.reload();
            },
            error: function (data, status) {

            }
        });
    });

    //Delete Role
    $(document).on("click", ".delete-role-link", function (e) {

        e.preventDefault();

        var roleId = $(e.target).data("role-id");

        $.ajax({
            url: contextRoot + "/admin/role/delete/" + roleId,
            type: "DELETE",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $("#role-row-" + roleId).remove();
            },
            error: function (data, status) {

                $("#error-delete-modal").modal("show");
                showUsers(roleId);
            }
        });

    });

    //Removes Users that aren't related to the Role that are being deleted
    function showUsers(id) {
        var currentRole = $("select[name='edit-user-role']");
        $.each(currentRole, function (key, value) {
            var userRole = $(this).val();
            if (userRole != id) {
                $(this).closest("tr").remove();
            }
        });

    }

    //Remove Users from table  by changing roles
    $(document).ready(function () {
        var previousRoleId;

        $(".remove-role-select").on("focus", function () {
            previousRoleId = $(this).val();

        }).change(function () {


            var userId = $(this).data("user-id");
            var roleId = $(this).val();

            $.ajax({
                url: contextRoot + "/admin/uac/change-role/" + roleId + "/" + userId,
                type: "POST",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-type", "application/json");
                },
                success: function (data, status) {
                    $("#edit-user-row-" + userId).remove();
                    checkRemainingUsers(previousRoleId);
                },
                error: function (data, status) {

                }
            });

        });
    });

    //Delete User From Table
    $(document).on("click", ".delete-user-link", function (e) {

        e.preventDefault();

        var userId = $(e.target).data("user-id");
        var roleId = $("#user-role-" + userId).val();

        $.ajax({
            url: contextRoot + "/admin/user/delete/" + userId,
            type: "DELETE",
            success: function (data, status) {
                $("#edit-user-row-" + userId).remove();
                $("#user-row-" + userId).remove();
                checkRemainingUsers(roleId);
            },
            error: function (data, status) {
                alert("Error");
            }
        });

    });

    function checkRemainingUsers(id) {
        var count = $("#delete-role-table tr").length;

        if (count <= 1) {
            $("#can-not-remove-role").replaceWith("<button type='button' data-role-id='" + id + "' id='remove-role' class='delete-role-link btn btn-default' data-dismiss='modal'>Remove Role</button>")

        }
    }



});