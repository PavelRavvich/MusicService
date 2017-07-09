$(function() {
    $(function() {
        // Initialize form validation on the registration form.
        // It has the name attribute "registration"
        $("form[name='add_data']").validate({
            // Specify validation rules
            rules: {
                username: {
                    required: true,
                    minlength: 3
                },

                login:  {
                    required: true,
                    minlength: 5
                },

                password:  {
                    required: true,
                    minlength: 6
                },

                email: {
                    required: true,
                },

                country: {
                    required: true
                },

                city: {
                    required: true
                }
            },

            messages: {
                username: "Please enter your name, min 3 char",
                login: "Please enter your login, min 5 char",
                password: "Please enter your password, min 6 char",
                country: "Please select country from list",
                city: "Please select city from list",
                email: "Please enter your email"
            },

            submitHandler: function(form) {
                form.submit();
            }
        });
    });

});
