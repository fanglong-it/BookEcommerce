<%-- 
    Document   : register
    Created on : Jul 7, 2021, 8:17:51 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
         <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
        <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
        <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
        <link rel="stylesheet" type="text/css" href="css/util.css">
        <link rel="stylesheet" type="text/css" href="css/main.css">
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Homepage - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
         <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="DispatchServlet?btnAction=">BookStore</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="SearchServlet?searchValue=&Category=All">All Book</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                    <c:if test="${not empty sessionScope.CATE}">
                                        <c:forEach  var="cate" items="${sessionScope.CATE}">
                                        <li><a class="dropdown-item" href="SearchCateServlet?CateId=${cate.categoryId}">${cate.categoryName}</a></li>
                                        </c:forEach>
                                    </c:if>
                            </ul>
                        </li>
                    </ul>

                    <%-->Search button<--%>
                    <form action="DispatchServlet" class="d-flex me-auto" >
                        <select name="Category" class="dropdown btn btn-outline-dark" aria-labelledby="navbarDropdown">
                            <option value="Name" selected="">Name</option>
                            <option value="Price">Price</option>
                        </select>

                        <input class="btn btn-outline-dark" type="text" name="searchValue" value="" placeholder="Search value here...."/>
                        <button class="btn btn-outline-dark" type="submit" value="Search" name="btnAction">
                            Search
                        </button>
                    </form>


                    <%-->Login Logout Cart<--%>
                    <form class="d-flex" action="DispatchServlet">

                        <button class="btn" type="submit" name="btnAction" value="Login">Login</button>
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>

                    </form>
                </div>
            </div>
        </nav>

        <%-->Slide of Styles Book<--%>
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">World Of Knowledge</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop home page template</p>
                    <p class="lead fw-normal text-white-50 mb-0">Find the suitable book for your own!</p>
                </div>
            </div>
        </header>



        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">

                <%-->Start of Login<--%>
                    <div class="limiter">
                        <div class="container-login100">
                            <div class="wrap-login100 p-b-160 p-t-50">
                                <form class="login100-form validate-form" action="DispatchServlet">
                                    <span class="login100-form-title p-b-43">
                                        Register Account
                                    </span>

                                    <div class="wrap-input100 rs1 validate-input" data-validate = "Username is required">
                                        <input class="input100" type="text" name="username">
                                        <span class="label-input100">Username</span>
                                    </div>
                                    
                                    <div class="wrap-input100 rs2 validate-input" data-validate = "Fullname is required">
                                        <input class="input100" type="text" name="fullname">
                                        <span class="label-input100">Full name</span>
                                    </div>


                                    <div class="wrap-input100  validate-input" data-validate="Password is required">
                                        <input class="input100" type="password" name="password">
                                        <span class="label-input100">Password</span>
                                    </div>
                                    
                                    <div class="wrap-input100 validate-input" data-validate="Repassword is required">
                                        <input class="input100" type="password" name="Repassword">
                                        <span class="label-input100">Re-Password</span>
                                    </div>
                                    
                                    <div class="wrap-input100 validate-input" data-validate = "dob is required">
                                        <input class="input100" type="date" name="dob" max="2016-12-31">
                                    </div>
                                    
                                    <div class="wrap-input100 validate-input">
                                        <span class="label-input100"></span>
                                    </div>

                                    <div class="container-login100-form-btn">
                                        <button class="login100-form-btn" type="Submit" name="btnAction" value="RegisterServlet">
                                            Register
                                        </button>
                                    </div>
                                    
                                    <c:if test="${not empty sessionScope.ERROR_REGISTER}">
                                    <div class="text-center w-full p-t-23">
                                        <p style="color: red"> ${sessionScope.ERROR_REGISTER}</p>
                                    </div>
                                    </c:if>

                                    <div class="text-center w-full p-t-23">
                                        <a href="DispatchServlet?btnAction=LoginPage" class="txt1">
                                            Already have account?
                                        </a>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>


                <script src="vendor/jquery/jquery-3.2.1.min.js"></script>
                <script src="vendor/animsition/js/animsition.min.js"></script>
                <script src="vendor/bootstrap/js/popper.js"></script>
                <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
                <script src="vendor/select2/select2.min.js"></script>
                <script src="vendor/daterangepicker/moment.min.js"></script>
                <script src="vendor/daterangepicker/daterangepicker.js"></script>
                <script src="vendor/countdowntime/countdowntime.js"></script>
                <script src="js/main.js"></script>

            </div>

        </section>
    </body>
</html>
