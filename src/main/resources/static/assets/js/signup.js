$(function () {
    $(".alert-danger").fadeTo(2000, 500).slideUp(100, function () {
        $(".alert-danger").alert('close');
    });
});

$(function () {
    $(".alert-success").fadeTo(2000, 500).slideUp(100, function () {
        $(".alert-success").alert('close');
    });
});

$("#register-submit").click(function (event) {
    if ($("#password").val() != $("#confirmpassword").val()) {
        alert("Password and Confirm Password does not match");
        event.preventDefault();
        $("#passworderror").show;
        $("#password").attr('title', 'Your Password is wrong');

    }
});