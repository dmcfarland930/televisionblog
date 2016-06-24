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
                $('#blog-post-input').val("");
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


});