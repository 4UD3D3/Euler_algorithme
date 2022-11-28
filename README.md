 1 Portée du projet
 1.1 Objectif du projet
Ce projet a pour but de créer un algorithme de résolution d’un problème complexe très long
à résoudre à la main. Ce problème est à la base un jeu : Dans une grille donnée et à partir
d’une source d’eau, on doit faire passer l’eau par le plus de tuyau possible (un tuyau par
case).
 1.2 Contraintes
Ce projet doit être réalisé en Java, et en JavaSwing pour l’interface homme machine.
Nous devons pouvoir résoudre le programme avec grille imposée. Cette grille doit s’afficher
dans une interface. Nous devons également mettre en place un système pour que
l’utilisateur puisse créer sur une interface la grille qu’il souhaite.
Ce projet a pour but de créer un algorithme de résolution d’un problème complexe
impossible à résoudre à la main. Ce problème est à la base un jeu, à partir d’une source
d’eau on doit faire passer l’eau par le plus de tuyau possible. Pour ce projet nous avons une
grille avec des tuyaux déjà définit nous allons également mettre en place un système pour
que l’utilisateur puisse mettre la grille qu’il veut. De plus nous
4 / 47
 2 Conception
 2.1 Découpage du projet
Le projet est découpé en deux dossiers : Image et src.
Image contient toutes les images nécessaires pour le projet.
Src contient toutes les classes Java, à savoir :
• Chemin.java
• ControlFenetre.java
• Coup.java
• Fenetre.java
• Grille.java
 2.2 Description des éléments
 Application : Il s’agit de la classe contenant le main. Elle contient également toutes
les fonctions nécessaires à la résolution du problème.
 Chemin : Chemin est une classe contenant une liste de Coup. Il contient donc tous
les coups joués lors de la résolution du problème. Il contient également toutes les
fonctions nécessaires pour manipuler la liste.
 ControlFenetre : Cette classe détecte tous les clics réalisés sur la fenêtre et agit en
conséquence.
 Coup : Cette classe est composée de toutes les informations nécessaires lorsqu’un
coup est joué. Il ne possède pas de fonction propre si ce n’est print() pour le debug. Il
est constitué des coordonnées i et j, du numéro de la pièce posée, s’il s’agit d’une
pièce de départ, et le numéro du choix.
 Fenetre : Fenetre gère l’affichage des fenêtres.
 Grille : Possède la grille de jeu, et toutes les fonctions utiles y ayant trait.