package com.example.gestionrondefrontend;

public class AgentModel {
    private int codeAgent ;
    private String nomAgent ;
    private String prenomAgent;
    private String pawdAgent;
    private int codePlanning;
    public AgentModel(){}

    public AgentModel(int codeAgent, String nomAgent, String prenomAgent, String pawdAgent, int codePlanning) {
        this.codeAgent = codeAgent;
        this.nomAgent = nomAgent;
        this.prenomAgent = prenomAgent;
        this.pawdAgent = pawdAgent;
        this.codePlanning = codePlanning;
    }

    public int getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(int codeAgent) {
        this.codeAgent = codeAgent;
    }

    public String getNomAgent() {
        return nomAgent;
    }

    public void setNomAgent(String nomAgent) {
        this.nomAgent = nomAgent;
    }

    public String getPrenomAgent() {
        return prenomAgent;
    }

    public void setPrenomAgent(String prenomAgent) {
        this.prenomAgent = prenomAgent;
    }

    public String getPawdAgent() {
        return pawdAgent;
    }

    public void setPawdAgent(String pawdAgent) {
        this.pawdAgent = pawdAgent;
    }

    public int getCodePlanning() {
        return codePlanning;
    }

    public void setCodePlanning(int codePlanning) {
        this.codePlanning = codePlanning;
    }
}
