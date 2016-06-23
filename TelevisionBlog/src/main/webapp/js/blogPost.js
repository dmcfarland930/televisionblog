$(document).ready(function () {

    $('#blog-post-button').on('click', function (e) {

        e.preventDefault();
        tinymce.triggerSave();
        var date = new Date();
        var d = date.getDate();
        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            content: $('#blog-post-input').val(),
            categoryId: $('#category-input').val(),
            approved: true,
            postDate: date

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
    
//        $('#blog-edit-submit').on('click', function (e){
//            
//            e.preventDefault();
//            
//            var postId = $(e.target).data("post-id");
//            
//               $.ajax({
//            url: contextRoot + "/admin/,
//            type: "POST",
//            datatype: "json",
//            beforeSend: function(xhr) {
//                xhr.setRequestHeader("Accept", "application/json");
//                xhr.setRequestHeader("Content-type", "application/json");
//            },
//            success: function(status, data) {
//                
//                $('#title-edit').val(data.title);
//                $('#category-edit').val(data.category);
//                $('#blog-post-edit').val(data.content);
////                $("#post-row-"+postId).remove();
//                
//            },
//            error: function(status, data) {
//               
//            }
//        });
//            
//        });
    
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
    $('#upload-button').on('click', function(e) { 
        var formData = new FormData($('form')[0]);
        console.log(formData);
        $.ajax({
            url: contextRoot + '/upload',  //Server script to process data
            type: 'POST',
            xhr: function() {  // Custom XMLHttpRequest
                var myXhr = $.ajaxSettings.xhr();
                if(myXhr.upload){ // Check if upload property exists
                    myXhr.upload.addEventListener('progress',progressHandlingFunction, false); // For handling the progress of the upload
                }
                return myXhr;
            },
            //Ajax events
            beforeSend: beforeSendHandler,
            success: completeHandler,
            error: errorHandler,
            // Form data
            data: formData,
            //Options to tell jQuery not to process data or worry about content-type.
            cache: false,
            contentType: false,
            processData: false
        });
    
    
    
    
    });
    function progressHandlingFunction(e){
        if(e.lengthComputable){
            $('progress').attr({value:e.loaded,max:e.total});
        }
    }
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