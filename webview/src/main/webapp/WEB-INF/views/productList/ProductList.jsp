<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>
<header>
<h1><span class="glyphicon glyphicon-phone"></span> Phonify</h1> <%--Adorn Roman font | Gallivant--%>
    <button>My cart: ${0} items ${0}$</button>
</header>
<hr>
<div class="container">
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
            <td><a href="/productDetails/${product.model}/${product.key}" target="_blank">${product.model}</a></td>
            <td>${product.color}</td>
            <td>${product.displaySize}</td>
            <td>${product.price}</td>
            <td><input type="text" name="quantity"/></td>
            <td><input type="submit" value="Add To Cart"/></td>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
