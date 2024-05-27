# Atelier 2

## Back :
Pour chaque partie, nous avons créé un controller, un service et un repository.

La base de données est une base postgresql, initié grâce à Hibernate.

Nous avons créé des Entités pour la partie base de données et des DTO pour la partie service et transfert de données. 

Le passage de DTO à Entité est fait grâce à des Mappers.

L'accès à la base de données est fait grâce à des repositories.

### Authentification
- Création d'un utilisateur
- Connexion d'un utilisateur (Avec gestion de token : ici, nous ne passons que le surname non hashé par soucis de simplicité, cependant il serait préférable de passer un token JWT)
- Déconnexion d'un utilisateur
- Récupération de l'utilisateur connecté

### User
- Création d'un utilisateur

### Card
- Création d'une card
- Récupération de toutes les cards
- Récupération de toutes les cards d'un utilisateur
- Récupération d'une card par son id
- Récupération des cards disponibles (celles n'appartenant à aucun utilisateur)

### CardGenerator
- Génération de carte aléatoire 

### Market
- Vente d'une carte (elle devient alors disponible pour tous les utilisateurs)
- Achat d'une carte (elle est alors associée à l'utilisateur qui l'a achetée)

### Liste des endpoints
![Endpoints](./api-endpoint.png)

## Frontend :
- Ajout d'un fichier `api.js` afin de centraliser les requêtes vers le backend
- Légères modifications des fichiers présents afin de prendre en compte notre backend
