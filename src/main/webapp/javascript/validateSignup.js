$(document).ready(function() {
    $('#reg_form').bootstrapValidator({
        // To use feedback icons, ensure that you use Bootstrap v3.1.0 or later
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid:'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userName: {
                validators: {
                    stringLength: {
                        min:2,
                    },
                    notEmpty: {
                        message: 'Please supply a user name'
                    }
                }
            },
            email: {
                validators: {
                    notEmpty: {
                        message: 'Please supply your email address'
                    },
                    emailAddress: {
                        message: 'Please supply a valid email address'
                    }
                }
            },
            password: {
                validators: {
                    identical: {
                        field: 'confirmPassword',
                        message: 'Confirm your password below - type same password please'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    identical: {
                        field: 'password',
                        message: 'The password does not match'
                    }
                }
            },
        }
    })

        .on('success.form.bv', function(e) {
            $('#success_message').slideDown({opacity: "show" }, "slow") //Do something...
            $('#reg_form').data('bootstrapValidator').resetForm();

            //Prevent form submission
            e.preventDefault();

            //Get the form instance
            var $form = $(e.target);

            //Get the BootstrapValidator instance
            var bv = $form.data("bootstrapValidator");

            //Use Ajax to submit form data
            $.post($form.attr('action'), $form.serialize(), function(result) {
                console.log(result);
            }, 'json');
        });
});