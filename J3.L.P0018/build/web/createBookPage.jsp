<%-- 
    Document   : createBookPage
    Created on : Jul 9, 2021, 9:45:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

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

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <h1>Create Book</h1>
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
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4 ">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Create</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="DispatchServlet?btnAction=CreateBookPage">Create Book</a></li>
                                <li><a class="dropdown-item" href="DispatchServlet?btnAction=CreateDiscountPage">Create Discount</a></li>

                            </ul>
                        </li>
                    </ul>
                    <a class="navbar me-auto btn btn-outline-dark" href="DispatchServlet?btnAction=ViewHistoryPage">View History</a>

                    
                    
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
                    <c:if test="${empty sessionScope.USER}">
                        <c:redirect url="index.jsp"/>
                        <form class="d-flex" action="DispatchServlet">
                            <button class="btn" type="submit" name="btnAction" value="LoginPage" >Login</button>
                            <button class="btn btn-outline-dark" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
                                <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${not empty sessionScope.USER}">

                        <c:if test="${sessionScope.USER.role eq 'US'}">
                            <c:redirect url="index.jsp"/>
                        </c:if>

                        <form class="d-flex" action="DispatchServlet">
                            <button class="btn" type="submit" name="btnAction" value="Profile" >
                                Welcome,    ${sessionScope.USER.name}
                            </button>

                            <button class="btn btn-outline-dark" style="background: tomato" type="submit" name="btnAction" value="Logout">
                                Logout
                            </button>

                        </form>

                    </c:if>
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

        <form action="DispatchServlet">
            <section class="py-5">
                <div class="container px-4 px-lg-5 mt-5">
                    
                    
                    <%-- %>Start of product <--%>
                        <div class="container">
                            <hr>
                            <div class="card">
                                <div class="row">
                                    <form action="DispatchServlet">
                                        <aside class="col-sm-12">
                                            <article class="card-body p-5">
                                                <dt>Title</dt>
                                                <h3 class="title mb-3"><input style="width: 100% ;" type="text" name="bookTitle" value="" /></h3>

                                                <p class="price-detail-wrap"> 
                                                    <span class="price h3 text-warning"> 
                                                        <span class="currency">VND đ</span><span class="num"><input type="text" name="bookPrice" value="" /></span>

                                                    </span> 
                                                    <span>/per</span> 
                                                </p> <!-- price-detail-wrap .// -->
                                                <dl class="item-property">
                                                    <dt>Description</dt>
                                                    <dd><input type="text" style="width: 100% ;" name="bookDescription" value="" /></dd>
                                                     
                                                </dl>
                                                <dl class="param param-feature">
                                                    <dt>Author</dt>
                                                    <dd><input type="text" name="bookAuthor" value="" /></dd>

                                                </dl>  <!-- item-property-hor .// -->
                                                <dl class="param param-feature">
                                                    <dt>Category</dt>
                                                    <select name="bookCategory">
                                                        
                                                        
                                                        <c:forEach var="category" items="${sessionScope.CATE}">
                                                            <option value="${category.categoryId}">${category.categoryName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </dl>  <!-- item-property-hor .// -->
                                                <dl class="param param-feature">
                                                    
                                                </dl>  <!-- item-property-hor .// -->

                                                <div class="row">
                                                    <div class="col-sm-5">
                                                        <dl class="param param-inline">
                                                            <dt>Quantity: </dt>
                                                            <dd>
                                                                <input type="text" name="bookQuantity" value="" />
                                                            </dd>
                                                        </dl>  <!-- item-property .// -->
                                                    </div> <!-- col.// -->

                                                </div> <!-- row.// -->
                                                
                                                <!-- row.// -->
                                                <div class="row">
                                                    <div class="col-sm-10">
                                                        <dl class="param param-inline">
                                                            <dt>Photo Code: </dt>
                                                            <dd>
                                                                <input type="text" style="width: 100% ;" name="photoCode" value="" />
                                                            </dd>
                                                        </dl>  <!-- item-property .// -->
                                                    </div> <!-- col.// -->

                                                </div><!-- row.// -->
                                                <hr>
                                                <c:if test="${not empty sessionScope.CREATE_MESSAGE}">
                                                    <p style="color: red">${sessionScope.CREATE_MESSAGE}</p>
                                                </c:if>
                                                
                                                <button class="btn btn-lg btn-outline-success text-uppercase" type="submit" name="btnAction" value="CreateBook">Update</button>
                                            </article> <!-- card-body.// -->
                                        </aside>
                                    </form>
                                    <!-- col.// -->
                                </div> <!-- row.// -->
                            </div> <!-- card.// -->

                        </div>


                    <%-- %>end of product <--%>

                </div>


            </section>
        </form>


        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        Core theme JS
    </body>
</html>
