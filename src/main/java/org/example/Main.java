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
        piece2.setCouleur("Noir");
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
        tourSuivant(plateau);
    }
    public  static void tourSuivant( String[][] plateau)
    {    System.out.println("Les pieces disponibles : ");
        for(Piece piece : pieces)
        {
            System.out.println(" Piece "+ piece.getCouleur());

        }
        System.out.println("Quel pièce donnez vous à votre adversaire ?");
        String strPiece= scanner.nextLine().trim();
        boolean pieceTrouvee = false;
        for(Piece piece : pieces)
        {
            if(strPiece.equals(piece.getCouleur())) {
                System.out.println("ok");
                pieceTrouvee = true;
                break;
            }
        }

        if (!pieceTrouvee) {
            System.out.println("La pièce " + strPiece + " n'a pas été trouvée.");
            return; // Sortir si la pièce n'est pas trouvée
        }

            System.out.println("Ou jouez vous la piece ?");
            System.out.println("Quelle ligne ?");
            int ligne = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Quelle colonne ?");
            int colonne = scanner.nextInt();
            scanner.nextLine();

            placerPiece(plateau, strPiece, ligne, colonne);





    }
}