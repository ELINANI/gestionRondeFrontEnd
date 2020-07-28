package com.example.gestionrondefrontend;

public class TraceModel {
    private String codeTrace;
    private String wifiName;
    private String codeAgent;

    public TraceModel(String codeTrace, String wifiName, String codeAgent) {
        this.codeTrace = codeTrace;
        this.wifiName = wifiName;
        this.codeAgent = codeAgent;
    }

    public String getCodeTrace() {
        return codeTrace;
    }

    public void setCodeTrace(String codeTrace) {
        this.codeTrace = codeTrace;
    }

    public String getWifiName() {
        return wifiName;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public String getCodeAgent() {
        return codeAgent;
    }

    public void setCodeAgent(String codeAgent) {
        this.codeAgent = codeAgent;
    }
}