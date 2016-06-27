
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

    //Edit Expiration Date Modal
    $("#edit-date-modal").on("show.bs.modal", function (e) {

        var link = $(e.relatedTarget);

        var postId = link.data("post-id");

        $.ajax({
            url: contextRoot + "/blog/grab/" + postId,
            type: "GET",
            datatype: "json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function (data, status) {
                $("#edit-post-expiration-date").val(data.expirationDate);
                $("#edit-post-title").val(data.title);
                $("#edit-post-user-id").val(data.user.id);
                $("#edit-post-category").val(data.category.id);
                $("#edit-post-content").val(data.content);
                $("#edit-post-date").val(data.postDate);
                $("#edit-post-active").val(data.active);
                $("#edit-post-approved").val(data.approved);
                $("#edit-post-id").val(data.id);
            },
            error: function (data, status) {

            }
        });

        //Edit Expiration Date Button
        $("#edit-date-button").on("click", function (e) {

            var postData = JSON.stringify({
                id: $("#edit-post-id").val(),
                expirationDate: $("#edit-post-expiration-date").val(),
                title: $("#edit-post-title").val(),
                userId: $("#edit-post-user-id").val(),
                categoryId: $("#edit-post-category").val(),
                content: $("#edit-post-content").val(),
                postDate: $("#edit-post-date").val(),
                active: $("#edit-post-active").val(),
                approved: $("#edit-post-approved").val()
            });
            
            $.ajax({
               url: contextRoot + "/blog/",
               data: postData,
               type: "PUT",
               datatype: "json",
               beforeSend: function(xhr) {
                   xhr.setRequestHeader("Accept", "application/json");
                   xhr.setRequestHeader("Content-type", "application/json");
               },
               success: function(data, status) {
                   $("#edit-date-modal").modal("hide");
                   
                   var tableRow = buildTableRow(data);
                   
                   $("#post-row-"+data.id).replaceWith($(tableRow));
               },
               error: function(data, status) {
                   
               }
            });

        });

    });
    
    function buildTableRow (data) {
      return "<tr id='post-row-"+data.id+"'>\n\
                <td><a href='${pageContext.request.contextPath}/blog/"+data.title+"'>"+data.title+"</a></td> \n\
                                <td>"+data.postDate+"</td> \n\
                                <td><a href='' data-post-id='"+data.id+"' data-toggle='modal' data-target='#edit-date-modal' class='glyphicon glyphicon-adjust' > "+data.expirationDate+"</a></td> \n\
                                <td>"+data.user.username+"</td> \n\
                                <td><a href='${pageContext.request.contextPath}/blog/edit/"+data.id+"' class='glyphicon glyphicon-edit' style='color:green;' ></a></td> \n\
                                <td><a href='' data-post-id='"+data.id+"' class='glyphicon glyphicon-transfer disapprove-post-link' style='color:dodgerblue;'></a></td> \n\
                                <td><a href='' data-post-id='"+data.id+"' class='delete-post-link glyphicon glyphicon-remove' style='color:red;'></a></td>  \n\
                                </tr>";
        
    };

});