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
            active: false,
            approved: true,
            postDate: $('#post-date-input').val(),
            expirationDate: $('#expiration-date-input').val()

        });
//        $('#name-div').removeClass('has-error');
//        $('#area-div').removeClass('has-error');
//        $('#name-error').empty();
//        $('#area-error').empty();
//        var date = $('#last-date').val();
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

                alert("BLOG SAVED!");
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
//            var errors = data.responseJSON.errors;
//            $('#name-error').empty();
//            $('#area-error').empty();
//
//            $.each(errors, function (index, error) {
//
//                switch (error.fieldName) {
//                    case "customerName":
//                        $('#name-error').append(error.message);
//                        $('#name-div').addClass('has-error');
//                        break;
//                    case "area":
//                        $('#area-error').append(error.message);
//                        $('#area-div').addClass('has-error');
//                        break;
//                    default:
//                        break;
//
//                }
//
//            });
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
//        $('#name-div').removeClass('has-error');
//        $('#area-div').removeClass('has-error');
//        $('#name-error').empty();
//        $('#area-error').empty();
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

                alert("BLOG SAVED AS DRAFT!");
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
//            var errors = data.responseJSON.errors;
//            $('#name-error').empty();
//            $('#area-error').empty();
//
//            $.each(errors, function (index, error) {
//
//                switch (error.fieldName) {
//                    case "customerName":
//                        $('#name-error').append(error.message);
//                        $('#name-div').addClass('has-error');
//                        break;
//                    case "area":
//                        $('#area-error').append(error.message);
//                        $('#area-div').addClass('has-error');
//                        break;
//                    default:
//                        break;
//
//                }
//
//            });
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
                    alert("<img  src='" + contextRoot + "/upload/showImage/" + data.id + "' alt='Not Found'>");
                    $("#image-upload-list").append("<div style='padding-bottom: 5px;' class='col-md-2'> \n\
                                                    <a href='#' class='image-upload' id='image-upload-" + data.id + "'><img  style='height: 50px; width: auto' src ='" + contextRoot + "/upload/showImage/" + data.id + "'></a> \n\
                                                    </div>");
                },
                error : function(err) {
                    alert(err);
                }
            });
        });
    
    
    
//    $('#next-page-link').on('click', function (e) {
//        
//        e.preventDefault();
//        
//        var pageNumber = $('#next-page-link').val();
//        
//        console.log(this.value);
//        
//        console.log($('#next-page-link').val());
//        
//        $.ajax({
//            url: contextRoot + "/blog/page/" + pageNumber,
//            type: "GET",
//            data: pageNumber,
//            dataType: 'json',
//            beforeSend: function (xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-type", "application/json");
//            },
//            success: function (data, status) {
//
//            },
//            error: function (data, status) {
//
//            }
//        });
//    });
});