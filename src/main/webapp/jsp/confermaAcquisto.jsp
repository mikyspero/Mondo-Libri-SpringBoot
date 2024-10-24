<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grazie per il tuo acquisto!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/confermaAcquistoStyle.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css">
</head>
<body>
<div class="container">
    <h1>Grazie per il tuo acquisto!</h1>

    <!-- Informazioni per la spedizione -->
    <h5><i class="fas fa-box"></i> Informazioni per la tua spedizione</h5>
    <div class="user-info">
        <p><strong>Nome:</strong> ${user.username}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Indirizzo:</strong> ${user.address}</p>
    </div>

    <!-- Prodotto acquistato -->
    <div class="products">
        <h2>Prodotto comprato:</h2>
        <ul>
            <li>${product.name}</li>
        </ul>
    </div>

    <!-- Informazioni aggiuntive sulla spedizione -->
    <p class="soon"><i class="fas fa-truck"></i> Arriverà presto</p>

    <!-- Bottone Home -->
    <div class="text-center mt-4">
        <a href="${pageContext.request.contextPath}/index" class="btn btn-outline-light border-0">
            <i class="fas fa-home"></i> Home
        </a>
    </div>
</div>
</body>
</html>

