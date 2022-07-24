package com.industrialscansystem.Bean;

public class ResultFromDetection {
    public String[][] position;
    public String[][] belief;

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
