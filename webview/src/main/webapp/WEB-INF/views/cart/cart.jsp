<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

       <form:form action="${cartPage}/update" method="post" modelAttribute="cart">

        <table class="product cart">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display Size</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${cart.orderItems}" var="orderItem" varStatus="status" begin="0" step="1">
                <c:set value="${orderItem.phone}" var="product" scope="page"/>
                <tr>
                    <td><a href="/productDetails/${product.model}/${product.key}" target="_blank">${product.model}</a></td>
                    <td>${product.color}</td>
                    <td>${product.displaySize}</td>
                    <td>${product.price}</td>
                    <td>
                        <form:input path="orderItems[${status.index}].quantity" class="quantityField" size="10" value="${orderItem.quantity}"/>
                        <form:errors path="orderItems[${status.index}].quantity" class="error"/>
                    </td>
                    <td>
                        <button formaction="${cartPage}/delete/${product.key}" class="deleteFromCartBtn">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="cartBtns cartForms">
             <form:button>Update</form:button>
        </div>

       </form:form>

        <div class="cartBtns cartForms">
            <form action="${cartPage}/order">
                <button>Order</button>
            </form>
        </div>

    </div>
</div>


</body>
</html>
