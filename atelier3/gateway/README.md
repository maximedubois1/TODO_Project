# Gateway

La Gateway est le point d'entrée de notre application. Elle permet de rediriger les requêtes vers les différents services. Elle tourne sur le port 8080, qui sera le seul port accessible depuis l'extérieur.

Celle ci va se connecter au service de découverte pour récupérer les adresses des services.

Les services sont les suivants :

- uAuth : Service d'authentification
- uCard : Service de gestion des cartes
- uRoom : Service de gestion des salles
- Orchestrator : Service d'orchestration
