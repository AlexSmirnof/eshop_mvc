<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProductList</title>
    <link rel="stylesheet" href="../css/styles.css"/>
</head>
<body>

<h1>Phones</h1>
<%--<button>My cart: ${} items ${}</button>--%>
<hr>
<table>
    <tr>
        <th>Model</th>
        <th>Color</th>
        <th>Display Size</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${phonesList}" var="phone">
        <tr>
            <td><a href="/ProductDetails/${phone.key}" target="_blank">${phone.model}</a></td>
            <td>${phone.color}</td>
            <td>${phone.displaySize}</td>
            <td>${phone.price}</td>
            <td><input type="text" name="quantity"/></td>
            <td><input type="submit" value="Add To Cart"/></td>
        </tr>
    </c:forEach>
</table>


</body>
</html>
