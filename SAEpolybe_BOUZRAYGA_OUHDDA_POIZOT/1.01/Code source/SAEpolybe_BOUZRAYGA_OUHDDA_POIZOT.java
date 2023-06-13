class SAEpolybe_BOUZRAYGA_OUHDDA_POIZOT extends Program {

    final int LARGEUR = 5;  // taille du carré (5x5 dans notre cas)
    
    //////////////////////////////////////////////////////////////////////////    
    
    // La fonction String initialiserCarre() retourne une chaine de caractères contenant le carré de Polybe (version de base, sans clé, c'est-à-dire la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ", le V et le W sont "fusionnés" en V)
    String initialiserCarreSimple(){
        return "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction void afficherCarre(String carre) affiche le carré de Polybe carrz passé en paramètre comme illustré dans l'exemple ci-après.
    // Par exemple : si le carré passé en paramètre est : "ABCDEFGHIJKLMNOPQRSTUVXYZ", la fonction devra afficher : 
    //  |0 1 2 3 4
    // ------------
    // 0|A B C D E
    // 1|F G H I J
    // 2|K L M N O 
    // 3|P Q R S T 
    // 4|U V X Y Z

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    void afficherCarre(String carre){
        int i=0;
        String c=" |";
        for (int cpt=0; cpt<LARGEUR; cpt=cpt+1) {
            c=c+""+cpt+" ";
        }
        c=c+"\n--";
        for (int cpt=0; cpt<LARGEUR; cpt=cpt+1) {
            c=c+"--";
        }
        for (int cpt=0; cpt<length(carre); cpt=cpt+1) {
                if ((cpt%LARGEUR)==0) {
                    c=c+"\n"+cpt/LARGEUR+"|"+charAt(carre, cpt)+" ";
                } else {
                    c=c+charAt(carre, cpt)+" ";
                }
        }
        println(c);
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderLettre (String carre, char lettre) retourne une chaîne de 2 caractères (2 entiers entre 0 inclus et LARGEUR exclus) 
    // contenant l'encodage du caractère lettre passé en paramètre en considérant le carré de Polybe carre également passé en paramètre.
    // Par exemple :
    // si on considère le carré de Polybe sans clé (càd le carré ABCDE représenté par la chaine "ABCDEFGHIJKLMNOPQRSTUVXYZ") : 
    //                                                           FGHIJ
    //                                                           KLMNO                   
    //                                                           PQRST 
    //                                                           UVWYZ
    //      'A' est codé "00"
    //      'B' est codé "01"
    //      'F' est codé "10"
    //      'V' est codé "41"
    //      'W' est codé "41"
    //      'Z' est codé "44"
    //      'P' est codé "30"

    // si on considère le carré de Polybe donné par la chaine "AZERTYUIOPQSDFGHJKLMXCVBN" :
    //      'A' est codé "00"
    //      'B' est codé "43"
    //      'Z' est codé "01"
    
    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)

    // Indication : pensez à la division entière et au modulo

    String coderLettre(String carre, char lettre){
        int ligne =0;
        int colonne =0;
        String rien;
        for (int i=0; i<length(carre); i=i+1){
            if (charAt(carre, i) == lettre) {
                ligne = i/(LARGEUR);
                colonne = i%LARGEUR;
            }
        }
        return ""+ligne+""+colonne;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String coderMessage (String carre, String message) retourne une chaîne de caractères contenant l'encodage de la chaîne de caractères message passé en paramètre avec le carré de Polybe carre donné en paramètre.
    // Chaque paire d'entiers (compris entre 0 et 4) correspondant à chaque lettre sera séparée de la suivante par un espace.
    // Pensez à utiliser la fonction coderLettre.
    // Par exemple, si le carré considéré est celui sans clé ("ABCDEFGHIJKLMNOPQRSTUVXYZ") et le message à coder est "BONJOUR" alors le résultat attendu est "01 24 23 14 24 40 32 "

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message passé en paramètre est valide (càd constitué uniquement des 26 lettres de l'alphabet en majuscule)

    String coderMessage(String carre, String message){
        String resultat= "";
        for (int i=0; i<length(message); i=i+1){
            resultat=resultat+(coderLettre(carre, charAt(message, i)))+' ';
        }
        return resultat;
    }
    //////////////////////////////////////////////////////////////////////////
    
    // La fonction String decoderMessage (String carre, String messageCode) retourne une chaîne de caractères contenant le décodage de la chaîne de caractère messageCode avec le carré de Polybe carre donné en paramètre.
    // Par exemple, si carre = "ABCDEFGHIJKLMNOPQRSTUVXYZ" et messageCode = "01 24 23 14 24 40 32 " alors le résultat attendu est "BONJOUR"

    // NB : On considère dans cette fonction que le carré passé en paramètre est valide (càd constitué de 25 lettres distinctes en majuscule, le W se substituant en V)
    // NB : On considère dans cette fonction que le message codé passé en paramètre est valide (càd constitué de paires d'entiers compris entre 0 et LARGEUR-1 inclus et séparées par un espace)

    String decoderMessage(String carre, String messageCode){
        int ligne;
        int colonne;
        int temp;
        String resultat = "";
        for (int i=0; i<length(messageCode); i=i+3) {
            ligne = (((int) charAt(messageCode, i))-48)*LARGEUR;
            colonne = ((int) charAt(messageCode, i+1)-48);
            temp = ligne+colonne;
            resultat=resultat+charAt(carre, temp);
        }
        //lettre = charAt(carre, (ligne  +  colonne));
        //resultat = resultat+lettre;
        
        return resultat;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction boolean estPresent(String mot, char lettre) retourne True si le caractère lettre est dans mot, faux sinon.
    // Par exemple :
    //      si mot = "BONJOUR" et lettre = 'B' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'R' alors le résultat de la fonction est True
    //      si mot = "BONJOUR" et lettre = 'M' alors le résultat de la fonction est False
    
    boolean estPresent(String mot, char lettre){
        boolean present = false ;
        for (int i =0 ; i <length(mot) ; i++) {
            if (charAt(mot,i) == lettre) {
                present=true;
            }
        }
        return present;
    }
  
    //////////////////////////////////////////////////////////////////////////
  
    // La fonction String initialiserCarreAvecCle(String cle) retourne une chaine de caractères contenant le carré de Polybe amélioré en considérant la clé passée en paramètre.
    // Pensez à utiliser la fonction estPresent
    // Par exemple, si cle = "BONJOUR" alors le résultat attendu est : "BONJURACDEFGHIKLMPQSTVXYZ"
    // Par exemple, si cle = "BUTINFORMATIQUE" alors le résultat attendu est : "BUTINFORMAQECDGHJKLPSVXYZ"

    // NB : On considère dans cette fonction que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule, le W se substituera en V)

    String initialiserCarreAvecCle(String cle){
        String carre = "";
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
        char car;
        for (int i=0; i<length(cle); i=i+1){
            if (charAt(cle, i)=='W') car='V';
            else car = charAt(cle, i);

            if (!estPresent(carre, car)){
                carre=carre+car;
            }
        }
        for (int i=0; i<length(alphabet); i=i+1){
            if (!estPresent(carre, charAt(alphabet, i))){
                carre=carre+charAt(alphabet, i);
            }
        }
        
        return carre;
    }
    
    //////////////////////////////////////////////////////////////////////////
    // LES FONCTIONS QUI SUIVENT SONT DES FONCTIONS DE VERIFICATION DE SAISIE
    //////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////println
    //  si c='B', la fonction retourne true

    boolean estLettreMajuscule(char c){
        return (c>='A' && c<='Z');
    }
   
    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estCleValide vérifie que la clé passée en paramètre est valide (càd constituée uniquement de lettres de l'alphabet en majuscule)
    // Par exemple :
    //  si cle="BUTINFORMATIQUE", la fonction retourne true
    //  si cle="BUTINF ORMATIQUE", la fonction retourne false
    //  si cle="BUTINFORMATIQUE!", la fonction retourne false
    //  si cle="ButInformatique", la fonction retourne false
   
    boolean estCleValide(String cle){
        boolean b = true;
        boolean f = true;
        int i=0;
        while (i<length(cle)) {
            if (!(charAt(cle, i)>='A' && charAt(cle, i)<='Z')) {
                f=false;
            } else b=true;
            i=i+1;
        }
        return (b && f);
    }

    //////////////////////////////////////////////////////////////////////////
   
    // La fonction estChiffreOK vérifie que le chiffre passé en paramètre est valide (càd est un entier compris entre 0 et LARGEUR-1)

    boolean estChiffreOK(int chiffre){
        return (chiffre>=0 && chiffre <=LARGEUR-1);
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageCodeValide vérifie que le message codé passé en paramètre est valide (càd constituée uniquement de paires d'entiers compris entre 0 et LARGEUR-1 et que chaque paire est séparée de la suivante par un espace, et un espace final)
    // Par exemple :
    //  si messageCode=""01 24 23 14 24 40 32 ", la fonction retourne true
    //  si messageCode=""01 24 23 14 24 40 32", la fonction retourne false
    //  si messageCode=""01 24 23 14 24 40 3", la fonction retourne false
    //  si messageCode=""01 25 23 14 24 40 32 ", la fonction retourne false
    //  si messageCode=""01242314244032", la fonction retourne false

    boolean estMessageCodeValide(String messageCode){
        boolean b = true;
        int a, b2;
        char c;
        if ((length(messageCode)%2)==0 || !(charAt(messageCode, length(messageCode)-1)==' ')) {
            b=false;
        } else {
            for (int i=0; i<length(messageCode); i=i+3) {
                a=(int) charAt(messageCode, i)-48;
                b2=(int) charAt(messageCode, i+1)-48;
                c=charAt(messageCode, i+2);
                if (!((estChiffreOK(a)) && (estChiffreOK(b2)) && c==' ')){
                    b=false;
                }
            }
        }
        return b;
    }

    //////////////////////////////////////////////////////////////////////////
    
    // La fonction estMessageValide vérifie que le message passé en paramètre est valide (càd constitué uniquement de lettres de l'alphabet en majuscule)

    boolean estMessageValide(String message){
        boolean b = true;
        boolean f = true;
        int i=0;
        while (i<length(message)) {
            if (!(charAt(message, i)>='A' && charAt(message, i)<='Z')) {
                f=false;
            } else b=true;
            i=i+1;
        }
        return (b && f);
    }

    //////////////////////////////////////////////////////////////////////////
    // PROGRAMME PRINCIPAL
    //////////////////////////////////////////////////////////////////////////
    // Ecrire un programme principal qui :
    // 1. affiche un message d’introduction `a l’utilisateur
    // 2. affiche un message `a l’utilisateur demandant s’il veut utiliser une cl ́e ?
    // 3. lit la r ́eponse de l’utilisateur
    // 4. si l’utilisateur a r ́epondu oui, demande et lit la cl ́e souhait ́ee
    // 5. affiche le carr ́e de Polybe (g ́en ́erique (c`ad sans cl ́e) ou avec cl ́e selon la r ́eponse pr ́ec ́edente de l’utilisateur)
    // 6. tant que la r ́eponse n’est pas 0, affiche un menu et demande `a l’utilisateur de saisir un entier (0 ou 1 ou 2 ou 3) pour :
    // 0. QUITTER
    // 1. CODER UN MESSAGE
    // 2. DECODER UN MESSAGE
    // 3. MODIFIER LE MODE AVEC/SANS CLE
    // puis agit en cons ́equence.
    // NB : si et tant qu’une saisie de l’utilisateur n’est pas correcte, il faut la redemander (que ce soit pour la cl ́e, le message `a coder, le message `a d ́ecoder ou le choix dans le menu)
    void algorithm(){
        String cle = "";
        String carre  = "";
        String message  = "";
        String messageCode = "";
        int choix, choix2;
        choix2 = -1;
        boolean test;
        println("SAE1.01 - Le carré de Polybe");
        do {
            do {
                println("\nVoulez vous uiliser une clé ? \n   1 - Oui \n   2 - Non \n");
                print("Votre choix : ");
                choix = readInt();
            } while (choix<1 && choix>2);

            if (choix==1) {
                do {
                    print("Entrez votre clé (en majuscule) : ");
                    cle = readString(); 
                } while (!estCleValide(cle));
                carre = initialiserCarreAvecCle(cle);
                afficherCarre(carre);
            } else if (choix==2) {
                carre = initialiserCarreSimple();
                afficherCarre(carre);
            }

            

            do {
                println("\nQuelle action voulez vous effectuer ? : \n     0 - QUITTER\n     1 - CODER UN MESSAGE \n     2 - DECODER UN MESSAGE \n     3 - MODIFIER LE MODE AVEC/SANS CLE");
                print("Entrez votre choix : ");
                choix2 = readInt();

                if (choix2==1){
                    print("Message à coder : ");
                    message = readString(); 
                    messageCode = coderMessage(carre, message);
                    println("\n Message codé : \n      "+messageCode);
                } else if (choix2==2){
                    print("Message à décoder : ");
                    messageCode = readString(); 
                    message = decoderMessage(carre, messageCode);
                    println("\n Message décodé : \n      "+message);
                }
            } while (choix2!=0 && choix2!=3);
            //afficherCarre(initialiserCarreSimple());
            //println(estMessageCodeValide("01242314244032"));
        } while (choix2==3);
    }
    //////////////////////////////////////////////////////////////////////////  
}
