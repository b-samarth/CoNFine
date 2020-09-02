package com.defiance.confine;

public class ResultData {

    private int image;
    private int risk_level;
    private int risk_description;

    public ResultData(int image, int risk_level, int risk_description) {
        this.image = image;
        this.risk_level = risk_level;
        this.risk_description = risk_description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(int risk_level) {
        this.risk_level = risk_level;
    }

    public int getRisk_description() {
        return risk_description;
    }

    public void setRisk_description(int risk_description) {
        this.risk_description = risk_description;
    }
}
