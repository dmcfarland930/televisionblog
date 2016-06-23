
$(document).ready(function() {
   
    //Approve Pending Posts
    $(document).on("click", ".approve-post-link", function(e) {
        
        e.preventDefault();
        
        var postId = $(e.target).data("post-id");
       
        $.ajax({
            url: contextRoot + "/admin/approval/" + postId +"/1",
            type: "POST",
            datatype: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function(status, data) {
                
                $("#post-row-"+postId).remove();
            },
            error: function(status, data) {
               
            }
        });
        
    });
    
    //Approve Pending Posts
    $(document).on("click", ".disapprove-post-link", function(e) {
        
        e.preventDefault();
        
        var postId = $(e.target).data("post-id");
       
        $.ajax({
            url: contextRoot + "/admin/approval/" + postId + "/2",
            type: "POST",
            datatype: "json",
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function(status, data) {
                
                $("#post-row-"+postId).remove();
            },
            error: function(status, data) {
               
            }
        });
        
    });
    
    
    
});