<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<header>
    <h1><span class="glyphicon glyphicon-phone"></span> Phonify</h1> <%--Adorn Roman font | Gallivant--%>
    <button>My cart: ${cart.totalQuantity} items ${cart.totalPrice}$</button>
</header>
<hr>
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
        <c:forEach items="${productList}" var="product">
            <tr>
                <td><a href="/productDetails/${product.model}/${product.key}" target="_blank">${product.model}</a></td>
                <td>${product.color}</td>
                <td>${product.displaySize}</td>
                <td>${product.price}</td>
                <td><input type="text" name="quantity" value="${productList[product].quantity}"/></td>
                <td><input type="submit" value="Add To Cart"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
