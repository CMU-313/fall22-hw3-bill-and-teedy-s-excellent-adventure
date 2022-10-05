package com.sismics.docs.core.dao;

import com.sismics.docs.core.constant.AuditLogType;
import com.sismics.docs.core.constant.PermType;
import com.sismics.docs.core.dao.dto.ReviewDto;
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
        TypedQuery<Review> q = em.createQuery("select r from Review r", Review.class);
        q.setFirstResult(offset);
        q.setMaxResults(limit);
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

}