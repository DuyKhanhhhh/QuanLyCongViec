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
            <h2 style="text-align: center">Edit Information User</h2>
        </caption>
        <input type="hidden" name="idUd" value="${user.id}">
        <div class="form-outline mb-4">
            <span class="input-group-text">
                <input type="text" name="nameUd" value="${user.name}" class="form-control form-control-lg"
                       placeholder="Your Name" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <input type="text" name="phoneNumberUd" value="${user.phoneNumber}" class="form-control form-control-lg"
                       placeholder="Phone Number" required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <input type="text" name="addressUd" value="${user.address}" class="form-control form-control-lg" placeholder="Address"required/>
            </span>
        </div>

        <div class="form-outline mb-4">
            <span class="input-group-text">
                <input type="text" name="avatarUd" value="${user.avatar}" class="form-control form-control-lg" placeholder="Avatar" required/>
            </span>
        </div>

        <div class="d-flex justify-content-center">
            <button type="submit"
                    class="btn btn-success btn-light btn-lg gradient-custom-4 text-body">
               Update
            </button>
        </div>
    </fieldset>
</form>
</body>
</html>
