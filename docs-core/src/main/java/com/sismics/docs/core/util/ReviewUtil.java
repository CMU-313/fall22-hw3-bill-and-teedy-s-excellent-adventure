package com.sismics.docs.core.util;

import com.sismics.docs.core.constant.AclType;
import com.sismics.docs.core.constant.PermType;
import com.sismics.docs.core.dao.AclDao;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.model.jpa.Acl;
import com.sismics.docs.core.model.jpa.Review;

/**
 * Review utilities.
 *
 * @author Alexis Axon
 */
public class ReviewUtil {
    /**
     * Create a review and add the base ACLs.
     *
     * @param review Review
     * @param userId User creating the review
     * @return Created review
     */
    public static Review createReview(Review review, String userId) {
        ReviewDao reviewDao = new ReviewDao();
        String reviewId = reviewDao.create(review, userId);

        // Create read ACL
        AclDao aclDao = new AclDao();
        Acl acl = new Acl();
        acl.setPerm(PermType.READ);
        acl.setType(AclType.USER);
        acl.setSourceId(reviewId);
        acl.setTargetId(userId);
        aclDao.create(acl, userId);

        // Create write ACL
        acl = new Acl();
        acl.setPerm(PermType.WRITE);
        acl.setType(AclType.USER);
        acl.setSourceId(reviewId);
        acl.setTargetId(userId);
        aclDao.create(acl, userId);

        return review;
    }
}