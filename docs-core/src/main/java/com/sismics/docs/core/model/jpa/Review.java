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
     * Review ID.
     */
    @Id
    @Column(name = "REV_ID_C", length = 36, nullable = false)
    private String reviewId;
    
    /**
     * Document ID.
     */
    @Column(name = "REV_IDDOC_C", length = 36, nullable = false)
    private String documentId;
    
    /**
     * Reviewer ID.
     */
    @Column(name = "REV_IDUSER_C", length = 36, nullable = false)
    private String reviewerId;
    
    /**
     * GPA score.
     */
    @Column(name = "REV_GPASCORE_C", length = 36, nullable = false)
    private String GPAScore;

    /**
     * Effort score.
     */
    @Column(name = "REV_EFFORTSCORE_C", length = 36, nullable = false)
    private String effortScore;

    /**
     * Experience score.
     */
    @Column(name = "REV_EXPERIENCESCORE_C", length = 36, nullable = false)
    private String experienceScore;

    /**
     * Skill score.
     */
    @Column(name = "REV_SKILLSCORE_C", length = 36, nullable = false)
    private String skillScore;

    /**
     * Comments
     */
    @Column(name = "REV_COMMENTS_C", length = 4000, nullable = true)
    private String comments;
    
    /**
     * Creation date.
     */
    @Column(name = "REV_CREATEDATE_D", nullable = false)
    private Date createDate;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String id) {
        this.reviewId = id;
    }

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

    public void setGPAScore(String score) {
        this.GPAScore = score;
    }

    public String getEffortScore() {
        return effortScore;
    }

    public void setEffortScore(String score) {
        this.effortScore = score;
    }

    public String getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(String score) {
        this.experienceScore = score;
    }

    public String getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(String score) {
        this.skillScore = score;
    }
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    // reviews cannot be deleted
    public Date getDeleteDate() {
        return null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("reviewId", reviewId)
                .add("documentId", documentId)
                .add("reviewerId", reviewerId)
                .toString();
    }

    @Override
    public String toMessage() {
        return reviewId;
    }
    
}