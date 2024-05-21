# Atelier 1

## Static : 

Modification des models de vue fournie sur le gitlab :  
-  https://gitlab.com/js-as1/asi1-visual-content/-/tree/master  

Il permet grâce à atelier1\static\addCard.html de rajouter des cards sur le web-service.  
Pour cela, il utilise la page atelier1\static\createCard.html.   
Celle-ci produit le **POST** sur le web-service. 

Une autre fonctionnalité est présente sur la page atelier1\static\card.html.  
La page permet de chercher une card par le nom sur le web-service.   
Elle affiche la première occurrence. Sinon affiche une erreur en console. 

## Dynamique

Création d'un projet spring avec un moteur de template.   
Le projet spring est disponible sur : 
- atelier1\dynamique

Nous avons réalisé une page création et une page affichage.

### Endpoints disponibles : 
**/addCard :** 

    -> GET : renvoie la page de formulaire

    -> POST : permet de rajouter une card

**/{id}** : 

    -> GET : Permet de chercher une card par son id sur le web-service.

### DAO 
Le Dao joue le role du service, c'est grâce a celui ci que nous allons créer et manipuler les cards sur la base de données H2.
Certaines des fonctions sont prêtes pour une utilisation future (getCardList, deleteCard)...

### Views
Les vues sont réalisées en thymeleaf.

**newCardForm.html** : Page de création de card

**viewCard.html** : Page d'affichage de card
