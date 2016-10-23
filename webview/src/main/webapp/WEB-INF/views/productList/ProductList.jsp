<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:url var="pageUrl" value=""/>--%>
<%--${pageContext.request.requestURL}--%>
<html>
<head>
    <title>ProductList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>
<header>
<h1><span class="glyphicon glyphicon-phone"></span> Phonify</h1> <%--Adorn Roman font | Gallivant--%>
    <button>My cart: <span id="cartItems">${cart.totalQuantity}</span> items <span id="cartPrice">${cart.totalPrice}</span>$</button>
</header>
<hr>
<nav>
    <a href="#"><button>Back to product list</button></a>
    <a href="#"><button>Order</button></a>
</nav>
<div class="container">
<table border="1">
    <caption>Cart</caption>
    <tr>
        <th>Model</th>
        <th>Color</th>
        <th>Display Size</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${cartItems.keySet}" var="product">
        <tr>
            <td><a href="/productDetails/${product.model}/${product.key}" target="_blank">${product.model}</a></td>
            <td>${product.color}</td>
            <td>${product.displaySize}</td>
            <td>${product.price}</td>
            <td><input type="text" id="productQuantity" key="${product.id}" value="0"/></td>
            <td><input type="submit" id="addToCartBtn" value="Delete"/></td>
        </tr>
    </c:forEach>
</table>
    <button>Update</button>
    <button>Order</button>

    <script>
        var productInfo = {};
        productInfo.id = document.getElementById("productQuantity").getAttribute("name");
        productInfo.quantity = document.getElementById("productQuantity").getAttribute("value");
        productInfo = JSON.stringify(productInfo);
        var urlWS = "ws://localhost:8080/eshop_mvc/addToCartWS";
        var urlPost = "http://localhost:8080/eshop_mvc//addProductToCart/";
        urlPost += document.querySelector('#productQuantity').getAttribute('key');

        if (window.WebSocket) {
            var ws = new WebSocket(url);
            ws.onmessage = function (e) {
//                поменять значения на cart btn
            };
            ws.onerror = function (e) {
                alert(e.data);
            };
            window.onload = function () {
                document.getElementById("#productQuantity").onclick = function () {
                    ws.send(productInfo);
                }
            }
        } else {
            $("#addToCartBtn").click(function(){
                $.post( urlPost,
                        productInfo
                        ,
                        function(data, status){
                            alert("Data: " + data + "\nStatus: " + status);
 //                     поменять значения на cart btn
                        });
            });
        }
    </script>
</div>
</body>
</html>
