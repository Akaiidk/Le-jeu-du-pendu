# 🎨 Jeu du Pendu - Interface JavaFX

Une version moderne et graphique du célèbre jeu du pendu, développée en **Java** avec la bibliothèque **JavaFX**. Ce projet illustre l'utilisation du pattern **MVC** (Modèle-Vue-Contrôleur) et la gestion des interfaces graphiques riches.

## ✨ Fonctionnalités

- **Interface Graphique Intuitive** : Une fenêtre interactive avec des visuels clairs.
- **Dessin Dynamique du Pendu** : Le pendu se dessine étape par étape à chaque erreur.
- **Clavier Virtuel** : Possibilité de cliquer sur les lettres ou d'utiliser le clavier physique.
- **Gestion des Niveaux** : Différentes listes de mots selon la difficulté (facile, moyen, difficile).
- **Animations** : Transitions fluides lors de la victoire ou de la défaite.

## 🛠️ Architecture du Projet

Le projet suit une structure organisée pour séparer la logique métier de l'affichage :

* **Model** : Gestion de la logique du jeu (choix du mot, vérification des lettres).
* **View (FXML)** : Définition de l'interface utilisateur via des fichiers XML.
* **Controller** : Lien entre l'utilisateur et la logique (gestion des clics, mise à jour de l'affichage).
* **CSS** : Personnalisation du style visuel (boutons, couleurs, polices).

## 🚀 Installation et Lancement

### Prérequis
* **Java JDK 17** ou supérieur.

### Étapes
1. **Cloner le projet** :
   ```bash
   git clone [https://github.com/Akaiidk/Le-jeu-du-pendu.git](https://github.com/Akaiidk/Le-jeu-du-pendu.git)
   cd Le-jeu-du-pendu
