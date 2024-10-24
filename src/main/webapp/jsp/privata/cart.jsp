<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carrello</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/carrelloStyle.css">
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<!-- NON MODIFICARE ALTEZZA ALE-->
<div class="container">
    <div class="cart-container">
        <h1 class="text-center">Il tuo carrello</h1>
        <div id="cart-items">
            <c:choose>
                <c:when test="${not empty cart and not empty cart.products}">
                    <c:forEach var="product" items="${cart.products}">
                        <div class="cart-item">
                            <div class="cart-item-details">
                                <div class="cart-item-name">${product.name}</div>
                                <div class="cart-item-price">€<fmt:formatNumber value="${product.price}" type="number" minFractionDigits="2" maxFractionDigits="2"/></div>
                            </div>
                            <form action="${pageContext.request.contextPath}/cart/delete" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${product.id}">
                                <button type="submit" class="btn btn-outline-light border-0">Rimuovi</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/order" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="${product.id}">
                                <button type="submit" class="btn btn-outline-light border-0">Acquista</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p class="text-center">Il tuo carrello è vuoto.</p>
                </c:otherwise>
            </c:choose>
        </div>
        <!-- Bottone Home -->
        <div class="text-center mt-4">
            <a href="${pageContext.request.contextPath}/index" class="btn btn-outline-light border-0">
                <i class="fas fa-home"></i> Home
            </a>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

