package com.sismics.docs.core.event;

import com.google.common.base.MoreObjects;

/**
 * Review created event.
 *
 * @author alexisaxon
 */
public class ReviewCreatedAsyncEvent extends UserEvent {
    /**
     * Review ID.
     */
    private String reviewId;

    /**
     * Document ID.
     */
    private String docId;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String id) {
        this.docId = id;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("reviewId", reviewId)
                .toString();
    }
}