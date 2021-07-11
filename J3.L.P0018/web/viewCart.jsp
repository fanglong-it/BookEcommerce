<%-- 
    Document   : viewCart
    Created on : Jul 7, 2021, 9:36:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Cart</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"">
        <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>
        <h1>Cart</h1>

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
                        <form class="d-flex" action="DispatchServlet">
                            <button class="btn" type="submit" name="btnAction" value="LoginPage" >Login</button>
                            <button class="btn btn-outline-dark" type="submit" name="btnAction" value="ViewCartPage">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
                            </button>
                        </form>
                    </c:if>
                    <c:if test="${not empty sessionScope.USER}">
                        <c:if test="${sessionScope.USER.role eq 'AD'}">
                            <c:redirect url="indexForAdmin.jsp"/>
                        </c:if>

                        <form class="d-flex" action="DispatchServlet">
                            <button class="btn" type="submit" name="btnAction" value="Profile" >
                                Welcome,    ${sessionScope.USER.name}
                            </button>

                            <button class="btn btn-outline-danger" type="submit" name="btnAction" value="Logout">
                                Logout
                            </button>

                            <button class="btn btn-outline-dark" type="submit" name="btnAction" value="ViewCartPage">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
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

        <section class="py-5">

            <div class="container px-4 px-lg-5 mt-5">



                <main class="page">
                    <section class="shopping-cart dark">
                        <div class="container">

                            <div class="content">
                                <div class="row">
                                    <div class="col-md-12 col-lg-12">
                                        <div class="items">


                                            <c:if test="${ not empty sessionScope.BOOKCART}">
                                                <%-->Start of Product in Cart<--%>
                                                <c:forEach var="c" items="${sessionScope.BOOKCART}">
                                                    <div class="product">
                                                        <div class="row">
                                                            <div class="col-md-3">
                                                                <img class="img-fluid mx-auto d-block image" src="${c.getPhotoCode()}" alt="...">
                                                            </div>
                                                            <div class="col-md-8">
                                                                <div class="info">
                                                                    <form action="DispatchServlet">
                                                                        <div class="row">
                                                                            <div class="col-md-4 product-name">
                                                                                <div class="product-name">
                                                                                    <a href="DispatchServlet?btnAction=ViewDetail&BookId=${c.bookID}">${c.title}</a>
                                                                                    <div class="product-info">
                                                                                        <div>Price: <span class="value">${c.price}</span></div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <div class="col-md-3 quantity">
                                                                                <label for="quantity">Quantity:</label>
                                                                                <input id="quantity" type="number" name="quantity" value ="${c.bookQuantity}" class="form-control quantity-input">
                                                                            </div>
                                                                            <div class="col-md-5 row">
                                                                                <input type="hidden" name="BookId" value="${c.bookID}" />
                                                                                <button class="col-1 col-lg-6 btn btn-outline-primary" name="btnAction" value="UpdateBookInCart">Update</button>
                                                                                <button class="col-2 col-lg-6 btn btn-outline-danger" name="btnAction" value="DeleteBookInCart">Delete</button>
                                                                            </div>
                                                                        </div>
                                                                    </form>

                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <%-->End of Product in Cart<--%>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty sessionScope.BOOKCART}">
                                                Nothing in cart
                                            </c:if>
                                            <c:if test="${not empty sessionScope.ORDERSUCCESS}">
                                                <p style="color: green"> ${sessionScope.ORDERSUCCESS} </p>
                                            </c:if>


                                        </div>
                                    </div>


                                    <%-->Summary for Product<--%>
                                    <div class="col-md-12 col-lg-12">
                                        <div class="summary">
                                            <c:if test="${not empty sessionScope.TOTAL}">
                                                <form action="DispatchServlet">
                                                    <h3>Summary</h3>
                                                    <div class="summary-item"><span class="text">Subtotal</span><span class="price">${sessionScope.TOTAL.subTotal}</span></div>
                                                    <div class="summary-item"><span class="text">Discount</span><span class="price">${sessionScope.TOTAL.discount}</span></div>
                                                    <div class="summary-item"><span class="text">Shipping</span><span class="price">${sessionScope.TOTAL.shipping}</span></div>
                                                    <div class="summary-item"><span class="text">Total</span><span class="price">${sessionScope.TOTAL.total}</span></div>
                                                    <div class="summary-item">

                                                        <span class="text">Discount Code: </span>
                                                        <span class="text">
                                                            <input type="hidden" name="username" value="${sessionScope.USER.username}" />
                                                            <c:if test="${sessionScope.TOTAL.subTotal ne '0.0'}">

                                                                <input class="btn btn-outline-dark mb-0" type="text" name="DiscountCode" value="${param.DiscountCode}" />
                                                                <button class="btn btn-outline-dark mb-3" type="Submit" name="btnAction" value="CheckDiscountCode">Check</button>
                                                            </c:if>
                                                        </span>
                                                    </div>
                                                    <c:if test="${not empty requestScope.ERROR_DISCOUNT}">
                                                    <p style="color: red">${requestScope.ERROR_DISCOUNT}</p>
                                                    </c:if>

                                                    <c:if test="${not empty requestScope.ERROR_CHECKOUT}">
                                                        <p style="color: red">${requestScope.ERROR_CHECKOUT}</p>
                                                    </c:if>
                                                    <c:if test="${not empty sessionScope.ERROR_CHECKOUT}">
                                                        <p style="color: red">${sessionScope.ERROR_CHECKOUT}</p>
                                                    </c:if>
                                                        
                                                    <c:if test="${sessionScope.TOTAL.subTotal ne '0.0'}">
                                                        <button type="submit" name="btnAction" value="Checkout" class="btn btn-primary btn-lg btn-block">Checkout</button>
                                                        <button type="submit" name="btnAction" value="CheckoutPaypal" class="btn btn-outline-dark btn-lg btn-block">Paypal</button>
                                                    </c:if>

                                                </form>
                                            </c:if>

                                            <c:if test="${empty sessionScope.TOTAL}">
                                                <h3>Summary</h3>
                                                <div class="summary-item"><span class="text">Subtotal</span><span class="price">$0</span></div>
                                                <div class="summary-item"><span class="text">Discount</span><span class="price">$0</span></div>
                                                <div class="summary-item"><span class="text">Shipping</span><span class="price">$0</span></div>
                                                <div class="summary-item"><span class="text">Total</span><span class="price">$0</span></div>
                                            </c:if>

                                        </div>
                                    </div> 
                                </div>
                            </div>
                    </section>
                </main>
            </div>
        </section>
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        Core theme JS
        <!--        <script src="js/scripts.js"></script>-->

    </body>
</html>
