package org.example;

import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);


    static int lignePlateau = 4;
    static int colonnePlateau = 4;

    public static void main(String[] args) {

        List<Piece> pieces = new ArrayList<>();
        // Blanc - Carré
        pieces.add(new Piece("Blanc", "Carre", "Grand", "Trou"));
        pieces.add(new Piece("Blanc", "Carre", "Grand", "Plein"));
        pieces.add(new Piece("Blanc", "Carre", "Petit", "Trou"));
        pieces.add(new Piece("Blanc", "Carre", "Petit", "Plein"));
        // Blanc - Rond
        pieces.add(new Piece("Blanc", "Rond", "Grand", "Trou"));
        pieces.add(new Piece("Blanc", "Rond", "Grand", "Plein"));
        pieces.add(new Piece("Blanc", "Rond", "Petit", "Trou"));
        pieces.add(new Piece("Blanc", "Rond", "Petit", "Plein"));
        // Noir - Carré
        pieces.add(new Piece("Noir", "Carre", "Grand", "Trou"));
        pieces.add(new Piece("Noir", "Carre", "Grand", "Plein"));
        pieces.add(new Piece("Noir", "Carre", "Petit", "Trou"));
        pieces.add(new Piece("Noir", "Carre", "Petit", "Plein"));
        // Noir - Rond
        pieces.add(new Piece("Noir", "Rond", "Grand", "Trou"));
        pieces.add(new Piece("Noir", "Rond", "Grand", "Plein"));
        pieces.add(new Piece("Noir", "Rond", "Petit", "Trou"));
        pieces.add(new Piece("Noir", "Rond", "Petit", "Plein"));
        // Melanger la liste
        //Collections.shuffle(pieces);
        String[][] plateau = new String[lignePlateau][colonnePlateau];

        // Joueur 1 choisit piece pour joueur 2

        // Choisir la position du plateau

        // remplir plateau

        // Joueur 2 choisit piece pour joueur 1
           tourSuivant(plateau, pieces);
    }
     public static String poserQuestion(String question, List<String> reponsesValides){
        String reponse;
        boolean ok = false;
         do {
             System.out.println(question);
             reponse = scanner.next();

             if (reponsesValides.contains(reponse)) {
                 ok = true;
             } else {
                 System.out.println("Réponse invalide. Veuillez entrer l'une des valeurs suivantes : " + reponsesValides);
             }
         } while (!ok);
        return reponse;
     }
    public static void afficherPlateau(String[][] plateau)
    {
        for(int i = 0; i < lignePlateau; i++){
            for(int j = 0; j < colonnePlateau; j++)
            {
                System.out.print((plateau[i][j] != null ? plateau[i][j] : "   ") + "|");
            }
            System.out.println();
        }
//        tourSuivant(plateau);
    }
    public static void reinitialiserPartie (){
        String[][] plateau = new String[lignePlateau][colonnePlateau];
        afficherPlateau(plateau);

        List<Piece> newPieces = new ArrayList<>();

        // Blanc - Carré
        newPieces.add(new Piece("Blanc", "Carre", "Grand", "Trou"));
        newPieces.add(new Piece("Blanc", "Carre", "Grand", "Plein"));
        newPieces.add(new Piece("Blanc", "Carre", "Petit", "Trou"));
        newPieces.add(new Piece("Blanc", "Carre", "Petit", "Plein"));

        // Blanc - Rond
        newPieces.add(new Piece("Blanc", "Rond", "Grand", "Trou"));
        newPieces.add(new Piece("Blanc", "Rond", "Grand", "Plein"));
        newPieces.add(new Piece("Blanc", "Rond", "Petit", "Trou"));
        newPieces.add(new Piece("Blanc", "Rond", "Petit", "Plein"));

        // Noir - Carré
        newPieces.add(new Piece("Noir", "Carre", "Grand", "Trou"));
        newPieces.add(new Piece("Noir", "Carre", "Grand", "Plein"));
        newPieces.add(new Piece("Noir", "Carre", "Petit", "Trou"));
        newPieces.add(new Piece("Noir", "Carre", "Petit", "Plein"));

        // Noir - Rond
        newPieces.add(new Piece("Noir", "Rond", "Grand", "Trou"));
        newPieces.add(new Piece("Noir", "Rond", "Grand", "Plein"));
        newPieces.add(new Piece("Noir", "Rond", "Petit", "Trou"));
        newPieces.add(new Piece("Noir", "Rond", "Petit", "Plein"));

        // Remplacer l'ancienne liste par la nouvelle

        tourSuivant(plateau, newPieces);
    }
    public static void placerPiece(String[][] plateau, String strPiece, int ligne, int colonne, List<Piece> pieces)
    {

        // je rempli le plateau avec la piece
        if(plateau[ligne][colonne] == null) {
            plateau[ligne][colonne] = "" + strPiece;
        }
        else{
            System.out.println("Cette case : " + ligne + ", " + colonne + " est déjà rempli");
            tourSuivant(plateau, pieces);
        }

        // je separe en 4 mots pour obtenir une piece à partir de strPiece
        String[] strSplitPiece = strPiece.split(" ");

        String couleur = strSplitPiece[0].trim();
        String forme = strSplitPiece[1].trim();
        String taille = strSplitPiece[2].trim();
        String texture = strSplitPiece[3].trim();
        Piece pieceSupprime = null;

        // j'affiche le plateau à jour
        afficherPlateau(plateau);

          if(comparerLigne(plateau, ligne)){
                System.out.println("C'est gagné! ");
                rejouer();
            }
          if(comparerColonne(plateau, colonne)){
                    System.out.println("C'est gagné! ");
                    rejouer();
          }
          if(comparerDiagonalePrincipale(plateau)){
              System.out.println("C'est gagné! ");
              rejouer();
          }
        if(comparerDiagonaleSecondaire(plateau)){
            System.out.println("C'est gagné! ");
            rejouer();
          }
            else{
                // je parcours la liste des pieces
                Iterator<Piece> iterator = pieces.iterator();
                while(iterator.hasNext())
                {
                    Piece piece = iterator.next();
                    if(piece.getCouleur().equals(couleur) && piece.getForme().equals(forme)
                            && piece.getTaille().equals(taille) && piece.getTexture().equals(texture) ) {
                        // je la supprimer
                        iterator.remove();
                        System.out.println("La pièce " + strPiece + " a été supprimée de la liste.");
                        break;
                    }

                }

                tourSuivant(plateau, pieces);
            }
        if (!pieces.contains(new Piece(couleur, forme, taille, texture))) {
            System.out.println("La pièce n'est plus disponible dans la liste.");
        }

    }

    public  static void tourSuivant( String[][] plateau, List<Piece> pieces)
    {   String strPiece;

        System.out.println("Les pieces disponibles : ");
        for(Piece piece : pieces)
        {
            System.out.println(piece);

        }

        String couleur = poserQuestion( "Quel couleur de piece donnez vous à votre adversaire (Blanc/Noir) ?", Arrays.asList("Blanc", "Noir"));
        String forme = poserQuestion( "Quel forme de piece donnez vous à votre adversaire (Carre/Rond) ?", Arrays.asList("Carre", "Rond"));
        String taille = poserQuestion( "Quel taille de piece donnez vous à votre adversaire (Grand/Petit) ?", Arrays.asList("Grand", "Petit"));
        String texture = poserQuestion( "Quel texture de piece donnez vous à votre adversaire (Trou/Plein) ?", Arrays.asList("Trou", "Plein"));
        strPiece = couleur + " " + forme + " " + taille + " " + texture;
        boolean pieceTrouvee = false;
        for(Piece piece : pieces)
        {
            if(piece.getCouleur().equals(couleur) && piece.getForme().equals(forme) &&
                    piece.getTaille().equals(taille) && piece.getTexture().equals(texture) ) {
                System.out.println("ok");
                pieceTrouvee = true;
            }
        }

        if (!pieceTrouvee) {
            System.out.println("La pièce " + strPiece + " n'a pas été trouvée.");
            tourSuivant(plateau,pieces);// Sortir si la pièce n'est pas trouvée
        }

            System.out.println("Ou jouez vous la piece ?");
        String ligne = poserQuestion("Quelle ligne ? ", Arrays.asList("0","1","2","3"));
        String colonne = poserQuestion("Quelle colonne ? ", Arrays.asList("0","1","2","3"));
        int intLigne = Integer.parseInt(ligne);
            int intColonne = Integer.parseInt(colonne);

            placerPiece(plateau, strPiece, intLigne, intColonne, pieces);

    }
    public static boolean comparerLigne(String[][] plateau, int ligne){

        String premierElement = null;
             // [ligne][colonne]
             // [0] = premiere colonne 

         int j = 0;
         while(j < 3)
        {
            if(plateau[ligne][j] != null && !plateau[ligne][j].isEmpty())
            {
                premierElement = plateau[ligne][j];
                break;
            }
            j++;

        }
         // si toutes les cases sont vides
        if (premierElement == null) {
            return false;
        }
             

            // j < plateau[ligne].length c'est pour pas dépasser la longueur de ligne
             for(int k = 0; k < plateau[ligne].length; k++) {

                 if (plateau[ligne][k] == null || plateau[ligne][k].isEmpty()) {
                     return false; // Passer à l'élément suivant
                 }

                 // Extraire les mots de l'élément courant
                 String[] elementActuel = plateau[ligne][k].split(" ");

                 boolean rep = comparerElement(premierElement, elementActuel);
                 if(!rep)
                 {
                     return false;
                 }
             }
        return true;
    }
    public static boolean comparerColonne(String[][] plateau, int colonne)
    {
        String premierElement = null;
        // [ligne][colonne]
        // [0] = premiere colonne

        int i = 0;
        while(i < 3)
        {
            if(plateau[i][colonne] != null && !plateau[i][colonne].isEmpty())
            {
                premierElement = plateau[i][colonne];
                break;
            }
            i++;

        }
        // si toutes les cases sont vides
        if (premierElement == null) {
            return false;
        }
        for(int j = 0; j < plateau.length ; j++ )
        {
            if (plateau[j][colonne] == null || plateau[j][colonne].isEmpty()) {
                return false; // Passer à l'élément suivant
            }

            // Extraire les mots de l'élément courant
            String[] elementActuel = plateau[j][colonne].split(" ");
           boolean rep =  comparerElement(premierElement, elementActuel);
           if(!rep)
           {
               return false;
           }

        }
        return true;
    }
    public static boolean comparerDiagonalePrincipale(String[][] plateau)
    {
      String premierElement = null;
        // diagonale principale
        int x = 0;
        int y = 0;
        premierElement= plateau[0][0];
        if(premierElement == null || premierElement.isEmpty())
        {
            x = x + 1;
            y = y + 1;
            premierElement= plateau[x][y];
        }
        if(premierElement == null)
        {
            return false;
        }

        for(int i = 0 ; i < plateau.length; i++)
        {
           // tout les éléments de la diagonale principale, l'indice ligne et l'indice colonne sont egaux
            if (plateau[i][i] == null || plateau[i][i].isEmpty()) {
                return false; // Passer à l'élément suivant
            }
                String[] elementActuel = plateau[i][i].split(" ");

          boolean rep = comparerElement(premierElement, elementActuel);

              if(!rep)
              {
                  return false;
              }

        }
        return true;
    }

    public static boolean comparerDiagonaleSecondaire(String[][] plateau) {
        String premierElement = null;

        int n = plateau.length;
        for (int i = 0; i < n; i++) {
            if (plateau[i][n - 1 - i] != null && !plateau[i][n - 1 - i].isEmpty()) {
                premierElement = plateau[i][n - 1 - i]; // Premier élément non vide trouvé
                break;
            }
        }
        if (premierElement == null) {
            return false; // Si toute la diagonale est vide, il n'y a rien à comparer
        }

        for (int i = 0; i < n; i++) {


            if (plateau[i][n - 1 - i] == null || plateau[i][n - 1 - i].isEmpty()) {
                return false; // Passer à l'élément suivant
            }
            String[] elementActuel = plateau[i][n - 1 - i].split(" ");

            boolean rep = comparerElement(premierElement, elementActuel);
            // Comparer les caractéristiques avec la première pièce
            if(!rep)
            {
                return false;
            }

        }
        return true;
    }

    public static boolean comparerElements(String[] elements)
    {
        String premierElement =null;

        for(String element : elements)
        {
            if (element != null && !element.isEmpty()) {
                premierElement = element;
                break;
            }
        }
        if(premierElement == null)
            return false;

        String[] premierePiece = premierElement.split(" ");

        boolean couleurCommun = true;
        boolean formeCommun =  true;
        boolean tailleCommun = true;
        boolean textureCommun =  true;

        for(String element : elements)
        {
            // un élement est vide, retourne faux
            if (element == null || element.isEmpty()) return false;
            String[] elementActuel = element.split(" ");

            // Les variables restent true tant que l'élément actuel a la même caractéristique que la première pièce.
// Elles passeront à false si une différence est détectée, et resteront false pour le reste de la boucle.
            couleurCommun &= elementActuel[0].equals(premierePiece[0]);
            formeCommun &= elementActuel[1].equals(premierePiece[1]);
            tailleCommun &= elementActuel[2].equals(premierePiece[2]);
            textureCommun &= elementActuel[3].equals(premierePiece[3]);

        }

        return couleurCommun || formeCommun || tailleCommun || textureCommun;
    }

    public static void rejouer()
    {
       String rep =  poserQuestion("Voulez vous rejouer ?" , Arrays.asList("o","n", "oui","non"));
       if(rep.equals("o") || rep.equals("oui"))
       {
           reinitialiserPartie();
       }
        System.out.println("Fin de jeu");

    }
}