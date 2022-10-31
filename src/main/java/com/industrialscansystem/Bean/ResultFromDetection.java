package com.industrialscansystem.Bean;

public class ResultFromDetection {
    public String[][] position;
    public String[][] belief;

    public String[][] flawLength;
    public String houdu;
    public String[] edge;

    public String[][] getFlawLength() {
        return flawLength;
    }

    public void setFlawLength(String[][] flawLength) {
        this.flawLength = flawLength;
    }

    public String getHoudu() {
        return houdu;
    }

    public void setHoudu(String houdu) {
        this.houdu = houdu;
    }

    public String[] getEdge() {
        return edge;
    }

    public void setEdge(String[] edge) {
        this.edge = edge;
    }

    public String[][] getPosition() {
        return position;
    }

    public void setPosition(String[][] position) {
        this.position = position;
    }

    public String[][] getBelief() {
        return belief;
    }

    public void setBelief(String[][] belief) {
        this.belief = belief;
    }
}
