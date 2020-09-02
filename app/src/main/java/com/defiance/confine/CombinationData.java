package com.defiance.confine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationData {

    private Map<String,String> map = new HashMap<>();

    private  StringBuilder data = new StringBuilder();

    public List<String> no_risk = Arrays.asList (
            "BL",
            "GL",
            "BLL",
            "GLL",
            "GP"
    );

    public List<String> very_low_risk = Arrays.asList(
            "BM",
            "GM",
            "BML",
            "GML",
            "GLP",
            "GMP",
            "BLS",
            "GLS"
    );

    public List<String> low_risk = Arrays.asList(
            "BH",
            "GH",
            "BLM",
            "GLM",
            "BMS",
            "GMS"
    );

    public List<String> mild_risk = Arrays.asList(
            "BMSLL",
            "GMSLL",
            "BMLL",
            "GMLL",
            "GLPL",
            "BMSLL",
            "GMSLL",
            "BHS",
            "GHS",
            "BLSM",
            "GLSM"
    );

    public List<String> moderate_risk = Arrays.asList(
            "GLPM",
            "GLSL",
            "BLSL",
            "GLPS",
            "BLSML",
            "GLSML",
            "GMPS"

    );

    public List<String> significant_risk = Arrays.asList(
            "BMLLM",
            "GMLLM",
            "GMPLL",
            "BHSL",
            "GHSL",
            "BHSLL",
            "GHSLL",
            "BMH",
            "GMH",
            "BLSH",
            "GLSH",
            "BLSLM",
            "GLSLM",
            "BLLM",
            "GLLM",
            "BMLM",
            "GMLM",
            "BHLL",
            "GHLL",
            "GLH",
            "BLH",
            "GMPSL",
            "GMPSH"
    );


    public List<String> major_risk = Arrays.asList(
            "GMPL",
            "BLHS",
            "GLHS",
            "BLLH",
            "GLLH",
            "BLML",
            "GLML",
            "BMLH",
            "GMLH",
            "BMLH",
            "GMLH",
            "BHL",
            "GHL",
            "BHM",
            "GHM",
            "BHSLM",
            "GHSLM",
            "BHLM",
            "GHLM",
            "BHML",
            "GHML",
            "GHP",
            "GLPLM",
            "GLPML",
            "GMPLH",
            "GMPH",
            "GLPSLL",
            "GMPSLL",
            "GHPS",
            "GHPSL",
            "GHPSM"
    );


    public List<String> high_risk = Arrays.asList(
            "BMLLH",
            "GMLLH",
            "GLPLH",
            "GLPMH",
            "BLLH",
            "GLSLH",
            "BLLH",
            "GLLH",
            "BMSLH",
            "GMSLH",
            "BMHSL",
            "GMSLH",
            "BHSM",
            "GHSM",
            "BHSML",
            "GHSML",
            "GHPL",
            "GLPSLM",
            "GLPSLH",
            "GLPSLML",
            "GLPSMH",
            "GLPSHM"
    );


    public List<String> extreme_risk = Arrays.asList(
            "BMSH",
            "GMSH",
            "BLSH",
            "GLSH",
            "BLSLH",
            "GLSLH",
            "BLSMH",
            "GLSMH",
            "BLSHM",
            "GLSHM",
            "BLMH",
            "GLMH",
            "GLPSH",
            "GLPSM",
            "GLPSL",
            "GLPSLH",
            "GMPSLH",
            "GHPSLL",
            "GHPSLM",
            "GHPSML"
    );


    public List<String> serious_risk = Arrays.asList(
            "GMPLH",
            "GLPH",
            "GLPLH",
            "GLPHM",
            "GHPL",
            "GHPM",
            "GHPLL",
            "GHPLM",
            "GHPML"
    );

    public void addtoString(char c) {
        data.append(c);
    }

    public void removeFromString() {
        data.deleteCharAt(data.length() -1);
    }

    public String getData() {
        return data.toString();
    }

    public ResultData checkRisk() {

        if (no_risk.contains(data.toString()))
            return new ResultData(R.drawable.no_risk, R.string.no_risk, R.string.no_risk_desc);
        else if (very_low_risk.contains(data.toString()))
            return new ResultData(R.drawable.no_risk, R.string.very_low_srisk, R.string.very_low_risk_desc);
        else if (low_risk.contains(data.toString()))
            return new ResultData(R.drawable.no_risk, R.string.low_risk, R.string.very_low_risk_desc);
        else if (mild_risk.contains(data.toString()))
            return new ResultData(R.drawable.mild_risk, R.string.mild_risk, R.string.very_low_risk_desc);
        else if (moderate_risk.contains(data.toString()) || FormActivity.l >= 3)
            return new ResultData(R.drawable.significant_risk, R.string.moderate_risk, R.string.very_low_risk_desc);
        else if (significant_risk.contains(data.toString()))
            return new ResultData(R.drawable.significant_risk, R.string.significant_risk, R.string.significant_risk_desc);
        else if (major_risk.contains(data.toString()))
            return new ResultData(R.drawable.high_risk, R.string.major_risk, R.string.significant_risk_desc);
        else if (high_risk.contains(data.toString()))
            return new ResultData(R.drawable.high_risk, R.string.high_risk, R.string.significant_risk_desc);
        else if (serious_risk.contains(data.toString()) || FormActivity.h >= 2)
            return new ResultData(R.drawable.serious_risk, R.string.serious_risk, R.string.significant_risk_desc);
        else if (extreme_risk.contains(data.toString()) || FormActivity.m >= 2)
            return new ResultData(R.drawable.extreme_risk, R.string.extrme_risk, R.string.significant_risk_desc);
        else
            return null;
    }
}
