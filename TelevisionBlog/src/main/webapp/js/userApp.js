$(document).ready(function() {
   
    //Create User
    $("#create-user-submit").on("click", function(e) {
       
        e.preventDefault();
        
        var userData = JSON.stringify({
            firstName: $("#first-name-input").val(),
            lastName: $("#last-name-input").val(),
            username: $("#username-input").val(),
            password: $("#password-input").val(),
            groupId: $("#role-id-input").val()
        });
        $.ajax({
           url: contextRoot + "/user/" ,
           type: "POST",
           data: userData,
           dataType: "json",
           beforeSend: function(xhr) {
               xhr.setRequestHeader("Accept", "application/json");
               xhr.setRequestHeader("Content-type", "application/json");
           },
           success: function(data, status) {
               
               $("#create-user-modal").modal("hide");
               
               var userRow = buildUserRow(data);
               
               $("#user-table").append($(userRow));
               
               
               
           },
           error: function(data, status) {
               alert("Could not create user.");
           }
        });
        
    });
    
    //Edit User Pop Up
    $("#edit-user-modal").on("show.bs.modal", function(e) {
       
        var link = $(e.relatedTarget);
        
        var userId = link.data("user-id");

        $.ajax({
            url: contextRoot + "/user/" + userId,
            type: "GET",
            dataType: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success: function(data, status) {
                $("#edit-user-first-name").val(data.firstName);
                $("#edit-user-last-name").val(data.lastName);
                $("#edit-user-username").val(data.username);
                $("#edit-user-password").val(data.password);
                $("#edit-user-role").val(data.groupId);
                $("#edit-user-id").val(data.id);
               
            },
            error: function(data, status) {
                alert("Error, showing edit user modal.");
            }
        });;
                
    });
    
    $("#edit-user-button").on("click", function(e) {
       
        var userData = JSON.stringify({
            id: $("#edit-user-id").val(),
            firstName: $("#edit-user-first-name").val(),
            lastName: $("#edit-user-last-name").val(),
            username: $("#edit-user-username").val(),
            password: $("#edit-user-password").val(),
            groupId: $("#edit-user-role").val()
        });
        
        $.ajax({
           url: contextRoot + "/user/",
           type: "PUT",
           data: userData,
           dataType: "json",
           beforeSend: function(xhr) {
               xhr.setRequestHeader("Accept", "application/json");
               xhr.setRequestHeader("Content-type", "application/json");
           },
           success: function(data, status) {
               
               $("#edit-user-modal").modal("hide");
               
               var tableRow = buildUserRow(data);
               
               $("#user-row-"+data.id).replaceWith($(tableRow));
               
           },
           error: function(data, status) {
               alert("Error saving change.");
           }
        });
        
        
    });
    
    $(document).on("click", ".delete-user-link", function(e) {
        
        e.preventDefault();
        
        var userId = $(e.target).data("user-id");
       
        $.ajax({
           url: contextRoot + "/user/" + userId,
           type: "DELETE",
           success: function(data, status) {
               $("#user-row-"+userId).remove();
           },
           error: function(data, status) {
              alert("Error");
           }
        });
        
    });
    
    function buildUserRow(data) {
        return "<tr id='user-row-"+data.id+"'>\n\
                <td>"+data.firstName+"</td>\n\
                <td>"+data.lastName+"</td>\n\
                <td>"+data.username+"</td>\n\
                <td><a href='' data-user-id='"+data.id+"' data-toggle='modal' data-target='#edit-user-modal' class='glyphicon glyphicon-edit' style='color:green;'></td>\n\
                <td><a href='' data-user-id='"+data.id+"' class='delete-user-link glyphicon glyphicon-remove' style='color:red;'></td>\n\
                </tr>";
    }
    
});