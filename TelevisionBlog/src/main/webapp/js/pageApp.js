
$(document).ready(function () {

//Create Page
    $("#create-submit").on("click", function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var pageData = JSON.stringify({
            name: $("#page-title-input").val(),
            content: $("#page-content-input").val(),
            url: $("#page-url-input").val()
        });
        $.ajax({
            url: contextRoot + "/admin/page/create/",
            type: "POST",
            data: pageData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                window.location = contextRoot + "/admin/page/";
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, error) {

                    if (error.fieldName === "url") {
                        $("#page-url-error").append(error.message + "<br />");

                    } else if(error.fieldName === "name") {
                        $("#page-name-error").append(error.message + "<br />");
                        
                    } else if(error.fieldName === "content") {
                        $("#page-content-error").append(error.message + "<br />");
                        
                    } else {

                        $("#add-page-validation-errors").append(error.fieldName + ": " + error.message + "<br />");
                    }
                });
            }
        });
    });
    $("#edit-submit").on("click", function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var pageData = JSON.stringify({
            id: $("#page-id").val(),
            name: $("#page-title-input").val(),
            content: $("#page-content-input").val(),
            url: $("#page-url-input").val(),
            active: $("#page-active").val()
        });
        $.ajax({
            url: contextRoot + "/admin/page/update/",
            type: "PUT",
            data: pageData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                window.location(contextRoot + "/admin/page/update/");
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, error) {

                    $("#add-page-validation-errors").append(error.fieldName + ": " + error.message + "<br />");
                });
            }
        });
    });

    $(document).on("click", ".delete-page-link", function (e) {

        e.preventDefault();
        var pageId = $(e.target).data("page-id");
        $.ajax({
            url: contextRoot + "/admin/page/delete/" + pageId,
            type: "DELETE",
            success: function (data, status) {
                $("#page-row-" + pageId).remove();
            },
            error: function (data, status) {
                alert("ERROR deleting");
            }
        });
    });
    //Create active page row
    function buildPageRow(data) {

        return "<tr id='page-row-" + data.id + "' class='page-rows'> \n\
                                    <td><a href='${pageContext.request.contextPath}/" + data.url + "'>" + data.name + "</a></td> \n\
                                    <td><a href='${pageContext.request.contextPath}/page/edit/" + data.id + "' class='glyphicon glyphicon-edit' style='color: green;'></a></td> \n\
                                    <td><a href='' data-page-id='" + data.id + "' class='delete-page-link glyphicon glyphicon-remove' style='color:red;'></a></td> \n\
                                    <td>" + data.position + "</td> \n\
                                    <td><a href='' data-page-id='" + data.id + "' class='active-page-link glyphicon glyphicon-check' style='color:dodgerblue;'></a></td>\n\
                                </tr>";
    }

    //Create inactive page row
    function buildPageRowIA(data) {

        return "<tr id='page-row-" + data.id + "' class='page-rows'> \n\
                                    <td><a href='${pageContext.request.contextPath}/" + data.url + "'>" + data.name + "</a></td> \n\
                                    <td><a href='${pageContext.request.contextPath}/page/edit/" + data.id + "' class='glyphicon glyphicon-edit' style='color: green;'></a></td> \n\
                                    <td><a href='' data-page-id='" + data.id + "' class='delete-page-link glyphicon glyphicon-remove' style='color:red;'></a></td> \n\
                                    <td>" + data.position + "</td> \n\
                                    <td><a href='' data-page-id='" + data.id + "' class='active-page-link glyphicon glyphicon-unchecked' style='color:red;'></a></td>\n\
                                </tr>";
    }


    //Used to Auto Populate Static Page Path with title
    $("#page-title-input").on("input", function (e) {

//        var myRegex= /(([a-zA-Z0-9])+)/g;

        var titleData = $("#page-title-input").val();
        var noSpecialChars = titleData.replace(/[^\w\s]/gi, '');
//        var match = myRegex.exec(titleData);

        $("#page-url-input").val(noSpecialChars.replace(/[\s]+/g, '-').toLowerCase());
//        $("#page-url-input").val(match[1]);

    });
    
    //Used to Remove special characters and redundant spacing from URL pattern
    $("#page-url-input").on("input", function (e) {

//        var myRegex= /(([a-zA-Z0-9])+)/g;

        var urlData = $("#page-url-input").val();
        var noSpecialChars = urlData.replace(/[^\w\s]/gi, '');
//        var match = myRegex.exec(titleData);

        $("#page-url-input").val(noSpecialChars.replace(/[\s]+/g, '-').toLowerCase());
//        $("#page-url-input").val(match[1]);

    });
    
    
    //Jquery Ui Sortable - Used for drag and drop static Page order
    $(document).ready(function () {

        var fixHelper = function (e, ui) {
            ui.children().each(function () {
                $(this).width($(this).width());
            });
            return ui;
        };
        $("tbody").sortable({
            helper: fixHelper,
            axis: 'y',
            update: function (event, ui) {
                var data = $(this).sortable("toArray");
                // POST to server using $.post or $.ajax
                $.ajax({
                    data: JSON.stringify(data),
                    type: "POST",
                    url: contextRoot + "/admin/page/position",
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
            }
        });
    });

    //Toggle Static Page Active/Inactive
    $(document).on("click", ".active-page-link", function (e) {
        e.preventDefault();

        var pageId = $(e.target).data("page-id");
        $.ajax({
            url: contextRoot + "/admin/page/toggle-active/" + pageId,
            type: "GET",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                var tableRow;
                if (data.active === true) {
                    tableRow = buildPageRow(data);

                } else {
                    tableRow = buildPageRowIA(data);
                }
//                if (data.position > 0) {
                    $("#page-row-" + pageId).replaceWith($(tableRow));
//                } else {
//                    alert("Page position must be set before you can Activate.");
//                }
            },
            error: function (data, status) {

            }
        });
    });
});
