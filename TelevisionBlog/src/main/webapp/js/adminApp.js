
$(document).ready(function() {
   
    //Approve Pending Posts
    $(document).on("click", ".approve-post-link", function(e) {
        
        e.preventDefault();
        
        var postId = $(e.target).data("post-id");
       
        $.ajax({
            url: contextRoot + "/admin/" + postId,
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