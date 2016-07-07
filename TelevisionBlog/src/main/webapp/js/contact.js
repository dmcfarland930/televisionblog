/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
   $('#script-upload-button').on('click', function(e) {
        e.preventDefault();
        var formData = new FormData();
        console.log('file', $('input[type=file]')[0].files[0]);
        formData.append('file', $('input[type=file]')[0].files[0]);
        var sender = $('#name-input').val();
        var email = $('#email-input').val();
        console.log("form data " + formData);
        var url = contextRoot + '/contact/send-script?sender='+sender + '&email=' + email;
        console.log(url);
        $.ajax({
            url : contextRoot + '/contact/send-script?sender='+sender + '&email=' + email,
            data : formData,
            processData : false,
            contentType : false,
            type : 'POST',
            success : function(data) {
                $(location).attr('href', contextRoot);
            },
            error : function(data) {
                alert('error');
            }
        });
    });
});














