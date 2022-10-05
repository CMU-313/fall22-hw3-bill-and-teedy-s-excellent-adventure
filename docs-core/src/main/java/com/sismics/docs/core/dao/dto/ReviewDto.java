package com.sismics.docs.core.dao.dto;

/**
 * Comment DTO.
 *
 * @author Alexis Axon 
 */
public class ReviewDto {
    /**
     * Review ID.
     */
    private String reviewId;

    /**
     * Document ID.
     */
    private String docId;
    
    /**
     * Reviewer name.
     */
    private String reviewerName;
    
    /**
     * Reviewer email.
     */
    private String reviewerEmail;
    
    /**
     * Comment.
     */
    private String comment;

    /**
     * GPA Score.
     */
    private String GPAScore;

    /**
     * Experience score.
     */
    private String experienceScore;

    /**
     * Effort score.
     */
    private String effortScore;

    /**
     * Skill score.
     */
    private String skillScore;
    
    /**
     * Creation date of this review.
     */
    private Long createTimestamp;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String id) {
        this.reviewId = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String id) {
        this.docId = id;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setCreatorName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
    
    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGPAScore() {
        return GPAScore;
    }

    public void setGPAScore(String score) {
        this.GPAScore = score;
    }

    public String getExperienceScore() {
        return experienceScore;
    }

    public void setExperienceScore(String score) {
        this.experienceScore = score;
    }

    public String getEffortScore() {
        return effortScore;
    }

    public void setEffortScore(String score) {
        this.effortScore = score;
    }

    public String getSkillScore() {
        return skillScore;
    }

    public void setSkillScore(String score) {
        this.skillScore = score;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

}