# MondoLibri-Generation-Italy

Applicazione E-commerce: Progetto Finale per il Corso Generation Italy Java Developer

## Panoramica del Progetto
Questo progetto è un'applicazione monolitica Spring Boot che dimostra la nostra comprensione dei concetti fondamentali dello sviluppo web Java, incluse le operazioni CRUD, l'autenticazione basata su sessione e i fondamenti dell'architettura Model-View-Controller (MVC). L'applicazione è una piattaforma e-commerce che permette agli utenti di sfogliare prodotti, aggiungerli al carrello e completare gli acquisti.

## Funzionalità Principali
Autenticazione e Autorizzazione Utenti
Registrazione utente e funzionalità di login
Autenticazione basata su sessione con Spring Security
Controllo accessi basato su ruoli per funzionalità cliente e admin

### Gestione Prodotti 
Visualizzazione lista prodotti con dettagli 
Aggiunta nuovi prodotti al catalogo  - Solo Admin
Rimozione prodotti dal catalogo  - Solo Admin

### Funzionalità Carrello
Aggiunta prodotti al carrello
Visualizzazione e modifica contenuti carrello

### Gestione Ordini
Creazione ordini dal contenuto del carrello

### Implementazione Tecnica
- Frontend: JSP per il rendering delle pagine
- Backend: Spring Boot MVC
- Database: MySQL con Spring Data JPA
- Autenticazione: Jakarta EE
- Gestione Dipendenze: Maven
- Server: Embedded Tomcat (incluso in Spring Boot)

### Architettura MVC
- Model: Classi Java che rappresentano le entità (User, Product, Order, ecc.)
- View: Template Thymeleaf per il rendering dell'interfaccia utente
- Controller: Controller Spring MVC per gestire le richieste HTTP

## Ringraziamenti
Desidero ringraziare i membri del team che hanno lavorato con me sulla versione precedente al fork.
Conclusione
Questo progetto mostra la nostra capacità di costruire un'applicazione e-commerce funzionale utilizzando le tecnologie Spring Boot. Dimostra la nostra comprensione dello sviluppo Java full-stack, inclusa l'integrazione frontend e backend, la gestione del database e l'implementazione di funzionalità di sicurezza.
Abbiamo applicato le migliori pratiche nello sviluppo software, come la separazione delle responsabilità attraverso il pattern MVC, e implementato funzionalità core essenziali per applicazioni e-commerce. Questo progetto serve come dimostrazione completa delle competenze acquisite durante il corso intensivo Generation Italy Java developer.
