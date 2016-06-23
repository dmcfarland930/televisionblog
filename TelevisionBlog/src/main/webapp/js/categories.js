/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#create-submit').on('click', function(e) {

            e.preventDefault();

            var categoryData = JSON.stringify({
                name: $('#name-input').val()

            });

            $.ajax({

                url: contextRoot + "/category",
                type: "POST",
                data: categoryData,
                dataType: 'json',
                beforeSend: function(xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-type", "application/json");
                },
                success: function(data, status) {
                    $("#category-name").val("");
                    var tableRow = buildCategoryRow(data);
                    
                    $('#category-table').append($(tableRow));
                },
                error: function(data, status) {
                    alert("error");
                }
            });

            //alert("alert after ajax");
        });
    $('#editCategoryModal').on('show.bs.modal', function(e) {
        var link = $(e.relatedTarget);
        
        var categoryId = link.data('category-id');
        
        $.ajax({ 
        
            url: contextRoot + "/category/" + categoryId,
            type: 'GET',
            dataType: 'json',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
            },
            success : function(data, status) {
                
                $('#edit-category-name').val(data.name);
                $('#edit-category-id').val(data.id);
            },
            error: function(data, status) {
                
            }
        
        });
    });
    
    $('#edit-category-button').on('click', function(e) {
        
        e.preventDefault();
        
        var categoryData = JSON.stringify({
            name: $('#edit-category-name').val(),
            id: $('#edit-category-id').val()
        });
        
        $.ajax({
            
            url: contextRoot + "/category",
            type: "PUT",
            data: categoryData,
            dataType: 'json',
            beforeSend: function(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function(data, status) {
                
                $('#editCategoryModal').modal('hide');
                var tableRow = buildCategoryRow(data);
                
                $('#category-row-' + data.id).replaceWith( $(tableRow) );
            },
            error: function(data, status) {
                alert("error");
            }
        });
        
        //alert("alert after ajax");
        
    });
    $(document).on('click', '.delete-link', function(e) { 
        e.preventDefault();
        
        var categoryId = $(e.target).data('category-id');
        
        $.ajax({
            type: "DELETE",
            url: contextRoot + "/category/" + categoryId,
            success: function(data, status) {
                $('#category-row-' + categoryId).remove();
            },
            error: function(data, status) {
                
            }
        });
    });
    function buildCategoryRow(data) {
        return "<tr> \n\
                    <td>"+ data.name + "</td> \n\
                    <td><a type='button' class='btn btn-info btn-sm' data-toggle='modal' data-category-id='" + data.id + "' data-target='#editCategoryModal'>Edit</a></td> \n\
\n\                 <td><a type='button' class='btn btn-danger btn-sm' data-category-id='" + data.id + "' class='delete-link'>Delete</a></td> \n\
                </tr>";
    }
});
    

