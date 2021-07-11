<%-- 
    Document   : indexForAdmin
    Created on : Jul 6, 2021, 7:33:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <h1>Admin Page!</h1>
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

                    <%-->Search<--%>
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
        <!-- Header-->

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




        <%-->Begin of List Book<--%>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

                    <c:forEach var="o" items="${sessionScope.LISTBOOK}">
                        <div class="col mb-5">
                            <div class="card h-100">
                                <!-- Product image-->
                                <img class="card-img-top" src="${o.getPhotoCode()}" alt="..." />
                                <!-- Product details-->
                                <div class="card-body p-4">
                                    <div class="text-center">
                                        <!-- Product name-->
                                        <h5 class="fw-bolder">${o.getTitle()}</h5>
                                        <!-- Product price-->
                                        ${o.getPrice()}
                                    </div>
                                </div>
                                <!-- Product actions-->
                                <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                    <div class="text-center">
                                        <form action="DispatchServlet" >
                                            <input type="hidden" name="BookId" value="${o.getBookID()}" />
                                            <button class="btn btn-outline-dark mt-auto" type="Submit" name="btnAction" value="ViewDetail">
                                                View detail
                                            </button>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>


                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">Special Item</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">$20.00</span>
                                    $18.00
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Add to cart</a></div>
                            </div>
                        </div>
                    </div>





                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        Core theme JS
        <!--        <script src="js/scripts.js"></script>-->

    </body>
</html>
