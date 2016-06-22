/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $('#create-submit').on('click', function(e) {

            e.preventDefault();

            var categoryData = JSON.stringify({
                categoryName: $('#name-input').val()

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
                    alert("success");

                    var tableRow = buildCategoryRow(data);

                    $('#category-table').append($(tableRow));
                },
                error: function(data, status) {
                    alert("error");
                }
            });

            //alert("alert after ajax");
        });
    function buildCategoryRow(data) {
        return "<tr id='category-row-" + data.id + "'>  \n\
                <td><a data-category-id='" + data.id +"' data-toggle='modal' data-target='#showCategoryModal'>" + data.name + "</a></td>  \n\
                <td> <a data-category-id='" + data.id +"' data-toggle='modal' data-target='#editCategoryModal'>Edit</a>  </td>   \n\
                <td> <a data-category-id='" + data.id +"' class='delete-link'>Delete</a>  </td>   \n\
                </tr>  ";
        
    }
});
