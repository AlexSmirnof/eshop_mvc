<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
<header>
    <h1><span class="glyphicon glyphicon-phone"></span>Phonify</h1>
    <button>My cart: ${0} items ${0}$</button>
</header>
<nav>
    <a href="#"><button>Back to product list</button></a>
</nav>
<div class="container">
    <h2>${product.model}</h2>
    <table border="1">
            <tr>
                <td>Dsplay}</td>
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
    <table>
        <tr>
            <td><input type="text" name="quantity"/></td>
            <td><input type="submit" value="Add To Cart"/></td>
        </tr>
    </table>
</div>

</body>
</html>
