
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
            url: contextRoot + "/page/",
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

                    $("#add-page-validation-errors").append(error.fieldName + ": " + error.message + "<br />");
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
            url: $("#page-url-input").val()
        });
        $.ajax({
            url: contextRoot + "/page/",
            type: "PUT",
            data: pageData,
            dataType: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                window.location(contextRoot + "/admin/page/");
            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $.each(errors, function (index, error) {

                    $("#add-page-validation-errors").append(error.fieldName + ": " + error.message + "<br />");
                });
            }
        });
    });
//    //Show Static Page
//    $("#show-page-modal").on("show.bs.modal", function (e) {
//
//        var link = $(e.relatedTarget);
//
//        var pageId = link.data("page-id");
//
//        $.ajax({
//            url: contextRoot + "/" + pageId,
//            type: "GET",
//            dataType: "json",
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//            },
//            success: function (data, status) {
//                $("#page-title").html(data.name);
//                $("#page-content").html(data.content);
//                $("#page-url").html(data.url);
//            },
//            error: function (data, status) {
//                alert("Modal could not be found");
//            }
//
//        });
//
//    });
//
//    //Show Edit Static Page
//    $("#edit-page-modal").on("show.bs.modal", function (e) {
//        
//        var link = $(e.relatedTarget);
//
//        var pageId = link.data("page-id");
//
//        $.ajax({
//            url: contextRoot + "/page/" + pageId,
//            type: "GET",
//            dataType: "json",
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//            },
//            success: function (data, status) {
//                
//                $("#edit-page-id").val(data.id);
//                $("#edit-page-title").val(data.name);
//                $("#edit-page-content").val(data.content);
//                tinyMCE.editors[0].setContent(data.content);
//                $("#edit-page-url").val(data.url);
//            },
//            error: function (data, status) {
//                alert("Modal could not be loaded.")
//            }
//        });
//
//    });
//
//    //Edit Static Page
//    $("#edit-page-button").on("click", function (e) {
//        
//        tinymce.triggerSave();
//
//        var pageData = JSON.stringify({
//            id: $("#edit-page-id").val(),
//            name: $("#edit-page-title").val(),
//            content: $("#edit-page-content").val(),
//            url: $("#edit-page-url").val()
//        });
//
//        $.ajax({
//            url: contextRoot + "/page/",
//            type: "PUT",
//            data: pageData,
//            dataType: "json",
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-type", "application/json");
//            },
//            success: function (data, status) {
//                alert("Page edit successful");
//            },
//            error: function (data, status) {
//                alert("ERROR: FAILURE Contact Your Developer");
//            }
//        });
//    });

    //Delete Static Page
    $(document).on("click", ".delete-page-link", function (e) {

        e.preventDefault();
        var pageId = $(e.target).data("page-id");
        $.ajax({
            url: contextRoot + "/page/" + pageId,
            type: "DELETE",
            success: function (data, status) {
                $("#page-row-" + pageId).remove();
            },
            error: function (data, status) {
                alert("ERROR deleting");
            }
        });
    });
    function buildPageRow(data) {

        return "<tr id='page-row-${data.id}' class='page-rows'> \n\
                                    <td><a href='${pageContext.request.contextPath}/" + data.url + "'>data.name</a></td> \n\
                                    <td><a href='${pageContext.request.contextPath}/page/edit/" + data.id + "' class='glyphicon glyphicon-edit' style='color: green;'></a></td> \n\
                                    <td><a href='' data-page-id='" + data.id + "' class='delete-page-link glyphicon glyphicon-remove' style='color:red;'></a></td> \n\
                                    <td>" + data.position + "</td> \n\
                                </tr>";
    }


    $("#page-title-input").on("input", function (e) {

//        var myRegex= /(([a-zA-Z0-9])+)/g;

        var titleData = $("#page-title-input").val();
        var noSpecialChars = titleData.replace(/[^\w\s]/gi, '');
//        var match = myRegex.exec(titleData);

        $("#page-url-input").val(noSpecialChars.replace(/[\s]+/g, '-').toLowerCase());
//        $("#page-url-input").val(match[1]);

    });

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
                    url: contextRoot + "/page/position",
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

});
