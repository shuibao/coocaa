package com.coocaa.demo.vo;

import java.util.Date;

public class IssueVo {

    private int issueId;
    private int status;
    private String assignee;
    private String issueKey;
    private Date newCreated;
    private int originalEstimate;
    private int remainingEstimate;
    private int timeSpent;
    private int storyPoint;
    private String projectName;

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public Date getNewCreated() {
        return newCreated;
    }

    public void setNewCreated(Date newCreated) {
        this.newCreated = newCreated;
    }

    public int getOriginalEstimate() {
        return originalEstimate;
    }

    public void setOriginalEstimate(int originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public int getRemainingEstimate() {
        return remainingEstimate;
    }

    public void setRemainingEstimate(int remainingEstimate) {
        this.remainingEstimate = remainingEstimate;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
