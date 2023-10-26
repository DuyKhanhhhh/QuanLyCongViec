<%--
  Created by IntelliJ IDEA.
  User: titdzvl
  Date: 23/10/2023
  Time: 14:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/892d14366e.js" crossorigin="anonymous"></script>
<body>
<section class="vh-100 bg-image"
         style="background-image: url('https://static-gcdn.basecdn.net/landing/base.vn/image/v2/bg.svg');">
    <div class="mask d-flex align-items-center h-100 gradient-custom-3">
        <div class="container h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-9 col-lg-7 col-xl-6">
                    <div class="card" style="border-radius: 25px;">
                        <div class="card-body p-5">
                            <form method="post" action="homeUser?action=addTable">
                                <h2 class="text-center text-center mb-5">Create New Table </h2>
                                <div class="form-outline mb-4">
                                    <span class="input-group-text">
                                    <input type="text" name="name" class="form-control form-control-lg"
                                           placeholder="Enter Your Name" required/>
                                    </span>
                                </div>

                                <div class="form-outline mb-4">
                                <span class="input-group-text">
                                    <select class="form-select" aria-label="Default select example" name="group">
                                        <option selected>Open Group</option>
                                        <option>Three</option>
                                    </select>
                                </span>
                                </div>

                                <div class="form-outline mb-4">
                                 <span class="input-group-text">
                                    <select class="form-select" aria-label="Default select example" name="permission">
                                    <option selected>Open Permission</option>
                                    <option>Public</option>
                                    <option>Private</option>
                                    </select>
                                 </span>
                                </div>


                                <div class="d-flex justify-content-center">
                                    <button type="submit"
                                            class="btn btn-success btn-light btn-lg gradient-custom-4 text-body">
                                        Submit
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
