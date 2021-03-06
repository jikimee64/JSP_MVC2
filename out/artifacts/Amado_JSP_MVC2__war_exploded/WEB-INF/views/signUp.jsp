<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SB Admin - Register</title>
    <!-- Custom fonts for this template-->
    <link href="static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="static/css/sb-admin.css" rel="stylesheet">
    <script type="text/javascript">
        function registerCheckFunction() {
            var userID = $('#userID').val();
            $.ajax({
                type: 'POST',
                url: './joinCheck',
                data: {userID: userID},
                success: function (result) {
                    if (result == 1) {
                        $('#checkMessage').html('사용할 수 있는 아이디입니다.');
                    } else {
                        $('#checkMessage').html('사용할 수 없는 아이디입니다.');
                    }
                    $('#checkModal').modal("show");
                }
            })
        }
    </script>

</head>

<body class="bg-dark">

<!-- Bootstrap core JavaScript-->
<script src="static/vendor/jquery/jquery.min.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="static/vendor/jquery-easing/jquery.easing.min.js"></script>


<c:if test="${ joinResult == 0}">
    <script>
        $('#messageModal').modal("show");
    </script>
</c:if>

<c:if test="${ pwRole == 0}">
    <script>
        $('#messageModal').modal("show");
    </script>
</c:if>

<c:if test="${ emailRole == 0}">
    <script>
        $('#messageModal').modal("show");
    </script>
</c:if>

<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Register an Account</div>
        <div class="card-body">
            <form action="join.do" method="post">

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" name="userID" id="userID" class="form-control" placeholder="userID"
                               required="required" autofocus="autofocus">
                        <label for="userID">ID</label>
                    </div>
                    <button class="btn btn-info btn-block" onclick="registerCheckFunction();" type="button">아이디 중복체크
                    </button>
                </div>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" name="userPassword" id="userPassword" class="form-control"
                               placeholder="Password"
                               required="required">
                        <label for="userPassword">Password</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" name="userName" id="userName" class="form-control" placeholder="Full name"
                               required="required">
                        <label for="userName">Full Name</label>
                    </div>
                </div>

                <div class="form-group">
                    <div class="form-label-group">
                        <input type="email" name="userEmail" id="userEmail" class="form-control"
                               placeholder="Email address"
                               required="required">
                        <label for="userEmail">Email address</label>
                    </div>
                </div>

                <input type="submit" class="btn btn-primary btn-block" value="Register" id="signUp"/>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="login.do">Login Page</a>
                <a class="d-block small" href="searchPw.do">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>

<%@include file="includes/model.jsp" %>
</body>
</html>