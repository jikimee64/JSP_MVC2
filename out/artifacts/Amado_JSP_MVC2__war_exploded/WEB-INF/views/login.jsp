<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Amado - Login</title>

    <!-- Custom fonts for this template-->
    <link href="static/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="static/css/sb-admin.css" rel="stylesheet">

</head>

<body class="bg-dark">

<c:if test="${ searchPwResult == 1}">
    <script>
        alert("비밀번호는 ${dto.userPassword} 입니다.");
    </script>
</c:if>

<c:if test="${sessionID != null }">
    <script>
        alert("이미 로그인 중입니다.");
        location.href = "main.do";
    </script>
</c:if>

<c:if test="${ loginResult == -1 || loginResult == 0}">
    <script>
        $('#messageModal').modal("show");
    </script>
</c:if>


<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Login</div>
        <div class="card-body">
            <form action="login.do" method="post">
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="text" name="userID" id="userID" class="form-control" placeholder="userID"
                               required="required" autofocus="autofocus">
                        <label for="userID">ID</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-label-group">
                        <input type="password" name="userPassword" id="userPassword" class="form-control"
                               placeholder="Password" required="required">
                        <label for="userPassword">Password</label>
                    </div>
                </div>
                <input type="submit" class="btn btn-primary btn-block" value="Register" id="login"/>
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="join.do">Register an Account</a>
                <a class="d-block small" href="searchPw.do">Forgot Password?</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="static/vendor/jquery/jquery.min.js"></script>
<script src="static/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="static/vendor/jquery-easing/jquery.easing.min.js"></script>

<%@include file="includes/model.jsp" %>
