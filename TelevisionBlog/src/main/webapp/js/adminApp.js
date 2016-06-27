
$(document).ready(function () {

    //Approve Pending Posts
    $(document).on("click", ".approve-post-link", function (e) {

        e.preventDefault();

        var postId = $(e.target).data("post-id");

        $.ajax({
            url: contextRoot + "/admin/approval/1/" + postId,
            type: "POST",
            datatype: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {
                if (data) {
                    $("#post-row-" + postId).remove();
                } else {
                    alert("That post is expired.\n"
                            + "Change the expiration date to approve.")
                }
            },
            error: function (data, status) {

            }
        });

    });

    //Approve Pending Posts
    $(document).on("click", ".disapprove-post-link", function (e) {

        e.preventDefault();

        var postId = $(e.target).data("post-id");

        $.ajax({
            url: contextRoot + "/admin/approval/2/" + postId,
            type: "POST",
            datatype: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $("#post-row-" + postId).remove();
            },
            error: function (data, status) {

            }
        });

    });

    //Remove Post
    $(document).on("click", ".delete-post-link", function (e) {
        
        e.preventDefault();

        var postId = $(e.target).data("post-id");

        $.ajax({
            url: contextRoot + "/blog/" + postId,
            type: "DELETE",
            success: function (data, status) {
                $("#post-row-" + postId).remove();

            },
            error: function (data, status) {

            }
        });

    });




});