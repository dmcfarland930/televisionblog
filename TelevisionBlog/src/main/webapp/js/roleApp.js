/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

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
            if (currentRole.val() === "Custom") {
                $(this).closest("tr").find(".checkbox").prop("disabled", true);
            } else {
                $(this).closest("tr").find("input[type='checkbox']").prop("disabled", true);
            }
        });

    });


    //When changing to custom Role, checkbboxes are enabled.
    $("select").on('change', function (e) {

        var userRole = $(this).val();
        if (userRole === "Custom") {
            $(this).closest("tr").find(".user-checkbox").prop("disabled", false);
        } else {
            $(this).closest("tr").find(".user-checkbox").prop("disabled", true);
        }

    });

    //When checking a box, save changes
    $(".role-checkbox").on("change", function (e) {

        if ($(this).is(":checked")) {
            var roleId = $(e.target).data("role-id");
            var userRightId = $(this).val();

            $.ajax({
                url: contextRoot + "/admin/uac/add/" + roleId + "/" + userRightId,
                type: "GET",
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
                type: "GET",
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

    //When checking a box, save changes **CUSTOM**
    $(".user-checkbox").on("change", function (e) {

        if ($(this).is(":checked")) {

            var userId = $(this).data("user-id");
            var userRightId = $(this).val();
            var roleId = $("#user-role-" + userId).val();

            $.ajax({
                url: contextRoot + "/admin/uac/add/" + roleId + "/" + userRightId + "/?userId=" + userId,
                type: "GET",
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
                type: "GET",
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

    function buildCheckboxRow(data) {
        return "<td>\n\
                <td>";
    }
});