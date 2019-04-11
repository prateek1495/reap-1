$(function () {

    $("#endDate").val(moment().format('YYYY-MM-DD HH:mm:ss'));
    $("#startDate").val(moment().subtract('days',1).format('YYYY-MM-DD HH:mm:ss'));

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
            console.log(data);
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

    var table = $('#example').DataTable({
        paging: true,
        fixedColumns: true

    });

    $('body').on('click', '.delDiv', function (e) {
        var id = $(this).find('.resultId').val();
        console.log(id);
        var star = $(this).find('.resultStar').val();
        console.log(star);

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
            $('.resultRevoke').append("Recognition Revoked");
            $('.resultRevoke').addClass("alert alert-success");
            $(".resultRevoke").fadeTo(2000, 500).slideUp(500, function () {
                $(".resultRevoke").empty();
                $('.resultRevoke').removeClass("alert alert-success");

            });

        });
    }


    $('.csvBtn').on("click", function () {
        window.open("/download-csv");
    });

    $(".searchData").keyup(function () {
        var value = $(this).val().toLowerCase();
        $(" #torecord #record  ").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    $('body').on('change', 'select.allocate', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        console.log(email);
        var role = $(this).children("option:selected").val();
        console.log(role);
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


    });

    $('body').on('change', 'select.revokeRole', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        console.log(email);
        var role = $(this).children("option:selected").val();
        console.log(role);
        $.ajax({
            url: "/deleteRole",
            type: "post",
            data: {email: email, role: role},
            success: function (response) {
                console.log("hi");
                $("#result").addClass("alert alert-success");
                $("#result small").text("Role Revoked");
                setTimeout(
                    function () {
                        location.reload();

                    }, 5000
                );
            }
        });

    });

    $('body').on('change', '.points', function (e) {
        var email = $(this).parent().parent().parent().find("#email").text();
        var point = $(this).val();
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
                    }, 5000
                );

            }
        });
    });


    $(".checkBox").change(function () {
        var checked = $(this).is(':checked');
        var userId = $(this).val();
        if (!checked) {
            $.ajax({

                url: "/deactivate-user",
                data: {"userId": userId},
                success: (function () {
                    $('#result').append("User Deactivated SuccessFully");
                    $('#result').addClass("alert alert-success");

                    $("#result").fadeTo(2000, 500).slideUp(500, function () {
                        $("#result").empty();
                        $('#result').removeClass("alert alert-success");
                    });
                })

            });
        } else {
            $.ajax({

                url: "/activate-user",
                data: {"userId": userId},
                success: (function () {
                    $('#result').append("User Activated SuccessFully");
                    $('#result').addClass("alert alert-success");

                    $("#result").fadeTo(2000, 500).slideUp(500, function () {
                        $("#result").empty();
                        $('#result').removeClass("alert alert-success");
                    });
                })

            });

        }
    });


});

