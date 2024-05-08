<%@page import="nguyennmk.product.ProductDTO"%>
<%@page import="java.util.Map"%>
<%@page import="nguyennmk.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Your cart</h1>
        <c:if test="${not empty sessionScope.CART}">
            <c:set var="cart" value="${sessionScope.CART}" scope="page" />
            <c:if test="${not empty cart.items}">
                <form action="DispatchServlet">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Unit Price</th>
                                <th>Total</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${entry.key.name}</td>
                                    <td>${entry.value}</td>
                                    <td>${entry.key.unitPrice}</td>
                                    <td>${entry.value * entry.key.unitPrice}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${entry.key.sku}" />
                                    </td>
                                </tr>
                            </c:forEach>

                            <tr>
                                <td colspan="3">
                                    <a href="DispatchServlet?btnAction=GetProductList">Add more</a>
                                </td>
                                <td>
                                    <input type="submit" name="btnAction" value="Remove Selected Items" />
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <a href="DispatchServlet?btnAction=Checkout">Checkout</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope.CART or empty sessionScope.CART.items}">
            <h2 style="color: red">No cart is not existed</h2>
            <a href="DispatchServlet?btnAction=GetProductList">Back to Product Menu</a>
        </c:if>
    </body>
</html>
