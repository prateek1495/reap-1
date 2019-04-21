$(function () {


    window.history.pushState(null, "", window.location.href);
    window.onpopstate = function () {
        window.history.pushState(null, "", window.location.href);
    };

    $("#endDate").val(moment().format('YYYY-MM-DD HH:mm:ss'));
    $("#startDate").val(moment().subtract('days', 1).format('YYYY-MM-DD HH:mm:ss'));

    $('span[name="dates"]').daterangepicker({}, function (start, end, label) {
        $("#startDate").val(start.format('YYYY-MM-DD HH:mm:ss'));
        $("#endDate").val(end.format('YYYY-MM-DD HH:mm:ss'));
        var start = $("#startDate").val();
        var end = $("#endDate").val();
        $.ajax({
            type: "Get",
            url: "/searchRecognitionByDate/" + start + "/" + end,
            success: function (data) {

                $('#page').empty();
                $('#page').html(data);
            }
        });
    });


    var availableTags = [];

    $.ajax({
        url: "/get-all-user",
        method: "get",

        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                availableTags.push(data[i].email);

            }
        }

    });

    $("#tagauto").autocomplete({
        source: availableTags
    });


    $(".alert-success").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-success").alert('close');
    });


    $(".alert-danger").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-danger").alert('close');
    });

    var table = $('#dataTable').DataTable({
        paging: false,
        scrollY: "500px",
        scrollX: false,
        scrollColapse: true,
        fixedColumns: true

    });

    $('body').on('click', '.delDiv', function (e) {
        var id = $(this).find('.resultId').val();
        var star = $(this).find('.resultStar').val();
        $(".recoId").val(id);
        $(".recoStar").val(star);
    });

    $('body').on('click', '.revokeRecognition', function (e) {
        var id = $(".recoId").val();
        var star = $(".recoStar").val();
        var comment = $("input[name='revoke']:checked").val();
        if (typeof comment === "undefined") {
            $("#selectResult").addClass("alert alert-danger");
            $("#selectResult").append("Select One Reason");
            $("#selectResult").fadeTo(2000, 500).slideUp(500, function () {
                $("#selectResult").empty();
                $('#selectResult').removeClass("alert alert-danger");

            });
        }
        else {
            deleteRecognition(id, star, comment);
        }
    });


    function deleteRecognition(id, star, comment) {

        $(' body').load("/delete-recognition/" + id + "/" + star + "/" + comment, function () {

            location.reload();
            $('.resultRevoke').append("Revocation Request submitted successfully , Mail has been sent");
            $('.resultRevoke').addClass("alert alert-success");
            $(".resultRevoke").fadeTo(7000, 500).slideUp(500, function () {
                $(".resultRevoke").empty();
                $('.resultRevoke').removeClass("alert alert-success");

            });

        });
    }

    $(".searchData").keyup(function () {
        var value = $(this).val().toLowerCase();
        $(" #torecord #record  ").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });


    $('body').on('change', 'select.allocate', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        var role = $(this).children("option:selected").val();
        var confirmBox = confirm("Are you sure you want to add the role");
        if (confirmBox == true) {
            $.ajax({

                url: "/addRole",
                type: "post",
                data: {email: email, role: role},
                success: function (response) {
                    $("#result").addClass("alert alert-success");
                    $("#result small").text("Role Allocated");
                    setTimeout(
                        function () {
                            location.reload();

                        }, 5000
                    );
                }
            });
        }
        else {
            location.reload();
        }
    });


    $('body').on('change', 'select.revokeRole', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        var role = $(this).children("option:selected").val();
        var confirmBox = confirm("Are you sure you want to revoke the role");
        if (confirmBox == true) {
            $.ajax({
                url: "/deleteRole",
                type: "post",
                data: {email: email, role: role},
                success: function (response) {
                    $("#result").addClass("alert alert-success");
                    $("#result small").text("Role Revoked");
                    setTimeout(
                        function () {
                            location.reload();

                        }, 3000
                    );
                }
            });
        }
        else {
            location.reload();
        }

    });


    $('body').on('change', '.points', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        var point = $(this).val();
        var confirmBox = confirm("Are you sure you want to change the points");
        if (confirmBox == true) {
            $.ajax({
                url: "/changePoints",
                type: "post",
                data: {email: email, point: point},
                success: function (response) {
                    $("#result").addClass("alert alert-success");
                    $('#result small').text("Points Changed");
                    setTimeout(
                        function () {
                            location.reload();
                        }, 3000
                    );

                }
            });
        }
        else {
            location.reload();
        }
    });

    $('input[name="isActive"]').click(function () {
        if ($('input[name="isActive"]:checked'))
            var e = $(this).parent().parent().find('#email').text();
        $.ajax({
            url: "/updateActive",
            type: "post",
            data: {email: e},
            success: function (response) {
                $("#result").addClass("alert alert-success");
                $("#result small").text("Active status changed ");
                setTimeout(
                    function () {
                        location.reload();
                    }, 3000
                );
            }
        });
    });


    $('body').on('change', 'input[name=goldStar]', function (e) {
        var email = $(this).parent().parent().find("#email").text();
        var goldStar = $(this).val();
        var confirmBox = confirm("Are you sure you want to change the gold badge");
        if (confirmBox == true) {
            if ($.isNumeric(goldStar)) {
                $.ajax({
                    url: "/changeGoldBadges",
                    type: "post",
                    data: {email: email, goldStar: goldStar},
                    success: function (response) {
                        $("#result").addClass("alert alert-success");
                        $('#result small').text("Gold Badge Changed");
                        setTimeout(
                            function () {
                                location.reload();
                            }, 3000
                        );

                    }
                });
            }
            else {
                $("#result").addClass("alert alert-danger");
                $('#result small').text("Gold Badge Can Not Be Changed");
                setTimeout(
                    function () {
                        location.reload();
                    }, 3000
                );
            }
        }
        else {
            location.reload();
        }
    });


    $('body').on('change', 'input[name=silverStar]', function (e) {
        var email = $(this).parent().parent().find("#email").text();
        var silverStar = $(this).val();
        var confirmBox = confirm("Are you sure you want to change the silver badge");
        if (confirmBox == true) {
            if ($.isNumeric(silverStar)) {
                $.ajax({
                    url: "/changeSilverBadges",
                    type: "post",
                    data: {email: email, silverStar: silverStar},
                    success: function (response) {
                        $("#result").addClass("alert alert-success");
                        $('#result small').text("Silver Badge Changed");
                        setTimeout(
                            function () {
                                location.reload();
                            }, 3000
                        );

                    }
                });
            }
            else {
                $("#result").addClass("alert alert-danger");
                $('#result small').text("Silver Badge Can Not Be Changed");
                setTimeout(
                    function () {
                        location.reload();
                    }, 3000
                );
            }
        }
        else {
            location.reload();
        }
    });


    $('body').on('change', 'input[name=bronzeStar]', function (e) {
        var email = $(this).parent().parent().find("#email").text();
        var bronzeStar = $(this).val();
        var confirmBox = confirm("Are you sure you want to change the bronze badge");
        if (confirmBox == true) {
            if ($.isNumeric(bronzeStar)) {
                $.ajax({
                    url: "/changeBronzeBadges",
                    type: "post",
                    data: {email: email, bronzeStar: bronzeStar},
                    success: function (response) {
                        $("#result").addClass("alert alert-success");
                        $('#result small').text("Bronze Badge Changed");
                        setTimeout(
                            function () {
                                location.reload();
                            }, 3000
                        );

                    }
                });
            } else {
                $("#result").addClass("alert alert-danger");
                $('#result small').text("Bronze Badge Can Not Be Changed");
                setTimeout(
                    function () {
                        location.reload();
                    }, 3000
                );
            }
        }
        else {
            location.reload();
        }
    });


});

