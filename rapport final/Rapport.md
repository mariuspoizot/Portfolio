Groupe : F5

##  Rapport

## Semaine 6 et 7
    Les 2 premières semaines on a commencé par une installation de machines virtuel classique à la main, on a par la suite répondu à des qiestopns.
    Et nous avons appris a créée un site HTML simplifier pour un rendu plus propre et plus lisible et on peut également créée un Pdf à partir du Md.

    Caractéristiques de la machine 

        1. Nom de la machine : sae203
        2. Dossier de la machine : _/usr/local/virtual_machine/infoetu/login_
        3. Type : Linux
        4. Version : Debian 11/64-bit
        5. RAM : 2048 Mo (minimum)
        6. Disque dur : 20 Go (une seule partition)(minimum)
        7. Laisser le reste par défaut


## Optimisation de la machine :

Il faut tout d'abord installé VirtualBox avec ses extensions. Les composant minimum pour avoir une machine fluide  : __4 go RAM minimum et 20go de disque de stockage.__

## Caractéristiques de la machine 

1. Nom de la machine : sae203

2. Dossier de la machine : _/usr/local/virtual_machine/infoetu/login_

3. Type : Linux

4. Version : Debian 11/64-bit

5. RAM : 2048 Mo

6. Disque dur : 20 Go (une seule partition)

  

## Création machine virtuelle

  

>1. Que signifie “64-bit” dans “Debian 64-bit” ?

  64-bit signifie que le processeur sera capable de traiter des paquets de 64 bit.

Donc ici debian 64-bit signifie qu'il est sur un processeurs traitant du 64-bit.

>2. Quelle est la configuration réseau utilisée par défaut ?

Dynamic Host Configuration Protocol (DHCP)

>3. Quel est le nom du fichier XML contenant la configuration de votre machine ?

"sae203.vbox"

>  4. Sauriez-vous le modifier directement ce fichier pour mettre 2 processeurs à votre machine ?

  Il faut juste aller modifier dans le fichier XML, `<CPU count="1">` et le mettre a 2.

  

## Installation de l'OS

>1. Qu’est-ce qu’un fichier iso bootable ?

Les iso bootable sont des formats de fichiers pouvant créer un DVD-ROM et il agit directement sur le disque mais étant donné que  c'est une machine virtuelle cela ne sert à l’installation de l'os.

>2. Qu’est-ce que MATE ? GNOME ?

MATE et GNOME sont des interfaces graphiques permettant aux  utilisateurs d'être plus à l'aise . MATE est un fork de GNOME.

>3. Qu’est-ce qu’un serveur web ?  

Un serveur web est un serveur privé ou public (cela dépend de l'utilisation de ce serveur web), il est donc utile lorsque l'on veut partager des choses avec d'autre personne qui visite le site en question.

>4. Qu’est-ce qu’un serveur ssh ?

SSH est un protocole qui permet de gérer une machine à distance ,  par exemple pour le télé-travail.

>5. Qu’est-ce qu’un serveur mandataire ?

Le serveur mandataire qui est aussi appelé proxy est un contrôleur d'envoi lors du passage entre l’ordinateur et le réseau et renvoi votre requête.

  

## Préparation du système

## Accès sudo pour user

  

>1. Comment peux-ton savoir à quels groupes appartient l’utilisateur user ?

  Il faut regarder dans le terminal et taper :  _groups user_  pour voir a quel groupe il appartient . 

  
  ## Installation des suppléments invités

>2. Quel est la version du noyau Linux utilisé par votre VM ? N’oubliez pas, comme pour toutes les questions, de justifier votre réponse.

  cat /proc/version : 5.10.0-21-amd64

  
  >3. À quoi servent les suppléments invités ? Donner 2 principales raisons de les installer.

 Ils servent à une meilleur utilisation pour l'utilisateur, un meilleur échange entre l’utilisateur et la machine et permet de transmettre des fichiers à la machine et la machine virtuelle

 
>4. À quoi sert la commande mount (dans notre cas de figure et dans le cas général) ?


La commande mount permet de monter un disque dur et il permet de le lancer.


**Semaine 9 Installation Débian automatisée**

Création du poste :

    Il faut tout d'abord créer une machine virtual avec les memes prérequis que celle de la semaine 6 c'est a dire :
        • Nom de la machine dans VirtualBox : sae203
        • Dossier de la machine : /usr/local/virtual_machine/infoetu/login
          Bien entendu, login est à remplacer par votre login.
        • Type : Linux
        • Version : Debian ou Debian 11 en 64-bit
        • Mémoire vive (RAM) : 2048 Mo pour être à l’aise à l’usage.
        • Disque dur : 20 Go entier (une seule partition)
        • Laisser le reste par défaut.

    Il faut par la suite prendre le fichier zip sur moodle contenant les fichiers necessaires a l'installation automatique.
        
        Il faut par la suite aller dans le terminal, dans le répertoire du ficher en question est tapé la commande suivante : sed -i -E "s/(--iprt-iso-maker-file-marker-bourne-sh).*$/\1=$(cat/proc/sys/kernel/random/uuid)/" S203-Debian11.viso

        Ensuite il y a quelque petite chose a ajouter dans le ficher pressed-fr.cfg :
            -ligne 56 a la fin ajouter "sudo"
            -ligne 83 a la fin ajouter "mate-desktop"
            -ligne 85 ajouter la ligne de commande "d-i pkgsel/include string git, sqlite3, curl bash-completion, neofetch"

        Enfin vous pouvez terminer en ajouter l'iso "S203-Debian11.viso" dans la machine en version lecteur optique. Et vous pouvez lancer la machine virtual et tout s'installe automatiquement.

##  Installation Debian automatisée par pré-configuration (Semaine 9)

>Q1. Qu'est-ce que le Projet Debian? D'où vient le nom Debian ?

Le projet Debian est un groupe mondial de volontaires qui font de leurs mieux pour que les logiciels sont en acces libre.
Le nom Debian vient des prénoms du créateur Ian Murdock et de sa femme Debra. [Source](https://www.debian.org/doc/manuals/project-history/intro.fr.html)


>Q2. Il existe 3 durée de prise en charge(support) de ces versions: la durée minimale, la durée en support long terme (LTS) et la durée en support long terme étendue (ELTS). Quelle sont les durées de ces prises en charge ?

  La durée minimale de la prise en charge dee ces version varie en fonction des version.
  La durée de LTS est : 5 ans. [Source](https://www.debian.org/lts/index.fr.html)
  La durée de ELTS est : 10 ans. [Source](https://wiki.debian.org/LTS/Extended)


>Q3. Pendant combien de temps les mises à jours de sécurité seront-elles fournies ?

Les mises à jours de sécurité sont fournis au bout de 1 ans, après la publication. [Source](https://www.debian.org/security/faq.fr.html#lifespan)


>Q4. Combien de version au minimum sont activement maintenus par Debian? Donnez leur nom générique(= les types de distribution).

Debian a minimum 3 verisons activement maintenus.
Leurs noms sont: stable, testing, unstable. [Source](https://www.debian.org/releases/index.fr.html)


>Q5. Chaque distribution majeur possède un nom de code différent. Par exemple, la version majeur actuelle (Debain 11) se nomme Bulleye. D'où viennent les noms de code données aux distributions ?

Les noms des versions de Debian proviennent des personnages de Toy Story. [Source](https://wiki.debian.org/fr/DebianReleases#Nom_de_code)

>Q6. L'un des atouts de Debian fut le nombre d'architecture (= processeurs) officiellement prises en charge. Combien et lesquelles sont prises en charge par la version Bulleye ?

>Debian fonctionne sur 9 architectures
    -AMD64 & Intel 64
    -Intel x86-based
    -APM
    -APM avec matériel FPU
    -APM 64 bits
    -MIPS 64 bits
    -MIPS 62 bits
    -Power Systems
    -IBM S/390 64 bits
[Source](https://wiki.debian.org/DebianBullseye)


>Q7. Première version avec un nom de code :
    
  - Quelle a était le premier nom de code utilisé? Le nom est dselect.
  - Quand a-t-il été annoncé? En mars 1995.
  - Quelle était le numéro de version de cette distribution? Le numéro de version est : 0.9R5.
  [Source](https://www.debian.org/doc/manuals/project-history/releases.fr.html)


>Q8. Dernière nom de code attribué:
    
    - Quel est le dernier nom de code annoncé à ce jour? Le nom est Bullseye.
    - Quand a-t-il été annoncé? Le 14 août 2021.
    - Quelle est la version de cette distribution? C'est Debian 11.
[Source](https://www.debian.org/doc/manuals/project-history/releases.fr.html)


**Semaine 10 et 11 Gitea**

  Il faut tout d'abord
    git config --global user.name "Prénom Nom" ①
git config --global user.email "votre@email" ②
git config --global init.defaultBranch "master" ③

sudo apt-get install git-all

apres il faut aller dans les caracteristqiue de la VM et chnager au niveau du port dans avancer part gitea et mettre les données a 3000 ou il y a des 0


    L'installation de Gitea, il faut taper les commande suivantes dans le terminal:
        -wget -O gitea https://dl.gitea.com/gitea/1.18.5/gitea-1.18.5-linux-amd64
        chmod +x gitea
        
    La mise en fonctionnement du GPG, il faut :
        Téléchargé la clé à "https://keys.openpgp.org/search?q=teabot%40gitea.io"
    
        Puis après le télechargement, taper les commandes suivantes dans le termial:
            -gpg --keyserver keys.openpgp.org --recv 7C9E68152594688862D62AF62D9AE806EC1592E2
            -gpg --verify gitea-1.18.5-linux-amd64.asc gitea-1.18.5-linux-amd64
        
    Le lancement de Gitea :
        On commence par copié le gitea.sevice donné sur la doc à 
            -/etc/systemd/system/gitea.service
        
        Pour activer gitea au lancemement il faut taper les commandes suivants dans le terminal :
            -sudo systemctl enable gitea
            -sudo systemctl start gitea
        
        Pour vérifier le fonctionnement de gitea on tape la commande :
            -systemctl status gitea.service
        
        Et pour finir on créé l'utilisateur "groupe".

    
    
    Voici comment upload un fichier "test", et donc les commandes à taper dans le terminal : 
        - git init (dans le dossier courant que vous voulez relier)
        - git checkout -b main
        - git add test.txt
        - git commit -m "test"
        - git remote add origin "lien"
        - git push -u origin main
        - git add *
        - git commit -m "la on commit tout le dossier"
        - git push -u origin main 


## 1. Préliminaire
## 1.1. Configuration globale de git

>Q1 : Qu’est-ce que le logiciel git-gui ? Comment se lance-t-il ?

Git Gui est l'interface graphique pour Git. De ce fait, c'est un système de contrôle de version open source. Git Gui donne également une utilisation plus simple.
Pour l'utiliser il faut entrer la commande dans le terminal "git init"

>Q2 : Qu’est-ce que le logiciel gitk ? Comment se lance-t-il ?

Gitk est un outil graphique inclus avec le système de contrôle de version open source Git. Il est utilisé pour afficher l'historique de validation d'un référentiel Git. Gitk affiche également le graphique de validation. Il fournit également des informations détaillées sur chaque commit pour vérifier les differentes version par exemple.

Pour le démarrer, vous devez utiliser la commande gitk dans un terminal, et il est préférable de mettre un & à la fin.

>Q3 : Quelle sera la ligne de commande git pour utiliser par défaut le proxy de l’université sur tous vos projets git ?

git config --global http.proxy [proxy].

##  2. Installation de Gitea

>Q1 : Qu’est-ce que Gitea ?

Gitea est un logicielle de gestion de code source, libre en Go sous licence MIT, qui fournit un interface web, basé sur le logiciel de gestion de versions Git, pour la gestion du code source. Gitea permet de créer et de gérer des dépôts Git bien évidemment.


>Q2 : À quels logiciels bien connus dans ce domaine peut-on le comparer (en citer au moins 2) ?

Github et Gitlab

>Q3 : Comment faire pour la mettre à jour sans devoir tout reconfigurer ? Essayez en mettant à jour vers la version 1.19.

Il faut juste taper "systemctl restart gitea".



    