package com.example.gestionrondefrontend;

public class BLEModel {
    private int idBLE ;
    public BLEModel(){}

    public BLEModel(int idBLE, String heurAjout) {
        this.idBLE = idBLE;
        this.heurAjout = heurAjout;
    }

    private String heurAjout;

    public int getIdBLE() {
        return idBLE;
    }

    public void setIdBLE(int idBLE) {
        this.idBLE = idBLE;
    }

    public String getHeurAjout() {
        return heurAjout;
    }

    public void setHeurAjout(String heurAjout) {
        this.heurAjout = heurAjout;
    }
}
