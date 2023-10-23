<%--
  Created by IntelliJ IDEA.
  User: nguyenhuugiang19072004
  Date: 10/21/23
  Time: 8:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>
</head>
<style>
    .headImg {
        margin-right: 20px;
    }

    .headRight {
        text-align: right;
        padding-right: 10px;
    }

    #boxLeft {
        background-color: #b4b4b4;
        height: 900px;
    }

    .textSpan {
        font-size: 30px;
        margin-left: 20px;
    }

    #contentTitle {
        margin: 36px;
    }

    .table {
        width: 100%;
        height: 3rem;
        margin-top: 2rem;
    }

    .group {
        width: 100%;
        height: 3rem;
    }
    .group_name{
        width: 80%;
        height: 3rem;
    }
    .group_add{
        width: 20%;
        height: 3rem;
        margin-left: 1rem;
        margin-top: 1rem;
    }
    .d_flex_main {
        width: 100%;
        height: 4rem;
    }

    .d_flex_left {
        width: 50%;
        height: auto;
        float: left;
    }

    .d_flex_right {
        width: 50%;
        height: auto;
        float: left;
        padding-left: 13rem;
    }

    .dropdown {
        display: inline-block;
        position: relative;
    }

    button{
        border:none;
        border-radius:5px;
        padding:15px 20px;
        font-size:22px;
        cursor:pointer;
    }

    button:hover{
        background-color:#ddd;
    }

    .dropdown-options {
        display: none;
        position: absolute;
        overflow: auto;
        background-color: #646464;
        border-radius:5px;
        box-shadow: 0px 10px 10px 0px rgba(255, 255, 255, 0.4);
    }

    .dropdown:hover .dropdown-options {
        display: block;
    }

    .dropdown-options a {
        float: left;
        display: block;
        color: #000000;
        padding: 5px;
        text-decoration: none;
        padding:20px 20px;
    }

    .dropdown-options a:hover {
        color: #0a0a23;
        background-color: #ddd;
        border-radius:5px;
    }

</style>
<body>
<div class="container-fluid">
    <div class="row">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <div class="headImg">
                    <img src="logo.png" width="80px"
                         height="60px">
                </div>
                <div class="collapse navbar-collapse">
                    <div class="dropdown">
                        <button>Nhóm</button>
                        <div class="dropdown-options">
                            <a href="#">?</a>
                            <a href="#">?</a>
                            <a href="#">?</a>
                        </div>
                    </div>
                </div>
                <div class="headRight">
                    <span class="group-text">
                    <div class="collapse navbar-collapse">
                      <i class="fa-solid fa-bell" style="color: #ffffff;font-size: 34px; margin-right: 10px"></i>
                         <div class="dropdown">
                        <button>Name</button>
                        <div class="dropdown-options">
                            <a href="#">Setting</a>
                            <a href="editPassword.jsp">Edit</a>
                            <a href="login.jsp">Logout</a>
                        </div>
                    </div>
                    </div>
                    </span>
                </div>
            </div>
        </nav>
    </div>
    <div class="row">
        <div class="col-2" id="boxLeft">
            <div class="table">
                <div class="d-flex align-items-center">
                    <i class="fa-solid fa-table" style="color: #000000; font-size: 30px;"></i>
                    <span class="textSpan">Bảng</span>
                </div>
            </div>
            <div class="group">
                <div class="d-flex  align-items-center">
                    <div class="group_name">
                        <i class="fa-solid fa-user-group" style="color: #000000; font-size: 30px"></i>
                        <span class="textSpan">Nhóm</span>
                    </div>
                    <div class="group_add">
                        <a href="/addGroup.jsp"><i class="fa-regular fa-square-plus" style="color: #000000;font-size: 30px"></i></a>
                    </div>
                </div>
            </div>
            <div class="d-flex  align-items-center">

            </div>
        </div>
        <div class="col-10">
            <div class="d-flex  align-items-center" id="contentTitle">
                <i class="fa-solid fa-user-group" style="color: #000000; font-size: 30px"></i>
                <span class="textSpan">Nhóm Của Bạn</span>
            </div>
            <div class="d-flex  align-items-center">

                <div class="d_flex_main">
                    <div class="d_flex_left">
                        <div class="project">
                            <h2>Dự Án QLCV</h2>
                        </div>
                    </div>
                    <div class="d_flex_right">
                        <div class="d-flex align-items-center ml-auto">
                            <a href="#" style="text-decoration: none;color: black">
                                <i class="fa-solid fa-table" style="color: #000000; font-size: 30px; "></i>
                                <span class="textSpan">Bảng</span>
                            </a>

                            <a href="#"  style="text-decoration: none;color: black">
                                <i class="fa-solid fa-user-group" style="color: #000000; font-size: 30px"></i>
                                <span class="textSpan">Thành Viên</span>
                            </a>

                            <a href="#"  style="text-decoration: none;color: black">
                                <i class="fa-solid fa-gear" style="color: #000000; font-size: 30px"></i>
                                <span class="textSpan">Cài Đặt</span>
                            </a>
                        </div>
                    </div>

                </div>

            </div>
            <div class="d-flex  align-items-center">
                <i class="fa-solid fa-user-group" style="color: #000000; font-size: 30px"></i>
                <span class="textSpan">Thành Viên Các Nhóm</span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
