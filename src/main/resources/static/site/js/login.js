$(function () {
    $.validator.setDefaults({
        submitHandler: function () {
            $.ajax({
                type: "POST",
                url: '/login',
                data: $('#loginForm').serialize(),
                dataType: "json",
                success: function(data){
                    $('.error-msg').empty();
                    if( data.status === 200 ){
                        location.href = '/index';
                    }else{
                        $('.error-msg').html(data.message);
                    }
                }
            });
        }
    });
    $('#loginForm').validate({
        rules: {
            name: {
                required: true
            },
            password: {
                required: true,
                minlength: 5
            },
            captcha: {
                required: true
            },
        },
        messages: {
            email: {
                required: "请输入用户名"
            },
            password: {
                required: "请输入密码"
            },
            captcha: {
                required: "请输入验证码"
            },
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-floating').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        }
    });
});