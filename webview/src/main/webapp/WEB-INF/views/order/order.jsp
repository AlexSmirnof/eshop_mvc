<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="productList" value="${pageContext.request.contextPath}/productList" scope="page"/>
<c:set var="orderPage"   value="${pageContext.request.contextPath}/order"       scope="page"/>
<c:set var="cartPage"    value="${pageContext.request.contextPath}/cart"        scope="page"/>

<c:set var="orderItemsList" value="${order.orderItems}"  scope="page"/>
<c:set var="totalPrice"     value="${order.totalPrice}"  scope="page"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order</title>
    <tag:style/>
    <tag:bootstrap/>
    <tag:script/>

</head>
<body>
<div class="container">
    <div class="row">
        <tag:header/>
    </div>
    <div class="row">
        <h2>Order</h2>
    </div>
    <div class="row">
        <nav class="cart">
            <a href="${cartPage}" class="btn btn-primary btn-lg">Back to Cart</a>
        </nav>
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
            <c:forEach var="orderItem" items="${orderItemsList}">
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
                <td>${totalPrice}$</td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>Delivery</td>
                <td>
                    <spring:eval expression="@prices['product.delivery']" var="delivery"/>
                    ${delivery}$
                </td>
            </tr>
            <tr>
                <td colspan="3" class="hideme"></td>
                <td>TOTAL</td>
                <td>
                    <%--<fmt:formatNumber var="total" pattern="000.00" value="${orderC.totalPrice + 5}"/>--%>
                    <%--<c:out value="${total}"/>--%>
                    ${totalPrice + delivery}$
                </td>
            </tr>
        </table>
    </div>

    <div class="row">
        <form:form action="${orderPage}/confirm" method="POST" modelAttribute="order">
        <table class="order">
            <tr>
                <td>First name</td>
                <td>
                    <form:input path="firstName"  placeholder="First name" />
                    <br/><span class="error"><form:errors path="firstName"/></span>
                </td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td>
                    <form:input path="lastName"  placeholder="Last name" />
                    <br/><span class="error"><form:errors path="lastName"/></span>
                </td>
            </tr>
            <tr>
                <td>Address</td>
                <td>
                    <form:input path="deliveryAddress"  placeholder="Address" />
                    <br/><span class="error"><form:errors path="deliveryAddress"/></span>
                </td>
            </tr>
            <tr>
                <td>Phone</td>
                <td>
                    <form:input path="contactPhoneNo"  placeholder="Phone" />
                    <br/><span class="error"><form:errors path="contactPhoneNo"/></span>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <form:textarea path="description"  placeholder="Additional information" ></form:textarea>
                </td>
            </tr>
            <tr>
                <td>
                    <%--<form:hidden path="orderItems"    value="${orderItemsList}"/>--%>
                    <form:hidden path="totalQuantity" value="${order.totalQuantity}"/>
                    <form:hidden path="totalPrice"    value="${totalPrice + delivery}"/>
                    <form:button class="btn btn-success">Order</form:button>
                </td>
            </tr>
        </table>
        </form:form>
    </div>
</div>
</body>
</html>
