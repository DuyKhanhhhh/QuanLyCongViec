<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: titdzvl
  Date: 24/10/2023
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>

</head>
<body>
<a href="/homeUser">Back</a>
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

<table class="table table-striped">
    <span>
        <h2 style="margin-left: 2rem"><a href="/homeAdmin?user=create">
                        <i class="fa-solid fa-plus"></i>
                   </a></h2>

    </span>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Password</th>
        <th>Address</th>
        <th>Avatar</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="list" items="${listUser}">
        <tr>
            <td>${list.id}</td>
            <td>${list.email}</td>
            <td>${list.name}</td>
            <td>${list.phoneNumber}</td>
            <td>${list.password}</td>
            <td>${list.address}</td>
            <td><img src="${list.avatar}" alt="Avatar"></td>
            <td>
               <span class="input-group">
                   <a href="/homeAdmin?user=update&id=${list.id}" style="margin-right: 20px; text-decoration: none" ;>
                        <i class="fa-solid fa-pen-to-square"></i>
                   </a>
                   <a href="/homeAdmin?user=delete&id=${list.id}">
                        <i class="fa-solid fa-trash"></i>
                   </a>

               </span>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>