<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
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
        <h2> Phones</h2>
        <table border="1">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display Size</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td><a href="productDetails?model=${product.model}&key=${product.key}" target="_blank">${product.model}</a></td>
                    <td>${product.color}</td>
                    <td>${product.displaySize}</td>
                    <td>${product.price}</td>
                    <td><input type="text"  class="quantityField" key="${product.key}" size="5" value="1"/></td>
                    <td><button class="addToCartBtn" key="${product.key}">Add to cart</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
