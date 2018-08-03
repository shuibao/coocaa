package com.coocaa.demo.vo;

import java.util.List;

public class Chart2 {
    private String projectName;
    private List<String> issueKey;
    private List<Integer> allOriginalEstimate;
    private List<Integer> allTimeSpent;
    private int resolution;
    private int sumOriginal;

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    private int sumSpent;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(List<String> issueKey) {
        this.issueKey = issueKey;
    }

    public List<Integer> getAllOriginalEstimate() {
        return allOriginalEstimate;
    }

    public void setAllOriginalEstimate(List<Integer> allOriginalEstimate) {
        this.allOriginalEstimate = allOriginalEstimate;
    }

    public List<Integer> getAllTimeSpent() {
        return allTimeSpent;
    }

    public void setAllTimeSpent(List<Integer> allTimeSpent) {
        this.allTimeSpent = allTimeSpent;
    }

    public int getSumOriginal() {
        return sumOriginal;
    }

    public void setSumOriginal(int sumOriginal) {
        this.sumOriginal = sumOriginal;
    }

    public int getSumSpent() {
        return sumSpent;
    }

    public void setSumSpent(int sumSpent) {
        this.sumSpent = sumSpent;
    }
}
