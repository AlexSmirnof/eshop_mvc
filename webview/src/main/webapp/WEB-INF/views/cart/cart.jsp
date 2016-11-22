<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>
<c:set var="cartPage" value="${pageContext.request.contextPath}/cart" scope="page"/>
<c:set var="orderPage" value="${pageContext.request.contextPath}/order" scope="page"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart</title>
    <tag:style/>
    <tag:bootstrap/>
    <tag:script/>
</head>
<body>
<div class="container">
    <div class="row">
        <tag:header showCartWidget="true"/>
    </div>
    <div class="row">
        <span class="title">Cart</span>
        <c:if test="${not empty flashMessage}">
            <span class="bg-success">
                    ${flashMessage}
            </span>
        </c:if>
    </div>
    <div class="row">
        <nav class="cart">
            <a href="${productList}" class="btn btn-primary btn-lg">Back to product list</a>
            <a href="${orderPage}"   class="btn btn-primary btn-lg">Order</a>
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
                    <td><a href="productDetails?model=${product.model}&key=${product.key}" target="_blank">${product.model}</a></td>
                    <td>${product.color}</td>
                    <td>${product.displaySize}</td>
                    <td>${product.price}</td>
                    <td>
                        <form:input path="orderItems[${status.index}].quantity" class="quantityField" size="10" value="${orderItem.quantity}"
                                    onkeydown="preventDefaultOnEnter(event);" />
                        <br/><span class="error"><form:errors path="orderItems[${status.index}].quantity"/></span>
                    </td>
                    <td>
                        <button formaction="${cartPage}/delete/${product.key}" class="btn btn-danger">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <div class="cartBtns cartForms">
             <form:button id="updateBtn" class="btn btn-success">Update</form:button>
        </div>

       </form:form>

        <div class="cartBtns cartForms">
            <form action="${cartPage}/order">
                <button class="btn btn-success">Order</button>
            </form>
        </div>

    </div>
</div>



</body>
</html>
