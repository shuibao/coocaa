package com.coocaa.demo.entity;

import java.util.Date;

public class Issue {
    private String summary;
    private String issueKey;
    private Integer issueId;
    private String  issueType;
    private String status;
    private String priority;
    private String resolution;
    private String assignee;
    private String reporter;
    private String creator;
    private Date created;
    private Date updated;
    private Date lastViewed;
    private Date resolved;
    private Integer originalEstimate;
    private Integer remainingEstimate;
    private Integer timeSpent;
    private String workRatio;
    private Integer allOriginalEstimate;
    private Integer allRemainingEstimate;
    private Integer allTimeSpent;
    private String sprint;
    private Integer storyPoint;

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public Integer getOriginalEstimate() {
        return originalEstimate;
    }

    public void setOriginalEstimate(Integer originalEstimate) {
        this.originalEstimate = originalEstimate;
    }

    public Integer getRemainingEstimate() {
        return remainingEstimate;
    }

    public void setRemainingEstimate(Integer remainingEstimate) {
        this.remainingEstimate = remainingEstimate;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public String getWorkRatio() {
        return workRatio;
    }

    public void setWorkRatio(String workRatio) {
        this.workRatio = workRatio;
    }

    public Integer getAllOriginalEstimate() {
        return allOriginalEstimate;
    }

    public void setAllOriginalEstimate(Integer allOriginalEstimate) {
        this.allOriginalEstimate = allOriginalEstimate;
    }

    public Integer getAllRemainingEstimate() {
        return allRemainingEstimate;
    }

    public void setAllRemainingEstimate(Integer allRemainingEstimate) {
        this.allRemainingEstimate = allRemainingEstimate;
    }

    public Integer getAllTimeSpent() {
        return allTimeSpent;
    }

    public void setAllTimeSpent(Integer allTimeSpent) {
        this.allTimeSpent = allTimeSpent;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public Integer getStoryPoint() {
        return storyPoint;
    }

    public void setStoryPoint(Integer storyPoint) {
        this.storyPoint = storyPoint;
    }
}
