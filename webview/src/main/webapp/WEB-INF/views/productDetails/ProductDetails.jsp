<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details</title>
    <tag:style/>
    <tag:script/>
</head>
<body>

<div class="container">
    <div class="row">
        <tag:header showCartWidget="true"/>
    </div>
    <div class="row">
        <nav>
            <a href="productList" class="btn btn-primary btn-large">Back to product list</a>
        </nav>
    </div>
    <div class="row">
        <h2>${product.model}</h2>
        <table class="product details">
            <tr>
                <td>Display</td>
                <td>${product.displaySize}</td>
            </tr>
            <tr>
                <td>Length</td>
                <td>${product.length}</td>
            </tr>
            <tr>
                <td>Width</td>
                <td>${product.width}</td>
            </tr>
            <tr>
                <td>Color</td>
                <td>${product.color}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>${product.price}$</td>
            </tr>
            <tr>
                <td>Camera</td>
                <td>${product.camera}</td>
            </tr>
        </table>
       <table class="details-buttons">
           <tr>
               <td>
                   <input type="text"  class="quantityField" key="${product.key}" size="10" value="1"/>
                   <span class="error"></span>
               </td>
               <td>
                   <button class="addToCartBtn btn btn-success" key="${product.key}">Add to cart</button>
               </td>
           </tr>

       </table>
    </div>
</div>

<script>
    AddToCart();
</script>

</body>
</html>
