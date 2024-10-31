package org.example;

public class Piece {

    private String couleur;

    private String forme;
    private String taille;


    private String texture;

    public Piece(String couleur, String forme, String taille, String texture) {
        this.couleur = couleur;
        this.forme = forme;
        this.taille = taille;
        this.texture = texture;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

   public void setTexture(String texture) {
        this.texture = texture;
   }
    public String getTexture() {
        return texture;
    }
    @Override
    public String toString() {
        return couleur + " " + forme + " " + taille + " " + texture;
    }


}
