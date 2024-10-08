package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<Piece> pieces = new ArrayList<>();

    static int lignePlateau = 4;
    static int colonnePlateau = 4;

    public static void main(String[] args) {


        Piece piece1 = new Piece();
        Piece piece2 = new Piece();;

        piece1.setCouleur("Blanc");
        piece1.setForme("Carre");
        piece2.setCouleur("Noir");
        piece2.setForme("Rond");
        pieces.add(piece1);
        pieces.add(piece2);

        String[][] plateau = new String[lignePlateau][colonnePlateau];

        // Joueur 1 choisit piece pour joueur 2

        // Choisir la position du plateau

        // remplir plateau

        // Joueur 2 choisit piece pour joueur 1
           tourSuivant(plateau);
    }

    public static void afficherPlateau(String[][] plateau)
    {
        for(int i = 0; i < lignePlateau; i++){
            for(int j = 0; j < colonnePlateau; j++)
            {
                System.out.print((plateau[i][j] != null ? plateau[i][j] : "Vide") + " ");
            }
            System.out.println();
        }
//        tourSuivant(plateau);
    }
    public static void placerPiece(String[][] plateau, String strPiece, int ligne, int colonne)
    {
        plateau[ligne][colonne] = ""+ strPiece;

        afficherPlateau(plateau);
        int i =0;
        while(i < plateau.length)
        {
            if(comparerLigne(plateau, i)){
                System.out.println("C'est gagné! ");
                System.out.print("Voulez vous rejouer ? ");
                String reponse = scanner.nextLine().trim();
                if(reponse.equals("Oui ") || reponse.equals("oui") || reponse.equals("o"))
                {
                    plateau = new String[lignePlateau][colonnePlateau];
                    afficherPlateau(plateau);
                    tourSuivant(plateau);
                }
                else {
                    break;
                }
            }
            else{
                tourSuivant(plateau);
            }
            i++;
        }

    }
    public  static void tourSuivant( String[][] plateau)
    {   String strPiece;
        System.out.println("Les pieces disponibles : ");
        for(Piece piece : pieces)
        {
            System.out.println(piece.getCouleur() + " " + piece.getForme());
           ;

        }
        System.out.println("Quel couleur de piece donnez vous à votre adversaire ?");
        String strCouleur= scanner.nextLine().trim();
        System.out.println("Quel forme de piece donnez vous à votre adversaire ?");
        String strForme= scanner.nextLine().trim();
        strPiece = strCouleur + " " + strForme;
        boolean pieceTrouvee = false;
        for(Piece piece : pieces)
        {
            if(piece.getCouleur().equals(strCouleur) && piece.getForme().equals(strForme)) {
                System.out.println("ok");
                pieceTrouvee = true;
            }
        }

        if (!pieceTrouvee) {
            System.out.println("La pièce " + "remplir" + " n'a pas été trouvée.");
            return; // Sortir si la pièce n'est pas trouvée
        }

            System.out.println("Ou jouez vous la piece ?");
            System.out.println("Quelle ligne ?");
            int ligne = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Quelle colonne ?");
            int colonne = scanner.nextInt();
            scanner.nextLine();
            if((colonne > 3 || ligne > 3))
            {
                System.out.println("Vous ne pouvez pas depasser 3");
                tourSuivant(plateau);
            }

            placerPiece(plateau, strPiece, ligne, colonne);

    }
    public static boolean comparerLigne(String[][] plateau, int ligne){

             // [ligne][colonne]
             // [0] = premiere colonne
             String premierElement = plateau[ligne][0];


             // j < plateau[ligne].length c'est pour pas dépasser la longueur de ligne
             for(int j = 1; j < plateau[ligne].length; j++) {

                 String[] mots = premierElement.split(" ");
                 String mot1 = mots[0];
                 String mot2 = mots[1];
                 if (plateau[ligne][j] == null || !plateau[ligne][j].equals(premierElement)) {
                     return false;
                 }
             }
            return true;

    }
}