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
    private String id;
    
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
     * Creation date of this review.
     */
    private Long createTimestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getGPAScore() {
        return GPAScore;
    }

    public void setComment(String score) {
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
}