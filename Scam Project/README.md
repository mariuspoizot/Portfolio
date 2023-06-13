Projet Scam
===========

Développé par Lony Fauchoit  Marius Poizot
Contacts : lony.fauchoit.etu@univ-lille.fr , marius.poizot.etu@univ-lille.fr

# Présentation de Projet Scam

Le but est simple, vous avez une grille et des cubes.
Dans cette grille il y a des zones blanches (zones motifs) et des cubes jaunes (blocs motifs).
Le but est de placer des blocs de déplacement ou d'altération dans les zones vertes (zones de placement)
afin de faire en sorte que les blocs motifs se retrouvent dans les zones motifs.

Chaque niveau aura un certain nombre de blocs plaçables, ainsi qu'un nombre prédéfini de placements possibles et de tours.

À chaque tour, les blocs se déplacent orthogonalement. Faites en sorte de recréer le motif avant que le nombre de tours restants n'atteigne 0.

Des captures d'écran illustrant le fonctionnement du logiciel sont proposées dans le répertoire shots.
(Les instructions sont réexpliquées lorsque le jeu est lancé)

Les sauvegardes sont stockés dans un CSV.
Les niveaux sont stockés en TXT et retranscrits sur le terminal par plusieurs algorithmes.
Les informations des niveaux sont stockés en CSV et TXT.

# Utilisation de Projet Scam

Afin d'utiliser le projet, il suffit de taper les commandes suivantes dans un terminal :

```
./compile.sh
```
Permet la compilation des fichiers présents dans 'src' et création des fichiers '.class' dans 'classes'

```
./run.sh <mettre ici le nom de votre classe (programme) principal>
```
Permet le lancement du jeu
