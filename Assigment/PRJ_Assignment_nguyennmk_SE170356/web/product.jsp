<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        <c:set var="list" value="${requestScope.list}" />
        <h1>Product Store</h1>
        <table border="1">
            <tr>
                <th>Product Name</th>
                <th>Description</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${list}" var="product">
                <tr>
                    <td>${product.name}</td>
                    <td>${product.description}</td>
                    <td>${product.unitPrice}</td>
                    <td>${product.quantity}</td>
                    <td>
                        <form action="DispatchServlet">
                            <input type="hidden" name="sku" value="${product.sku}">
                            <input type="submit" value="Add to Cart" name="btnAction">
                        </form>                           
                    </td>
                </tr>
            </c:forEach>
        </table>
        <form action="DispatchServlet" method="POST">
            <input type="submit" value="View Your Cart" name="btnAction" />
        </form>
        <a href="login.html">Back To Login</a>
    </body>
</html>