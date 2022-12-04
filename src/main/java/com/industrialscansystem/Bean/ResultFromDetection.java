package com.industrialscansystem.Bean;

import java.util.List;

public class ResultFromDetection {
    public List<String> position;
    public List<String> flaw_type;
    public List<String> beliefs;

    public List<String> getPosition() {
        return position;
    }

    public void setPosition(List<String> position) {
        this.position = position;
    }

    public List<String> getFlaw_type() {
        return flaw_type;
    }

    public void setFlaw_type(List<String> flaw_type) {
        this.flaw_type = flaw_type;
    }

    public List<String> getBeliefs() {
        return beliefs;
    }

    public void setBeliefs(List<String> beliefs) {
        this.beliefs = beliefs;
    }
}
