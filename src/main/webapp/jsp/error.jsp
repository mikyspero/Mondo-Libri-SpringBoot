<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.generation.mondolibri.model.entity.Product, com.generation.mondolibri.model.entity.Cart, java.util.List" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Errore</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/errorStyle.css">
</head>
<body>
    <!-- NON MODIFICARE ALTEZZA ALE-->
<div class="container d-flex flex-column align-items-center justify-content-center vh-100">
    <div class="error-container text-center">
        <i class="fas fa-exclamation-triangle fa-5x mb-3"></i>
        <h2 class="error-message">Qualcosa non è andato a buon fine</h2>
        <p class="error-description">Siamo spiacenti, si è verificato un errore. Per favore, riprova più tardi o torna alla home.</p>
        <a href="${pageContext.request.contextPath}/index" class="btn btn-custom mt-4"><i class="fas fa-home"></i> Torna alla Home</a>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
