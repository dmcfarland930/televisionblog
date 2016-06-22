$(document).ready(function () {

    $('#blog-post-button').on('click', function (e) {

        e.preventDefault();
        var blogPost = JSON.stringify({
            title: $('#title-input').val(),
            content: $('#blog-entry').val()
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