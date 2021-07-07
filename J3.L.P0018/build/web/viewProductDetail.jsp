<%-- 
    Document   : viewProductDetail
    Created on : Jul 6, 2021, 5:10:38 PM
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

        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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
                    <c:if test="${empty sessionScope.USER}">
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
                        <c:if test="${sessionScope.USER.role eq 'AD'}">
                            <c:redirect url="indexForAdmin.jsp"/>
                        </c:if>
                        
                        <form class="d-flex" action="DispatchServlet">
                            <button class="btn" type="submit" name="btnAction" value="Profile" >
                            Welcome,    ${sessionScope.USER.name}
                            </button>
                            
                            <button class="btn btn-outline-dark" style="background: tomato" type="submit" name="btnAction" value="Logout">
                                Logout
                            </button>
                            
                            
                            <button class="btn btn-outline-dark" type="submit">
                                <i class="bi-cart-fill me-1"></i>
                                Cart
                                <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
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


        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">


                <%-- %>Start of product <--%>
                <c:if test="${not empty sessionScope.BOOKDETAIL}">
                    
                    <div class="container">
                        <hr>
                        <div class="card">
                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> 
                                                <img src="${sessionScope.BOOKDETAIL.photoCode}">
                                            </div>
                                        </div> <!-- slider-product.// -->
                                        <!-- slider-nav.// -->
                                    </article> <!-- gallery-wrap .end// -->
                                </aside>
                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <h3 class="title mb-3">${sessionScope.BOOKDETAIL.title}</h3>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <span class="currency">VND đ</span><span class="num">${sessionScope.BOOKDETAIL.price}</span>
                                            </span> 
                                            <span>/per</span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p>${sessionScope.BOOKDETAIL.discription} </p></dd>
                                        </dl>
                                        <dl class="param param-feature">
                                            <dt>Author#</dt>
                                            <dd>${sessionScope.BOOKDETAIL.author}</dd>
                                        </dl>  <!-- item-property-hor .// -->
                                        <dl class="param param-feature">
                                            <dt>Category</dt>
                                            <dd>${sessionScope.BOOKDETAIL.categoryId}</dd>
                                        </dl>  <!-- item-property-hor .// -->
                                        <dl class="param param-feature">
                                            <dt>Delivery</dt>
                                            <dd>Việt Nam</dd>
                                        </dl>  <!-- item-property-hor .// -->

                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <dl class="param param-inline">
                                                    <dt>Quantity: </dt>
                                                    <dd>
                                                        <select name="Quantity" class="form-control form-control-sm" style="width:70px;">
                                                            <option> 1 </option>
                                                            <option> 2 </option>
                                                            <option> 3 </option>
                                                        </select>
                                                    </dd>
                                                </dl>  <!-- item-property .// -->
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>
                                        <a href="" class="btn btn-lg btn-outline-dark text-uppercase"> <i class="fas fa-shopping-cart"></i> Add to cart </a>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->
                    </div>
                </c:if>
                <%-- %>end of product <--%>

            </div>

        </section>


        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        Core theme JS
    </body>
</html>
