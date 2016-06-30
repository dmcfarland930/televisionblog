$(document).ready(function () {

    $('#blog-post-button').on('click', function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var date = new Date();
        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            content: $('#blog-post-input').val(),
            userId: $('#author-input').val(),
            categoryId: $('#category-input').val(),
            tagIdList: $('#tag-input').val(),
            active: false,
            approved: true,
            postDate: $('#post-date-input').val(),
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
                window.location = contextRoot + "/admin/post/";
            },
            error: function (data, status) {
                console.log("error creating blog post");
            }
        });
    });
    
    $('#blog-modal-submit-button').on('click', function (e) {
        $('#submitBlogModal').modal('show');
        
    });


    $('#blog-draft-button').on('click', function (e) {

        e.preventDefault();

        var date = new Date();

        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            content: $('#blog-post-input').val(),
            approved: false,
            postDate: date

        });
        
        var date = $('#last-date').val();
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
            },
            error: function (data, status) {
                
            }
        });
    });

    
    $(document).on('click', '.image-upload', function(e) {
        e.preventDefault();
        $(this).removeClass('image-upload');
        $(this).addClass('selected-image-link');
        $(this).children().addClass('selected-image');
    });
    
    $(document).on('click', '.selected-image-link', function(e) {
        e.preventDefault();
        $(this).addClass('image-upload');
        $(this).removeClass('selected-image-link');
        $(this).children().removeClass('selected-image');
    });
    
    $(document).on('click', '#add-images', function(e) {
        e.preventDefault();
        var body = $(tinymce.activeEditor.getBody());
        $('.selected-image').each(function() {
            $(this).removeClass('selected-image');
            var a = $(this);
            body.append(a.clone());
        });
        $('#UploadModal').modal('hide');
        
        
    });
    $('#file-upload-button').on('click', function(e) {
        e.preventDefault();
        var formData = new FormData();
        console.log('file', $('input[type=file]')[0].files[0]);
        formData.append('file', $('input[type=file]')[0].files[0]);

        console.log("form data " + formData);
        $.ajax({
            url : contextRoot + '/upload',
            data : formData,
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {
                $("#image-upload-list").append("<div style='padding-bottom: 5px;' class='col-md-2'> \n\
                                                <a href='#' class='image-upload' id='image-upload-" + data.id + "'><img  style='height: 50px; width: auto' src ='" + contextRoot + "/upload/showImage/" + data.id + "'></a> \n\
                                                </div>");
            },
            error : function(data) {
            }
        });
    });
    $('.chosen-select').chosen();
        
    
});