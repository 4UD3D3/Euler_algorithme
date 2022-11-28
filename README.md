<p class="has-line-data" data-line-start="0" data-line-end="1">1 Portée du projet</p>
<p class="has-line-data" data-line-start="2" data-line-end="42">1.1 Objectif du projet<br>
Ce projet a pour but de créer un algorithme de résolution d’un problème complexe très long<br>
à résoudre à la main. Ce problème est à la base un jeu : Dans une grille donnée et à partir<br>
d’une source d’eau, on doit faire passer l’eau par le plus de tuyau possible (un tuyau par<br>
case).<br>
1.2 Contraintes<br>
Ce projet doit être réalisé en Java, et en JavaSwing pour l’interface homme machine.<br>
Nous devons pouvoir résoudre le programme avec grille imposée. Cette grille doit s’afficher<br>
dans une interface. Nous devons également mettre en place un système pour que<br>
l’utilisateur puisse créer sur une interface la grille qu’il souhaite.<br>
Ce projet a pour but de créer un algorithme de résolution d’un problème complexe<br>
impossible à résoudre à la main. Ce problème est à la base un jeu, à partir d’une source<br>
d’eau on doit faire passer l’eau par le plus de tuyau possible. Pour ce projet nous avons une<br>
grille avec des tuyaux déjà définit nous allons également mettre en place un système pour<br>
que l’utilisateur puisse mettre la grille qu’il veut. De plus nous<br>
4 / 47<br>
2 Conception<br>
2.1 Découpage du projet<br>
Le projet est découpé en deux dossiers : Image et src.<br>
Image contient toutes les images nécessaires pour le projet.<br>
Src contient toutes les classes Java, à savoir :<br>
• Chemin.java<br>
• ControlFenetre.java<br>
• Coup.java<br>
• Fenetre.java<br>
• Grille.java<br>
2.2 Description des éléments<br>
 Application : Il s’agit de la classe contenant le main. Elle contient également toutes<br>
les fonctions nécessaires à la résolution du problème.<br>
 Chemin : Chemin est une classe contenant une liste de Coup. Il contient donc tous<br>
les coups joués lors de la résolution du problème. Il contient également toutes les<br>
fonctions nécessaires pour manipuler la liste.<br>
 ControlFenetre : Cette classe détecte tous les clics réalisés sur la fenêtre et agit en<br>
conséquence.<br>
 Coup : Cette classe est composée de toutes les informations nécessaires lorsqu’un<br>
coup est joué. Il ne possède pas de fonction propre si ce n’est print() pour le debug. Il<br>
est constitué des coordonnées i et j, du numéro de la pièce posée, s’il s’agit d’une<br>
pièce de départ, et le numéro du choix.<br>
 Fenetre : Fenetre gère l’affichage des fenêtres.<br>
 Grille : Possède la grille de jeu, et toutes les fonctions utiles y ayant trait.</p>
