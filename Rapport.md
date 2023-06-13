Vous pouvez télécharger le présent fichier au format .md depuis la page Moodle de la SAÉ à l'aide d'un clic droit sur le lien, "Enregistrer la cible du lien" ou équivalent.

Le rapport à rendre doit être au format pdf ou html (éventuellement format md lisible sur gitlab) et doit suivre le plan donné ici.
Il doit se trouver dans un répertoire `graphes` à la racine de votre dépôt git.
Le premier rendu concerne uniquement la Version 1, le second rendu concerne la Version 2.

Pour chaque partie du plan nous indiquons *en italique* ce que cette partie doit contenir au minimum.

Le rapport n'est pas un DS où on se contente de répondre aux questions. 
Dans le rapport **vous devez écrire des phrases et ajouter toutes les explications** qui vous semblent nécessaires pour le rendre compréhensible et agréable à lire, comme tout rapport se doit d'être.

SAE S2.02 -- Rapport pour la ressource Graphes
===

> Poizot Marius , Atilla Tas , Kellian Mirey 

Version 1
---

Sera évaluée à partir du tag git `Graphes-v1`

### Étude d'un premier exemple

*Énumérer tous les appariements acceptables (c'est à dire qui asocient des adolescents compatibles) pour les données de l'Exemple 1, en supposant que les français rendent visite aux italiens.*

Adonia peut aller avec tout le monde mais Xolag et Zander sont préférables car ils partagent des hobbies . 
Bellatrix est allergique aux animaux , elles ne peu donc pas aller avec Yak , elle peut aller avec Xolag et Zander mais Xolag est préférable car ils partagent un hoobie .
Callista peut aller avec tout le monde mais Yak est préférable car ils partagent les mêmes hobbies .

*Justifier pourquoi l'appariement optimal est Bellatrix--Xolag, Adonia--Zander, et Callista--Yak; cette explication ne doit pas parler de graphes, mais se baser uniquement sur les données du problème.*

Cet appariement est optimal puisque comme expliqué dans la question précédente Adonia et Callista peuvent aller avec tout le monde ce qui n'est pas le cas de Bellatrix car elle est allergique aux animaux et que Yak en possède . Donc Xolag est le mieux pour Belatrix , Yak est le meux pour Callista , Adonia va donc avec Zander .

### Modélisation de l'exemple

*Donner un graphe qui modélise l'Exemple 1, plus précisément, la matrice d'adjacence de ce graphe. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*

Matrice d'adjacence :

     
      A B C X Y Z
    A 0 0 0 1 1 1
    B 0 0 0 1 1 1 
    C 0 0 0 1 1 1 
    X 1 1 1 0 0 0 
    Y 1 1 1 0 0 0 
    Z 1 1 1 0 0 0 
Pour avoir un résultat parlant et clair nous avons pris des poids ronds .De base toutes les arrêtes sont a 50 , si des personnes ont un hobbie en commun on enleve 10 a cette arrête , si elles en ont deux , on enlève 20 ...
Si une personne a une allergie un poids de 50 est rajouté au poids de base avec la personne ayant un animal . Les poids sont assez explicites et rendent les calculs simples et compréhensibles .

### Modélisation pour la Version 1

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1.*

Toutes les arrêtes ont , de base , un poids de 50  . Le poids de cette arrête va être modifié en fonction de l'allergie et des hobbies . Un hobbie en commun entre deux personnes enleve un poids de 10 pour l'arrête entre ces deux personnes . Une allergie aux animaux rajoute 50 pour l'arrête entre une personne allergique et une personne qui  a un animal . Exemple : Bellatrix et Yak ont une arrête de poids 90  : poids de base  + poids allergie - poids hobbie = 90 .

### Implémentation de la Version 1

*Cette partie du travail sera évaluée à partir du code. Implémenter la fonction weight de la classe AffectationUtil en suivant la modélisation proposée. Puis, implémenter une classe TestAffectationVersion1 qui montre que votre implémentation est correcte. La classe de test doit contenir au moins une méthode de test comme ceci:*
- *créer les adolescents de l'Exemple 1*
- *construire le graphe modèle pour ces adolescents; le graphe sera de type fr.ulille.but.GrahpeNonOrienteValue*
- *calculer l'affectation optimale en utilisant la classe fr.ulille.but.CalculAffectation*
- *écrire des assertions (assertEquals ...) qui vérifient que le résultat de l'affectation calculé à l'étape précédente est bien celui attendu*

*Si vous n'êtes pas à l'aise avec les tests unitaires, votre classe TestAffectationVersion1 peut contenir une méthode main à la palce de la méthode de test, dans ce cas vous afficherez dans le terminal l'appariement résultat.*

### Exemple de vérification de l'incompatibilité 

*Cet exemple va mettre au défi votre modèle vis à vis de la prise en compte de l'incompatibilité entre adolescents 

Récupérez sur Moodle le fichier de données `compatibilityVsHobbies.csv`. Expliquez quelle est sa particularité de cet exemple. Écrire la méthode de test qui test qui change cet exemple, construit le graphe modèle, calcule l'affectation, et finalement vérifie qu'aucune paire d'adolescents non compatibles n'a été construite par l'algorithme.*

La particularité de cet exemple est qu'il n'y a qu'un appariement possible car A a une allergie aux animaux et D a un animal et C n'en a pas donc le seul appariement possible est AC et BD , les deux filles sont donc ensemble et les garçcons aussi .

Version 2
---

Sera évaluée à partir du tag git `Graphes-v2`

### Exemple minimal pour la gestion de l'historique

*Présenter un exemple minimal qui est pertinent pour tester l'historique. L'exemple contiendra:*
- *huit adolescents de deux pays différents tels que* 
  - *certains des adolescents expriment des préférences d'historique (critère HISTORY). Toutes les valeurs possibles pour ce critère doivent être présentes*
  - *aucun des adolescents n'est allergique aux animaux en aucun n'a exprimé de passe-temps, ainsi pour l'instant on peut se concentrer uniquement sur la gestion de l'historique*
- *un historique, c'est à dire une collection de paires d'adolescents qui étaient correspondants l'année passée. Ces paires doivent permettre d'illustrer les différents cas de figure qui peuvent se présenter par rapport aux contraintes d'historique et les huit adolescents*

*Puis, donner un appariement optimal qui tient compte des données d'historique, et expliquer pourquoi il est optimal. L'explication ne doit pas parler des graphes, mais uniquement des adolescents et les critères exprimés.*

### Deuxième exemple pour la gestion d'historique

*Modifiez l'exemple précédent en ajoutant des préférences liées aux passe-temps. Donnez l'appariement que vous considérez optimal dans ce cas. En particulier, expliquez comment vous comptez combiner une éventuelle affinité liée à l'historique avec l'affinité liée aux passe-temps. Rappelons que l'historique peut compter comme une contrainte rédhibitoire ou comme une préférence, voir le sujet pour plus de précisions.*

*Donner l'appariement que vous considérez optimal dans ce deuxième exemple, toujours sans parler de graphes.*

### Modélisation pour les exemples

*Pour chacun des deux exemples précédents, donnez un graphe (donné par sa matrice d'adjacence) tel que l'affectation minimale dans ce graphe correspond à l'appariement optimal identifié plus haut. Expliquez comment vous avez choisi le poids pour chacune des arêtes.*

### Modélisation pour l'historique de la Version 2

*Décrire une modélisation générale pour la Version 1. C'est à dire, donner une formule ou une description précise qui décrit comment, étant donné un adolescent hôte et un adolescent visiteur, on détermine le poids de l'arête entre ces deux adolescents en fonction des critères considérés dans la Version 1. Décrire également comment vous construisez le graphe modèle à partir des données en entrée.*

### Implémentation de l'historique de la Version 2

*Quelles fonctions de votre code avez-vous modifié pour prendre en compte le critère historique ? Donnez ici les noms des méthodes (et leur classe), à quoi elles servent, et quelles modifications vous avez apportées. Essayez d'être synthétique.*

### Test pour l'historique de la Version 2

*Créer la classe de TestAffectationVersion2 qui contiendra deux méthodes de test, une pour chacun des exemples. Chacune de ces méthodes doit avoir la même structure que pour TestAffectationVersion1, c'est à dire créer les données d'entrée (adolescents, historique), créer le graphe, calculer l'affectation, et tester que le résultat est comme attendu.*

### Prendre en compte les autres préférences

*Pour chacun des autres critères d'affinité que vous décidez de prendre en compte, décrire comment vous changez la fonction weight de la classe AffectationUtil.*

### L'incompatibilité en tant que malus

*Proposer une formule ou une description précise qui explique comment calculer le poids d'une arête en considérant les incompatibilités comme des malus et les critères satisfaits comme des bonus. Implémenter cette formule dans une seconde méthode appelée `weightAdvanced`, ceci pour éviter de casser votre code. Puis, écrire une méthode de test qui permet d'illustrer le calcul d'affectation basé sur `weightAdvanced`. Vous pouvez égalmente tester l'affectation en utilisant le fichier de données `incompatibilityVsBonus.csv`.*
