package com.coocaa.demo.vo;

import java.util.Date;

public class IssueVo {

    private int issueId;
    private int resolution;
    private String assignee;
    private String issueKey;
    private Date newCreated;
    private int allOriginalEstimate;
    private int allRemainingEstimate;
    private int allTimeSpent;
    private String sprint;
    private int storyPoint;
    private String projectName;

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getnewCreated() {
        return newCreated;
    }

    public void setnewCreated(Date newCreated) {
        this.newCreated = newCreated;
    }

    public int getAllOriginalEstimate() {
        return allOriginalEstimate;
    }

    public void setAllOriginalEstimate(int allOriginalEstimate) {
        this.allOriginalEstimate = allOriginalEstimate;
    }

    public int getAllRemainingEstimate() {
        return allRemainingEstimate;
    }

    public void setAllRemainingEstimate(int allRemainingEstimate) {
        this.allRemainingEstimate = allRemainingEstimate;
    }

    public int getAllTimeSpent() {
        return allTimeSpent;
    }

    public void setAllTimeSpent(int allTimeSpent) {
        this.allTimeSpent = allTimeSpent;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public int getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(int storyPoint) {
        this.storyPoint = storyPoint;
    }
}
