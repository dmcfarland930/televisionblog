$(document).ready(function () {

    jQuery('#post-date-input').datetimepicker({
        format: 'Y-m-d H:i'

    });

    jQuery('#expiration-date-input').datetimepicker({
        format: 'Y-m-d'

    });
    jQuery('#edit-post-expiration-date').datetimepicker({
        timepicker: false,
        format: 'Y-m-d'

    });



    $('#blog-post-button').on('click', function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var date = new Date();
        var reg = /(#)([a-z\d-]+)/gi;
        var tags = [];
        var content = $('#blog-post-input').val();
        var result;
        while ((result = reg.exec(content)) !== null) {
            tags.push(result[2]);
        }
        console.log(tags);
        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            url: $('#slug-input').val(),
            content: $('#blog-post-input').val(),
            userId: $('#author-input').val(),
            categoryId: $('#category-input').val(),
            tagNameList: tags,
            postDate: $('#post-date-input').val(),
            isDraft: false,
            approved: false,
            expirationDate: $('#expiration-date-input').val()

        });

        console.log(date);
        $.ajax({
            url: contextRoot + "/blog/create-blog-post/",
            type: "POST",
            data: blogPost,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#title-input').val("");
                tinyMCE.activeEditor.setContent("");
                $('#no-order').remove();
                console.log(data);
                if (date === data.date) {
                    var tableRow = buildOrderRow(data);
                }

                console.log("SUCCESS");
                window.location = contextRoot + "/admin/";
            },
            error: function (data, status) {
                $('#submitBlogModal').modal('hide');

                console.log("error creating blog post");
                var errors = data.responseJSON.errors;
                $('#title-error').empty();
                $('#url-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "title":
                            $('#title-error').append(error.message);
                            $('#title-div').addClass('has-error');
                            break;
                        case "url":
                            $('#url-error').append(error.message);
                            $('#slug-div').addClass('has-error');
                            break;
                        default:
                            break;

                    }

                });
            }
        });
    });

    $('#blog-modal-submit-button').on('click', function (e) {
        $('#submitBlogModal').modal('show');

    });


    $('#blog-draft-button').on('click', function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var date = new Date();
        var reg = /(#)([a-z\d-]+)/gi;
        var tags = [];
        var content = $('#blog-post-input').val();
        var result;
        while ((result = reg.exec(content)) !== null) {
            tags.push(result[2]);
        }

        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            url: $('#slug-input').val(),
            content: $('#blog-post-input').val(),
            userId: $('#author-input').val(),
            categoryId: $('#category-input').val(),
            tagNameList: tags,
            postDate: $('#post-date-input').val(),
            expirationDate: $('#expiration-date-input').val(),
            approved: false,
            isDraft: true

        });

        console.log(date);
        $.ajax({
            url: contextRoot + "/blog/create-blog-post/",
            type: "POST",
            data: blogPost,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#title-input').val("");
                $('#blog-post-input').val("");
                $('#no-order').remove();
                console.log(data);
                if (date === data.date) {
                    var tableRow = buildOrderRow(data);
                }

                console.log("SUCCESS");
                window.location = contextRoot + "/admin/";
            },
            error: function (data, status) {

            }
        });
    });


    $(document).on('click', '.image-upload', function (e) {
        e.preventDefault();
        $(this).removeClass('image-upload');
        $(this).addClass('selected-image-link');
        $(this).children().addClass('selected-image');
    });

    $(document).on('click', '.selected-image-link', function (e) {
        e.preventDefault();
        $(this).addClass('image-upload');
        $(this).removeClass('selected-image-link');
        $(this).children().removeClass('selected-image');
    });

    $(document).on('click', '#add-images', function (e) {
        e.preventDefault();
        var body = $(tinymce.activeEditor.getBody());
        $('.selected-image').each(function () {
            $(this).removeClass('selected-image');
            var a = $(this).clone();
            $(a).height(200).width('auto');
            body.append(a.clone());
        });
        $('#UploadModal').modal('hide');


    });

    $(document).on('click', '#delete-images', function (e) {
        e.preventDefault();
        $('.selected-image-link').each(function () {
            var img = $(this);
            var imgId = $(this).attr('id');
            imgId = imgId.replace('image-upload-', '');
            console.log(imgId);
            $.ajax({
                url: contextRoot + '/upload/' + imgId,
                type: 'DELETE',
                success: function (data) {
                    img.parent().remove();
                    img.remove();
                },
                error: function (data) {
                }
            });
        });
    });

    $('#file-upload-button').on('click', function (e) {
        e.preventDefault();
        var formData = new FormData();
        console.log('file', $('input[type=file]')[0].files[0]);
        formData.append('file', $('input[type=file]')[0].files[0]);

        console.log("form data " + formData);
        $.ajax({
            url: contextRoot + '/upload',
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {
                $("#image-upload-list").append("<div style='padding-bottom: 5px;' class='col-md-2'> \n\
                                                <a href='#' class='image-upload' id='image-upload-" + data.id + "'><img  style='height: 50px; width: auto' src ='" + contextRoot + "/upload/showImage/" + data.id + "'></a> \n\
                                                </div>");
            },
            error: function (data) {
            }
        });
    });


    $("#title-input").on("input", function (e) {

//        var myRegex= /(([a-zA-Z0-9])+)/g;

        var titleData = $("#title-input").val();
        var noSpecialChars = titleData.replace(/[^\w\s]/gi, '');
//        var match = myRegex.exec(titleData);

        $("#slug-input").val(noSpecialChars.replace(/[\s]+/g, '-').toLowerCase());
//        $("#page-url-input").val(match[1]);

    });
});