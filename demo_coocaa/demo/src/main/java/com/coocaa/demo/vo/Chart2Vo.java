package com.coocaa.demo.vo;

import java.util.List;

public class Chart2Vo {
    private String projectName;
    private List<String> issueKey;
    private List<Integer> originalEstimate;
    private List<Integer> timeSpent;
    private int status;
    private int sumOriginal;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<Integer> getOriginalEstimate() {
        return originalEstimate;
    }

    public void setOriginalEstimate(List<Integer> originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public List<Integer> getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(List<Integer> timeSpent) {
        this.timeSpent = timeSpent;
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
