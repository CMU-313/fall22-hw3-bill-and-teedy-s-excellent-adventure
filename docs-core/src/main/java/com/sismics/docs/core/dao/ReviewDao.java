package com.sismics.docs.core.dao;

import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.dao.dto.ReviewDto;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.model.jpa.Review;
import com.sismics.docs.core.util.AuditLogUtil;
import com.sismics.util.context.ThreadLocalContext;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Review DAO.
 * 
 * @author alexisaxon
 */
public class ReviewDao {

    /**
     * Creates a new review instance.
     * 
     * @param review Review
     * @param reviewer Reviewer
     * @return New ID
     */
    public String create(Review review, String userId) {

        // Create the UUID
        review.setReviewId(UUID.randomUUID().toString());
        review.setCreateDate(new Date());
        
        // Create the review
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        em.persist(review);
        
        // Create audit log
        AuditLogUtil.create(review, AuditLogType.CREATE, userId);
        
        return review.getReviewId();
    }

    /**
     * Returns the list of all active reviews.
     *
     * @param offset Offset
     * @param limit Limit
     * @return List of reviews
     */
    public List<Review> findAll(int offset, int limit) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        TypedQuery<Review> q = em.createQuery("select r from Review r where 1 = 1", Review.class);
        return q.getResultList();
    }

    /**
     * Returns the list of all active reviews from a reviewer.
     * 
     * @param userId User ID
     * @return List of reviews
     */
    public List<Review> findByUserId(String userId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        TypedQuery<Review> q = em.createQuery("select r from Review r where r.reviewerId = :userId", Review.class);
        q.setParameter("userId", userId);
        return q.getResultList();
    }

    /**
     * Gets an active review by its ID.
     * 
     * @param id Review ID
     * @return Review
     */
    public Review getById(String id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        TypedQuery<Review> q = em.createQuery("select r from Review r where r.reviewId = :id", Review.class);
        q.setParameter("id", id);
        try {
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Returns the number of reviews.
     *
     * @return Number of reviews
     */
    public long getReviewCount() {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        Query query = em.createNativeQuery("select count(r.REV_ID_C) from T_REVIEW r");
        return ((Number) query.getSingleResult()).longValue();
    }

    public ReviewDto getReview(String id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        StringBuilder sb = new StringBuilder(" select distinct r.REV_ID_C, r.REV_IDDOC_C, r.REV_IDUSER_C, r.REV_GPAScore_C, r.REV_EFFORTSCORE_C, r.REV_EXPERIENCESCORE_C, r.REV_SKILLSCORE_C, r.REV_COMMENTS_C, r.REV_CREATEDATE_D, ");
            sb.append(" from T_Review r ");
            sb.append(" where r.REV_ID_C = :id");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("id", id);

        Object[] o;
        try {
            o = (Object[]) q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        
        ReviewDto reviewDto = new ReviewDto();
        int i = 0;
        reviewDto.setReviewId((String) o[i++]);
        reviewDto.setDocId((String) o[i++]);
        reviewDto.setReviewerId((String) o[i++]);
        reviewDto.setGPAScore((String) o[i++]);
        reviewDto.setEffortScore((String) o[i++]);
        reviewDto.setExperienceScore((String) o[i++]);
        reviewDto.setSkillScore((String) o[i++]);
        reviewDto.setComment((String) o[i++]);
        reviewDto.setCreateTimestamp((long) o[i++]);
        return reviewDto;
    }
}