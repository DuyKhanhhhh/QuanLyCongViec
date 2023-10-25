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
    <title>Update</title>
</head>
<body>
<form method="post">
    <fieldset>
        <legend>Edit Product</legend>
        <table>
            <input type="hidden" name="idUd" value="${user.id}">
            <tr>
                <td>Name:</td>
                <td><input type="text" name="nameUd" value="${user.name}"></td>
            </tr>
            <tr>
                <td>Email :</td>
                <td><input type="text" name="emailUd" value="${user.email}"></td>
            </tr>
            <tr>
                <td>Phone Number :</td>
                <td><input pattern="^\d{10}$"  type="text" name="phoneNumberUd" value="${user.phoneNumber}"></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input pattern="^.{6,32}$" type="text" name="passwordUd" value="${user.password}"></td>
            </tr>
            <tr>
                <td>Address :</td>
                <td><input type="text" name="addressUd" value="${user.address}"></td>
            </tr>
            <tr>
                <td>Avatar:</td>
                <td><input type="text" name="addressUd" value="${user.avatar}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>