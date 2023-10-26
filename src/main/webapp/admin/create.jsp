<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nguyenhuugiang19072004
  Date: 10/25/23
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>

</head>
<body>
<form method="post">
    <fieldset>
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
        <caption>
            <h2 style="text-align: center">Add New User</h2>
        </caption>
        <div class="form-outline mb-4">
            <span class="input-group-text">
                <i class="fa-solid fa-envelope" style="color: #000000;font-size: 30px"></i>
                <input type="email" name="email" class="form-control form-control-lg"
                       placeholder="Your Email"
                       pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <i class="fa-solid fa-user" style="color: #000000; font-size: 30px"></i>
                <input type="text" name="name" class="form-control form-control-lg"
placeholder="Your Name" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <i class="fa-solid fa-phone" style="color: #000000; font-size: 30px"></i>
                <input type="text" name="phoneNumber" class="form-control form-control-lg"
                       placeholder="Your Phone" pattern="^\d{10}$" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <i class="fa-solid fa-lock" style="color: #000000; font-size: 30px"></i>
                <input type="password" name="password" class="form-control form-control-lg" placeholder="Password" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <i class="fa-solid fa-key" style="color: #000000; font-size: 30px"></i>
                <input type="password" name="confirmPassword"
                       class="form-control form-control-lg"
                       placeholder="Repeat Your Password"
                       pattern="^.{6,32}$" required/>
            </span>
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit"
                    class="btn btn-success btn-light btn-lg gradient-custom-4 text-body">
                Create
            </button>
        </div>
    </fieldset>
</form>
</body>
</html>