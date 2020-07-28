package com.example.gestionrondefrontend;

public class PlanningModel {
    private int codePlanning ;
    private String desc;
    private int codeAdmin ;
    public PlanningModel(){}

    public PlanningModel(int codePlanning, String desc, int codeAdmin) {
        this.codePlanning = codePlanning;
        this.desc = desc;
        this.codeAdmin = codeAdmin;
    }

    public int getCodePlanning() {
        return codePlanning;
    }

    public void setCodePlanning(int codePlanning) {
        this.codePlanning = codePlanning;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCodeAdmin() {
        return codeAdmin;
    }

    public void setCodeAdmin(int codeAdmin) {
        this.codeAdmin = codeAdmin;
    }
}
