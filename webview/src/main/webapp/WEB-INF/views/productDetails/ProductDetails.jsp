<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" text="text/css" href="<c:url value="/css/styles.css"/>" >
    <script type="text/javascript" src="<c:url value="/js/jquery-3.1.1.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/js/main.js"/>"></script>
</head>
<body>

<div class="container">
    <div class="row">
        <header>
            <div class="phone"><span class="glyphicon glyphicon-phone"></span> Phonify</div> <%--Gallivant--%>
            <button id="cartBtn"><u>My cart:</u> <span id="cartItems">${empty cart.totalQuantity ? 0 : cart.totalQuantity}</span>
                items <span id="cartPrice">${empty cart.totalPrice ? 0 : cart.totalPrice}</span>$
            </button>
            <div class="clear"></div>
        </header>
    </div>
    <div class="row">
        <nav>
            <a href="productList"><button>Back to product list</button></a>
        </nav>
    </div>
    <div class="row">
        <h2>${product.model}</h2>
        <table class="details">
            <tr>
                <td>Display</td>
                <td>${product.displaySize}</td>
            </tr>
            <tr>
                <td>Length</td>
                <td>${product.length}</td>
            </tr>
            <tr>
                <td>Width</td>
                <td>${product.width}</td>
            </tr>
            <tr>
                <td>Color</td>
                <td>${product.color}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${product.price}$</td>
            </tr>
            <tr>
                <td>Camera</td>
                <td>${product.camera}</td>
            </tr>
        </table>
        <p>
            <input type="text"  class="quantityField" key="${product.key}" size="10" value="1"/>
            <button class="addToCartBtn" key="${product.key}">Add to cart</button>
        </p>
    </div>
</div>
</body>
</html>
