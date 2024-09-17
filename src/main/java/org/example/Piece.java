package org.example;

public class Piece {

    private String couleur;

    private String forme;

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

    public boolean isTrou() {
        return trou;
    }

    public void setTrou(boolean trou) {
        this.trou = trou;
    }

    private boolean trou;

}
