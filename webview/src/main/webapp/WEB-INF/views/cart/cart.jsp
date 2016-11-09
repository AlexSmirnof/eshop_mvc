<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>
<c:set var="cartPage" value="${pageContext.request.contextPath}/cart" scope="page"/>
<c:set var="orderPage" value="${pageContext.request.contextPath}/order" scope="page"/>
<html>
<head>
    <title>Cart</title>
    <tag:style/>
    <tag:script/>
</head>
<body>
<div class="container">
    <div class="row">
        <tag:header/>
    </div>
    <div class="row">
        <h2>Cart</h2>
    </div>
    <div class="row">
        <nav class="cart">
            <a href="${productList}"><button class="back">Back to product list</button></a>
            <a href="${orderPage}"><button class="forward">Order</button></a>
        </nav>
    </div>
    <div class="row">
        <table class="product cart">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display Size</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${cart.orderItems}" var="orderItem">
                <c:set value="${orderItem.phone}" var="product" scope="page"/>
                <tr>
                    <td><a href="/productDetails/${product.model}/${product.key}" target="_blank">${product.model}</a></td>
                    <td>${product.color}</td>
                    <td>${product.displaySize}</td>
                    <td>${product.price}</td>
                    <td>
                        <input type="text" form="cart" class="quantityField" name="${product.key}" size="10" value="${orderItem.quantity}"/>
                        <span class="error"></span>
                    </td>
                    <td>
                        <button form="cart" formaction="${cartPage}/delete/${product.key}" class="deleteFromCartBtn">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="cartBtns cartForms">
            <form id="cart" action="${cartPage}/update" method="POST">
                <span class="error"></span>
                <button>Update</button>
            </form>
            <form action="order">
                <button/>Order</button>
            </form>
        </div>
    </div>
</div>


</body>
</html>
