package com.sismics.docs.core.event;

import com.google.common.base.MoreObjects;

/**
 * Review created event.
 *
 * @author Alexis Axon
 */
public class ReviewCreatedAsyncEvent extends UserEvent {
    /**
     * Review ID.
     */
    private String reviewId;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("reviewId", reviewId)
                .toString();
    }
}