/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    $(document).on('click', '#insert-tags-button', function(e) {
        e.preventDefault();
        var body = $(tinymce.activeEditor.getBody());
        var tags = $('#tag-input').val();
        for (var i = 0; i < tags.length; i++) {
            body.append('#' + tags[i] + ' ');
        }
        $('.chosen-select option').prop('selected', false).trigger('chosen:updated');
        $('#HashtagModal').modal('hide');

    });
    $('#HashtagModal').on('shown.bs.modal', function(e) {
        $('.chosen-select').chosen();
    });
});

