$(document).ready(function () {

    $('#blog-submit').on('click', function (e) {

        e.preventDefault();

        var blogPost = JSON.stringify({
//            content = 

        });

        $('#name-div').removeClass('has-error');
        $('#area-div').removeClass('has-error');
        $('#name-error').empty();
        $('#area-error').empty();
        var date = $('#last-date').val();

        console.log(date);

        $.ajax({
            url: contextRoot + "/order/",
            type: "POST",
            data: orderData,
            dataType: 'json',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-type", "application/json");
            },
            success: function (data, status) {

                $('#no-order').remove();
                console.log(data);

                if (date === data.date) {
                    var tableRow = buildOrderRow(data);
                }
                $('#order-table').append($(tableRow));
                $("#order-name-input").val('');
                $("#order-area-input").val('');
                $("#order-number").val(data.orderNumber);
                $('#show-header').text("Order Added");

                $('#order-date').text(data.date);
                $('#order-name').text(data.customerName);
                $('#order-state').text(data.state);
                $('#order-product').text(data.productType);
                $('#order-area').text(data.area);

                $('#order-product-cost').text(data.costPerSqFt);
                $('#order-labor-cost').text(data.laborCostPerSqFt);
                $('#order-tax-rate').text(data.taxRate);

                $('#order-product-total').text(data.materialCost);
                $('#order-labor-total').text(data.totalLaborCost);
                $('#order-tax-total').text(data.tax);
                $('#order-grand-total').text(data.orderTotal);

                $('#showOrderModal').modal('show');

            },
            error: function (data, status) {
                var errors = data.responseJSON.errors;
                $('#name-error').empty();
                $('#area-error').empty();

                $.each(errors, function (index, error) {

                    switch (error.fieldName) {
                        case "customerName":
                            $('#name-error').append(error.message);
                            $('#name-div').addClass('has-error');
                            break;
                        case "area":
                            $('#area-error').append(error.message);
                            $('#area-div').addClass('has-error');
                            break;
                        default:
                            break;

                    }

                });
            }


        });

    });

});

