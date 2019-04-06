'use strict';

var numberOfItems = $('#page .list-group').length; // Get total number of the items that should be paginated
var limitPerPage = 4; // Limit of items per each page
$('#page .list-group:gt(' + (limitPerPage - 1) + ')').hide(); // Hide all items over page limits (e.g., 5th item, 6th item, etc.)
var totalPages = Math.round(numberOfItems / limitPerPage); // Get number of pages
$(".pagination").append("<li class='current-page active'><a href='javascript:void(0)'>" + 1 + "</a></li>"); // Add first page marker

// Loop to insert page number for each sets of items equal to page limit (e.g., limit of 4 with 20 total items = insert 5 pages)
for (var i = 2; i <= totalPages; i++) {
    $(".pagination").append("<li class='current-page'><a href='javascript:void(0)'>" + i + "</a></li>"); // Insert page number into pagination tabs
}

// Add next button after all the page numbers
$(".pagination").append("<li id='next-page'><a href='javascript:void(0)' aria-label=Next><span aria-hidden=true>&raquo;</span></a></li>");

// Function that displays new items based on page number that was clicked
$(".pagination li.current-page").on("click", function() {
    // Check if page number that was clicked on is the current page that is being displayed
    if ($(this).hasClass('active')) {
        return false; // Return false (i.e., nothing to do, since user clicked on the page number that is already being displayed)
    } else {
        var currentPage = $(this).index(); // Get the current page number
        $(".pagination li").removeClass('active'); // Remove the 'active' class status from the page that is currently being displayed
        $(this).addClass('active'); // Add the 'active' class status to the page that was clicked on
        $("#page .list-group").hide(); // Hide all items in loop, this case, all the list groups
        var grandTotal = limitPerPage * currentPage; // Get the total number of items up to the page number that was clicked on

        // Loop through total items, selecting a new set of items based on page number
        for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
            $("#page .list-group:eq(" + i + ")").show(); // Show items from the new page that was selected
        }
    }

});

// Function to navigate to the next page when users click on the next-page id (next page button)
$("#next-page").on("click", function() {
    var currentPage = $(".pagination li.active").index(); // Identify the current active page
    // Check to make sure that navigating to the next page will not exceed the total number of pages
    if (currentPage === totalPages) {
        return false; // Return false (i.e., cannot navigate any further, since it would exceed the maximum number of pages)
    } else {
        currentPage++; // Increment the page by one
        $(".pagination li").removeClass('active'); // Remove the 'active' class status from the current page
        $("#page .list-group").hide(); // Hide all items in the pagination loop
        var grandTotal = limitPerPage * currentPage; // Get the total number of items up to the page that was selected

        // Loop through total items, selecting a new set of items based on page number
        for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
            $("#page .list-group:eq(" + i + ")").show(); // Show items from the new page that was selected
        }

        $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active'); // Make new page number the 'active' page
    }
});

// Function to navigate to the previous page when users click on the previous-page id (previous page button)
$("#previous-page").on("click", function() {
    var currentPage = $(".pagination li.active").index(); // Identify the current active page
    // Check to make sure that users is not on page 1 and attempting to navigating to a previous page
    if (currentPage === 1) {
        return false; // Return false (i.e., cannot navigate to a previous page because the current page is page 1)
    } else {
        currentPage--; // Decrement page by one
        $(".pagination li").removeClass('active'); // Remove the 'activate' status class from the previous active page number
        $("#page .list-group").hide(); // Hide all items in the pagination loop
        var grandTotal = limitPerPage * currentPage; // Get the total number of items up to the page that was selected

        // Loop through total items, selecting a new set of items based on page number
        for (var i = grandTotal - limitPerPage; i < grandTotal; i++) {
            $("#page .list-group:eq(" + i + ")").show(); // Show items from the new page that was selected
        }

        $(".pagination li.current-page:eq(" + (currentPage - 1) + ")").addClass('active'); // Make new page number the 'active' page
    }
});































































































/*
$(function () {


    $(".alert-success").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-success").alert('close');
    });


    $(".alert-danger").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-danger").alert('close');
    });


    var availableTags = [];

    var table = $('#example').DataTable({
        scrollY: "300px",
        scrollX: true,
        scrollCollapse: true,
        paging: true,
        fixedColumns: true,
        displayLength: 2
    });
    $.ajax({
        type: "get",
        url: "/current-user",
        contentType: "application/json; charset=utf-8",
        success: (function (response) {
            if (response.status === true) {
                populateUserModal(response.data);
            }
        })
    });

    $.ajax({
        type: "get",
        url: "/get-all-user",
        contentType: "application/json; charset=utf-8",
        success: (function (response) {
            if (response.status === true) {
                populateUserAutoComplete(response.data);
            }
        })
    });


    function populateUserAutoComplete(data) {
        console.log(data);
        for (var i = 0; i < data.length; i++)
            availableTags.push(data[i].email);
        $('.tags').autocomplete({
            source: availableTags
        });
        $('.modalInput').autocomplete({
            source: availableTags,
            appendTo: "#modalB"
        });
    }


    function populateUserModal(data) {

        $('.userName').text(data.firstName);
        $('.userEmail').text(data.email);

    }

    $('body').on("click", ".delDiv", function (e) {
        var id = $(this).find('.resultId').val();
        var star = $(this).find('.resultStar').val();
        console.log(id)
        console.log(star)

        $(".recoId").val(id);
        $(".recoStar").val(star);

        // deleteRecognition(id,star);
    });

    $('body').on("click", ".revokeRecognition", function (e) {

        var id = $(".recoId").val();
        var star = $(".recoStar").val();
        var comment = $(".revoke").val();

        deleteRecognition(id, star, comment);

    });

    function deleteRecognition(id, star,comment) {


        $.ajax({
            type: "get",
            url: "/delete-recognition/" + id + "/" + star + "/" +comment,
            success: (function () {
                $('#resultRevoke').append("Recognition Revoked");
                $('#resultRevoke').addClass("alert alert-success");
                $("#resultRevoke").fadeTo(2000, 500).slideUp(500, function () {
                    $("#resultRevoke").empty();
                    $('#resultRevoke').removeClass("alert alert-success");
                    location.reload();
                });
            }),
            error: (function () {
                console.log("error")
            })
        });


    }

    $('body').on("click", ".GetUserDetail", function (e) {
        var email = $('#userEmail').val();
        $.ajax({
            url: "/get-user-detail",
            data: {"email": email},
            success: (function (response) {
                $('.roleAssignedTable').empty();
                $('#nameUser').empty();
                $('#emailUser').empty();
                $('.roleAssignedTable').append("<tr></tr>");
                $('.roleNew').empty();
                populateRoleModal(response)
            }),
            error: (function () {
                console.log("error")
            })
        })
    });

    function populateRoleModal(data) {

        $('.bodyRoleUser').show();
        $('#nameUser').empty();
        $('#emailUser').empty();

        $('#nameUser').text(data.user.firstName);
        $('#emailUser').text(data.user.email);

        $('.roleAssignedTable').children().remove();
        $('.roleAssignedTable').empty();
        $('.roleAssignedTable').append("<tr></tr>");
        $('.roleNew').empty();

        for (var i = 0; i < data.user.roles.length; i++) {
            var
                tr = $("<tr></tr>"),
                td1 = $("<td style='padding: 8px'></td>"),
                td2 = $("<td style='padding: 8px;'></td>"),
                button = $("<button class='btn btn-danger' style='height: auto'></button>");
            td1.append(data.user.roles[i].name);
            td2.append(button);
            button.append("Delete Role");
            button.on("click", {
                userId: data.user.id,
                roleId: data.user.roles[i].id
            }, function (e) {
                deleteRole(e.data.roleId, e.data.userId, $(this).parent().parent());
            });
            tr.append(td1).append(td2);
            $('#roleAssignedTable').append(tr);
        }

        for (var j = 0; j < data.allRoles.length; j++) {
            console.log(data.allRoles[j].name);
            var
                chips = $("<button class='btn btn-info' style='border-radius: 40px;color:white;padding: 20px;" +
                    "width: auto;height: auto;font-size: 12px;margin-right: 14px;text-align: center'>" +
                    "</button>");
            chips.append(data.allRoles[j].name);
            chips.on("click", {
                userId: data.user.id,
                roleId: data.allRoles[j].id
            }, function (e) {
                addNewRole(e.data.roleId, e.data.userId);
            });
            $('.roleNew').append(chips);

        }

    }

    function addNewRole(roleId, userId) {

        $.ajax({
            url: "/add-new-role",
            data: {"roleId": roleId, "userId": userId},
            success: function (response) {
                populateRoleModal(response)
            }
        })
    }

    function deleteRole(roleId, userId, row) {

        $.ajax({
            url: "/delete-role-user",
            data: {"roleId": roleId, "userId": userId},
            success: function (response) {
                row.remove();
                populateRoleModal(response)
            }
        })
    }

    $('body').on("click", ".roleModalClose", function () {

        location.reload();

    });

    $('body').on("change", ".checkBox", function () {
        var checked = $(this).is(':checked');
        var userId = $(this).val();
        console.log(checked);
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
    })


});










































$(function () {

    $('.loading').hide();

    function startLoader() {
        $('.loading').show();
    }

    function stopLoader() {
        $('.loading').hide();
    }

    $(".alert-success").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-success").alert('close');
    });


    $(".alert-danger").fadeTo(2000, 500).slideUp(500, function () {
        $(".alert-danger").alert('close');
    });

    $('.dateRange').daterangepicker({
        opens: 'left'
    }, function (start, end, label) {

        filterDataByDate(start, end);


        // console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
    });

    function filterDataByDate(start, end) {
        var startDate = start.format('YYYY-MM-DD HH:mm:ss');
        var endDate = end.format('YYYY-MM-DD HH:mm:ss');

        $('.recordInfo').load("/list-badges-date-filter",{"startDate":startDate,"endDate":endDate})


    }


    var availableTags = [];

    var table = $('#example').DataTable({
        scrollY: "300px",
        scrollX: true,
        scrollCollapse: true,
        paging: true,
        fixedColumns: true,
        displayLength: 2
    });

    $.ajax({
        type: "get",
        url: "/current-user",
        contentType: "application/json; charset=utf-8",
        success: (function (response) {
            if (response.status === true) {
                populateUserModal(response.data);
            }
        })
    });

    $('.recordInfo').load("/list-badges");



    $.ajax({
        type: "get",
        url: "/get-all-user",
        contentType: "application/json; charset=utf-8",
        success: (function (response) {
            // stopLoader();
            if (response.status === true) {
                populateUserAutoComplete(response.data);
            }
        })


    });





    function populateUserAutoComplete(data) {

        for (var i = 0; i < data.length; i++)
            availableTags.push(data[i].email);
        $('.tags').autocomplete({
            source: availableTags
        });
        $('.modalInput').autocomplete({
            source: availableTags,
            appendTo: "#modalB"
        });
    }


    function populateUserModal(data) {

        $('.userName').text(data.firstName);
        $('.userEmail').text(data.email);

    }

    $('body').on("click", ".delDiv", function (e) {

        console.log("in");

        var id = $(this).find('.resultId').val();
        console.log(id);
        var star = $(this).find('.resultStar').val();
        console.log(star);

        $(".recoId").val(id);
        $(".recoStar").val(star);

        // deleteRecognition(id,star);
    });

    $('body').on("click", ".revokeRecognition", function (e) {

        var id = $(".recoId").val();
        var star = $(".recoStar").val();
        var comment = $(".revoke").val();

        deleteRecognition(id, star, comment);

    });


    $('body').on("click",".pageBtn", function () {



        $('.recordInfo').load("/list-badges", {"size": 5, "page": $(this).text()});

    });





    function deleteRecognition(id, star, comment) {

        startLoader();

        $('.recordInfo').load("/delete-recognition/" + id + "/" + star + "/" + comment, function () {
            stopLoader();
            $('.recordInfo').load("/list-badges");
            $('.resultRevoke').append("Recognition Revoked");
            $('.resultRevoke').addClass("alert alert-success");
            $(".resultRevoke").fadeTo(2000, 500).slideUp(500, function () {
                $(".resultRevoke").empty();
                $('.resultRevoke').removeClass("alert alert-success");

            });
        })



    }

    $('body').on("click", ".GetUserDetail", function (e) {
        var email = $('#userEmail').val();
        $.ajax({
            url: "/get-user-detail",
            data: {"email": email},
            success: (function (response) {
                $('.roleAssignedTable').empty();
                $('#nameUser').empty();
                $('#emailUser').empty();
                $('.roleAssignedTable').append("<tr></tr>");
                $('.roleNew').empty();
                populateRoleModal(response)
            }),
            error: (function () {
                console.log("error")
            })
        })
    });

    function populateRoleModal(data) {

        $('.bodyRoleUser').show();
        $('#nameUser').empty();
        $('#emailUser').empty();

        $('#nameUser').text(data.user.firstName);
        $('#emailUser').text(data.user.email);

        $('.roleAssignedTable').children().remove();
        $('.roleAssignedTable').empty();
        $('.roleAssignedTable').append("<tr></tr>");
        $('.roleNew').empty();

        for (var i = 0; i < data.user.roles.length; i++) {
            var
                tr = $("<tr></tr>"),
                td1 = $("<td style='padding: 8px'></td>"),
                td2 = $("<td style='padding: 8px;'></td>"),
                button = $("<button class='btn btn-danger' style='height: auto'></button>");
            td1.append(data.user.roles[i].name);
            td2.append(button);
            button.append("Delete Role");
            button.on("click", {
                userId: data.user.id,
                roleId: data.user.roles[i].id
            }, function (e) {
                deleteRole(e.data.roleId, e.data.userId, $(this).parent().parent());
            });
            tr.append(td1).append(td2);
            $('#roleAssignedTable').append(tr);
        }

        for (var j = 0; j < data.allRoles.length; j++) {
            console.log(data.allRoles[j].name);
            var
                chips = $("<button class='btn btn-info' style='border-radius: 40px;color:white;padding: 20px;" +
                    "width: auto;height: auto;font-size: 12px;margin-right: 14px;text-align: center'>" +
                    "</button>");
            chips.append(data.allRoles[j].name);
            chips.on("click", {
                userId: data.user.id,
                roleId: data.allRoles[j].id
            }, function (e) {
                addNewRole(e.data.roleId, e.data.userId);
            });
            $('.roleNew').append(chips);

        }

    }

    function addNewRole(roleId, userId) {

        $.ajax({
            url: "/add-new-role",
            data: {"roleId": roleId, "userId": userId},
            success: function (response) {
                populateRoleModal(response)
            }
        })
    }

    function deleteRole(roleId, userId, row) {

        $.ajax({
            url: "/delete-role-user",
            data: {"roleId": roleId, "userId": userId},
            success: function (response) {
                row.remove();
                populateRoleModal(response)
            }
        })
    }

    $('body').on("click", ".roleModalClose", function () {

        location.reload();

    });

    $('body').on("change", ".checkBox", function () {
        var checked = $(this).is(':checked');
        var userId = $(this).val();
        console.log(checked);
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


    $('body').on('keyup', '.searchData', function () {

        $('.recordInfo').load("/list-badges",{"name":$(this).val()});

    });


});
*/
