<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="com.generation.mondolibri.model.entity.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="com.generation.mondolibri.model.entity.User" %>
<!doctype html>
<html lang="it">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Mondo Libri</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" href="${pageContext.request.contextPath}/img/LogoFinito.png" type="image/jpg">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
<!-- INIZIO NAVBAR -->
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/index">
            <img class="logo" src="${pageContext.request.contextPath}/img/LogoFinito.png" alt="">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/index">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <%
                            boolean accesso;
                            //HttpSession session = request.getSession(false);
                            if (session == null || session.getAttribute("username") == null) {
                                out.print("Accesso");
                                accesso = false;
                            } else {
                                out.print(session.getAttribute("username"));
                                accesso = true;
                            }
                        %>
                    </a>
                    <ul class="dropdown-menu">
                        <% if (session == null || session.getAttribute("username") == null) { %>
                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#loginModal">Accedi</a></li>
                        <li><a class="dropdown-item" href="#" data-bs-toggle="modal" data-bs-target="#registerModal">Registrati</a></li>
                        <% } else { %>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/users/logout">Logout</a></li>
                        <% } %>
                    </ul>
                </li>
                <%-- Sezione Admin visibile solo se loggato come admin --%>
                <% if (session != null && session.getAttribute("username") != null && session.getAttribute("role").equals("Admin")) { %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Area Amministratore
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/users/admin/all">Utenti</a></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/catalogue/admin/catalogue">Prodotti</a></li>
                    </ul>
                </li>
                <% } %>
                <li class="nav-item">
                    <a class="nav-link" href="#">Chi siamo</a>
                </li>
            </ul>
            <form action="${pageContext.request.contextPath}/catalogue" method="GET" class="d-flex" role="search">
                <input class="form-control me-2 border-0 btn-search" type="search" placeholder="Cosa vorresti leggere?" aria-label="Search" name="keyword">
                <div class="btn-group me-2">
                    <button type="button" class="btn btn-outline-dark border-0 dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        Filtra per prezzo
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('minPrice').value=''; document.getElementById('maxPrice').value='';">Qualsiasi Prezzo</a></li>
                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('minPrice').value='0'; document.getElementById('maxPrice').value='10';">0 - 10 €</a></li>
                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('minPrice').value='10'; document.getElementById('maxPrice').value='20';">10 - 20 €</a></li>
                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('minPrice').value='20'; document.getElementById('maxPrice').value='30';">20 - 30 €</a></li>
                        <li><a class="dropdown-item" href="#" onclick="document.getElementById('minPrice').value='30'; document.getElementById('maxPrice').value='';">30+ €</a></li>
                    </ul>
                </div>
                <input type="hidden" id="minPrice" name="minPrice">
                <input type="hidden" id="maxPrice" name="maxPrice">
                <button class="btn btn-outline-dark border-0" type="submit">Cerca</button>
            </form>
            <div id="user-info" class="ms-3">
                <!-- Qui verrà visualizzato il nome utente -->
            </div>
            <%
                String href;
                String classe;
                String cart;
                if (accesso)
                {
                    href = request.getContextPath();
                    classe = "";
                    cart = "/cart";
                } else {
                    href = "#";
                    classe = "data-bs-toggle=\"modal\" data-bs-target=\"#loginModal\"";
                    cart = "";
                }
            %>
            <a class="btn btn-outline-dark border-0 ms-3"
               href="<%= href + cart %>"
               role="button" <%= classe %>>
                <i class="bi bi-cart"></i> Carrello
            </a>
        </div>
    </div>
</nav>
<!-- FINE NAVBAR -->

<div class="container mt-4">
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
        <% for (User u : (java.util.List<User>) request.getAttribute("user")) { %>
        <div class="col">
            <div class="card h-100 p-1">
                <div class="card-body">
                    <h5 class="card-title"><%= u.getUsername() %></h5>
                    <p class="card-text"><strong>Email:</strong> <%= u.getEmail() %></p>
                    <p class="card-text"><strong>Address:</strong> <%= u.getAddress() %></p>
                    <!-- Aggiungi altre informazioni secondo necessità -->
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>

<!-- INIZIO FOOTER -->
<footer class="footer bg-dark text-light py-4 mt-5">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <h5>Assistenza Clienti</h5>
                <a href="#"><i class="bi bi-chat-dots"></i> Chatta con noi</a><br>
                <a href="blalblalal"><i class="bi bi-envelope"></i> blablabla.com</a>
            </div>
            <div class="col-md-3">
                <h5>Contattaci</h5>
                <a href="#"><i class="bi bi-geo-fill"></i> blabla</a><br>
                <a href="#"><i class="bi bi-clock-fill"></i> blabla</a><br>
                <a href="#"><i class="bi bi-clock-fill"></i> blabla</a>
            </div>
            <div class="col-md-3">
                <h5>Spedizioni</h5>
                <a href="#"><i class="bi bi-truck"></i> Informazioni Spedizioni</a><br>
                <a href="#"><i class="bi bi-box-seam"></i> Tracciamento Ordine</a>
            </div>
            <div class="col-md-3">
                <h5>Valutaci</h5>
                <p>Condividi la tua esperienza</p>
                <a href="#"><i class="bi bi-star-fill"></i></a>
                <a href="#"><i class="bi bi-star-fill"></i></a>
                <a href="#"><i class="bi bi-star-fill"></i></a>
                <a href="#"><i class="bi bi-star-fill"></i></a>
                <a href="#"><i class="bi bi-star"></i></a>
            </div>
        </div>
    </div>
</footer>
<!-- FINE FOOTER -->

<!-- LOGIN MODALE -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Accedi ora</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="login" method="post" id="loginForm">
                    <div class="mb-3">
                        <label for="login_username" class="form-label">Nome utente</label>
                        <input type="text" class="form-control" id="login_username"  name="login_username" required>
                    </div>
                    <div class="mb-3">
                        <label for="login_password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="login_password" name="login_password" required>
                    </div>
                    <button type="submit" class="btn btn-outline-dark" value="Login">Accedi</button>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- MODALE REGISTRAZIONE -->
<div class="modal fade modal-sm" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Registrati adesso</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="registerForm" action="registration" method="post" >
                    <div class="mb-3">
                        <label for="registration_username" class="form-label">Nome utente</label>
                        <input type="text" class="form-control" id="registration_username" placeholder="Inserisci il tuo nome utente" name="registration_username" required>
                    </div>
                    <div class="mb-3">
                        <label for="registration_address" class="form-label">Indirizzo</label>
                        <input type="text" class="form-control" id="registration_address" placeholder="Inserisci il tuo indirizzo" name="registration_address" required>
                    </div>
                    <div class="mb-3">
                        <label for="registration_email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="registration_email" placeholder="Inserisci la tua email" name="registration_email" required>
                    </div>
                    <div class="mb-3">
                        <label for="registration_password" class="form-label">Password</label>
                        <input type="password" class="form-control" id="registration_password" placeholder="Inserisci la tua password" name="registration_password" required>
                    </div>
                    <button type="submit" class="btn btn-outline-dark">Registrati</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
