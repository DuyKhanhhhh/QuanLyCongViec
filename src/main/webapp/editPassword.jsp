    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenhuugiang19072004
  Date: 10/19/23
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>
<style>
    input {
        margin-left: 9px;
    }
</style>
<body>
<section class="vh-100 bg-image"
         style="background-image: url('https://static-gcdn.basecdn.net/landing/base.vn/image/v2/bg.svg');">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 25px;">
                        <div class="card-body p-5">
                            <h2 class="text-center text-center mb-5">Change Password</h2>
                            <c:if test="${message != null}">
                                <div class="alert alert-success" role="alert">
                                        ${message}
                                </div>
                            </c:if>
                            <script>
                                setTimeout(function () {
                                    document.querySelector(".alert").remove();
                                }, 3000);
                            </script>
                            <form action="/updatePassword?login=updatePassword" method="post">


                                    <input type="hidden" name="email" value="${user.email}" class="form-control form-control-lg"
                                           placeholder="Your Email " required/>
                                <div class="form-outline mb-4">
                                      <span class="input-group-text">
                                          <i class="fa-solid fa-lock" style="color: #000000; font-size: 30px"></i>
                                          <input type="password" name="password" class="form-control form-control-lg"
                                                 placeholder="Password"
                                                 pattern="^.{6,}$" required/>
                                      </span>
                                </div>

                                <div class="form-outline mb-4">
                                      <span class="input-group-text">
                                          <i class="fa-solid fa-key" style="color: #000000; font-size: 30px"></i>
                                          <input type="password" name="newPassword" class="form-control form-control-lg"
                                                 placeholder="New Password"
                                                 pattern="^.{6,}$" required/>
                                      </span>
                                </div>

                                <div class="form-outline mb-4">
                                     <span class="input-group-text">
                                         <i class="fa-solid fa-key" style="color: #000000; font-size: 30px"></i>
                                         <input type="password" name="confirmPassword"
                                                class="form-control form-control-lg"
                                                placeholder="Repeat Your New Password"
                                                required/>
                                    </span>
                                </div>

                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            class="btn btn-success btn-light btn-lg gradient-custom-4 text-body">
                                        Update
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>