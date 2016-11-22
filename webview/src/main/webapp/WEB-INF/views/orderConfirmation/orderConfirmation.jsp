<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>

<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>
<c:set var="cartPage"    value="${pageContext.request.contextPath}/cart"        scope="page"/>
<c:set var="orderPage"   value="${pageContext.request.contextPath}/order"       scope="page"/>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order</title>
    <tag:style/>
    <tag:script/>

</head>
<body>
<div class="container">
    <div class="row">
        <tag:header/>
    </div>
    <div class="row">
        <h2>Thank you for your order</h2>
    </div>
    <div class="row">
        <table class="product cart">
            <tr>
                <th>Model</th>
                <th>Color</th>
                <th>Display size</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
            <c:forEach var="orderItem" items="${order.orderItems}">
                <c:set var="phone" value="${orderItem.phone}" scope="page"/>
                <tr>
                    <td>${phone.model}</td>
                    <td>${phone.color}</td>
                    <td>${phone.displaySize}</td>
                    <td>${orderItem.quantity}</td>
                    <td>${phone.price * orderItem.quantity}$</td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>Subtotal</td>
                <td>${order.subTotalPrice}$</td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>Delivery</td>
                <td>
                    <spring:eval expression="@prices['product.delivery']"/>$
                </td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>TOTAL</td>
                <td>
                    ${order.totalPrice}$
                </td>
            </tr>
        </table>
    </div>

    <div class="row">
            <table class="order">
                <tr>
                    <td>First name</td>
                    <td>
                        ${order.firstName}
                    </td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td>
                        ${order.lastName}
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>
                        ${order.deliveryAddress}
                    </td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td>
                        ${order.contactPhoneNo}
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        ${order.description}
                    </td>
                </tr>
            </table>
        <a href="${productList}" class="btn btn-success btn-lg">Back to shopping</a>
    </div>
</div>
</body>
</html>
