package com.sismics.docs.core.model.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

/**
 * Review entity.
 * 
 * @author Alexis Axon
 */
@Entity
@Table(name = "T_REVIEW")
public class Review implements Loggable {
    
    /**
     * Document ID.
     */
    @Column(name = "DOC_ID_C", length = 36, nullable = false)
    private String documentId;
    
    /**
     * Reviewer ID.
     */
    @Column(name = "REVIEWER_ID", length = 36, nullable = false)
    private String reviewerId;
    
    /**
     * GPA score.
     */
    @Column(name = "SCORE_GPA", length = 36, nullable = false)
    private String GPAScore;

    /**
     * Effort score.
     */
    @Column(name = "SCORE_EFFORT", length = 36, nullable = false)
    private String effortScore;

    /**
     * Experience score.
     */
    @Column(name = "SCORE_EXPERIENCE", length = 36, nullable = false)
    private String experienceScore;

    /**
     * Skill score.
     */
    @Column(name = "SCORE_SKILL", length = 36, nullable = false)
    private String skillScore;

    /**
     * Comments
     */
    @Column(name = "COMMENTS", length = 4000, nullable = false)
    private String comments;
    
    /**
     * Creation date.
     */
    @Column(name = "CREATE_ID", nullable = false)
    private Date createDate;

    public String getDocId() {
        return documentId;
    }

    public void setDocId(String id) {
        this.documentId = id;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String id) {
        this.reviewerId = id;
    }

    public String getGPAScore() {
        return GPAScore;
    }

    public void setGPAScore(score) {
        this.GPAScore = score;
    }

    public String getEffortScore() {
        return effortScore;
    }

    public void setEffortScore(score) {
        this.effortScore = score;
    }

    public String getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(score) {
        this.experienceScore = score;
    }

    public String getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(score) {
        this.skillScore = score;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
}