<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <tag:style/>
    <tag:script/>
</head>
<body>
<div class="container">
    <div class="row">
        <tag:header/>
    </div>
    <div class="row">
        <h2> Phones</h2>
        <table class="product">
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
                    <td>${product.price}$</td>
                    <td><input type="text"  class="quantityField" key="${product.key}" size="10" value="1"/>
                        <span class="error"></span>
                    </td>
                    <td><button class="addToCartBtn" key="${product.key}">Add to cart</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<script>
    AddToCart();
</script>

</body>
</html>
