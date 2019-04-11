$(function () {

    var totalCart = 0;
    $('.cartBtn').on('click', function () {
        $(this).attr('disabled');

        var price = $(this).prev().text();
        console.log(price);
        var name = $(this).parent().prev().attr("src");
        // console.log(name);
        var nameee = name.substring(name.lastIndexOf("/") + 1, name.lastIndexOf(".png"));
        // console.log(nameee);
        var url = "/assets/images/" + nameee + ".png";
        price = price.substring(0, price.indexOf(" "));
        console.log(price + "dsnjdjs");


        var row = ' <div class="row item-row-r">' +
            '<div class="col-md-2">' +
            '<img class="item-img"' + "src=" + '"' + url + '"' +
            'height="30" width="30"/>' +
            '</div>' +
            '<div class="col-md-3">' +
            '<p class="item-name">' + nameee + '</p>' +
            '</div>' +
            '<div class="col-md-3">' +
            '' +
            '</div>' +
            '<div class="col-md-2 item-price">' +
            '' + price + '' +
            '</div>' +
            '<div><button class="clear-item">&times;</button>' +
            '' +
            '</div>'
            + '';

        $(".item-data-r").append(row);

        totalCart += parseInt(price);
        $("#points").text(totalCart);


    });


    var items = "";
    var totalPrice = "";
    var itemUrls = "";

    $(".RButton").click(function () {

        if (!$(".item-data-r").children().length > 0) {
            $("#result").addClass("alert alert-danger");
            $("#result").append("Order Something");
            setTimeout(
                function(){
                    location.reload();

                },3000
            );
        }
        else {
            var child = $(".item-data-r").children();
            child.each(function (index) {
                var itemName = $(this).find($(".item-name")).text();
                var itemPrice = $(this).find($(".item-price")).text();
                var imgUrl = $(this).find($(".item-img")).text()
                console.log(itemName + " " + itemPrice + " " + imgUrl);
                items = items + itemName + " ";
                totalPrice = totalPrice + itemPrice + " ";
                itemUrls = itemUrls + imgUrl + " ";

            })

            $.ajax({

                url: "/itemRedeem",
                type: "post",
                data: {items: items, totalPrice: totalCart, itemUrls: itemUrls},
                success: function (e) {
                    if (e == "redeemed") {
                        $("#result").addClass("alert alert-success");
                        $("#result").append("Order Placed");
                        setTimeout(
                            function(){
                                location.reload();

                            },3000
                        );
                    }
                    else {
                        $("#result").addClass("alert alert-danger");
                        $("#result").append("Not Enough Points");
                        setTimeout(
                            function(){
                                location.reload();

                            },3000
                        );
                    }

                }
            });


        }
    });

    $('body').on('click', '.clear-item', function () {
        console.log("clickedd");
        var priceee = parseInt($(this).parent().prev().text());
        console.log(priceee);
        totalCart = totalCart - priceee;
        $("#points").text(totalCart);
        $(this).parent().parent().remove();

    });

});