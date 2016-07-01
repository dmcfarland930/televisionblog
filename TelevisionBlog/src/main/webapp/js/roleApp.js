/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    //Disables Admin checkboxes and checks all roles.
    $(document).ready(function () {
        $("input[name='ROLE_ADMIN']").prop("disabled", true);
//        $("input[name='ROLE_ADMIN']").prop("checked", true);
    });


    //Disables all checkboxes that are not custom
    $(document).ready(function () {
        var currentRole = $("select[name='user-role']");

        $.each(currentRole, function (key, value) {
            if (currentRole.val() !== "ROLE_CUSTOM") {
                $(this).closest("tr").find(".chkbox").prop("disabled", true);
            }
        });

    });

    //When changing to custom Role, checkbboxes are enabled.
    $("select").on('change', function (e) {

        var userRole = $(this).val();
        if (userRole === "ROLE_CUSTOM") {
            $(this).closest("tr").find(".chkbox").prop("disabled", false);
        }

    });
    
    //When checking a box, save changes
    $("input[type='checkbox'").on("change", function(e) {
       
        if($(this).is(":checked")) {
            var roleId = $(e.target).data("role-id");
            var userRightId = $(this).val();
            
            $.ajax({
               url: contextRoot + "/admin/uac/add/" +roleId+"/" +userRightId,
               type: "GET",
               beforeSend: function(xhr) {
                   xhr.setRequestHeader("Accept", "application/json");
               },
               success: function(data, status) {
                   
               },
               error: function(data, status) {
                   
               }
            });
                     
        } else {
            var roleId = $(e.target).data("role-id");
            var userRightId = $(this).val();
            
            $.ajax({
               url: contextRoot + "/admin/uac/remove/" +roleId+"/" +userRightId,
               type: "GET",
               beforeSend: function(xhr) {
                   xhr.setRequestHeader("Accept", "application/json");
               },
               success: function(data, status) {
                   
               },
               error: function(data, status) {
                   
               }
            });
        }
        
    });
});