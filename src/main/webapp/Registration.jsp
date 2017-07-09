<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Registration</title>

    <%--Bootstrap connection--%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

    <%--Jquery connection--%>
    <script src="https://cdn.jsdelivr.net/jquery/1.12.4/jquery.min.js" type="text/javascript"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>

    <%--JavaScript refer --%>
    <script><%@include file="/js/reg_form_validation.js" %></script>
    <script><%@include file="/js/location_refer.js" %></script>

    <%--Refer to css styles--%>
    <style type="text/css">
        <%@include file="/css/reg_style.css"%>
    </style>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">

            <form class="form-horizontal" role="form" id="commentForm"
                  method="post" action="registration" name="add_data">

                <span class="heading">Registration</span>

                <div class="form-group">
                    <input class="form-control" type="text" name="username" id="username" placeholder="Username" />
                    <i class="fa fa-user"></i>
                </div>


                <div class="form-group">
                    <input class="form-control" type="text" name="login" id="login" placeholder="Login" />
                    <i class="fa fa-user"></i>
                </div>


                <div class="form-group">
                    <input class="form-control" type="password" name="password" id="password" placeholder="Password" />
                    <i class="fa fa-lock"></i>
                </div>


                <div class="form-group">
                    <input class="form-control" type="email" name="email" id="email" placeholder="Email" />
                    <i class="fa fa-lock"></i>
                </div>


                <div class="form-group">
                    <label class="control-label" for="country"></label><br />
                    <select class="form-control" id="country" name="country">
                        <option value="">Select Country</option>
                        <option value="USA">USA</option>
                        <option value="Israel">Israel</option>
                    </select>
                </div>


                <div class="form-group">
                    <label class="control-label" for="city"></label><br />
                    <select  class="form-control" id="city" name="city">
                        <option value="">Select City</option>
                    </select>
                </div>
                <br />

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-success">Register</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
