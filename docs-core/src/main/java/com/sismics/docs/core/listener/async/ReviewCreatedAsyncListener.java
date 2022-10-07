package com.sismics.docs.core.listener.async;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.sismics.docs.core.dao.ContributorDao;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.event.ReviewCreatedAsyncEvent;
import com.sismics.docs.core.model.context.AppContext;
import com.sismics.docs.core.model.jpa.Contributor;
import com.sismics.docs.core.model.jpa.Review;
import com.sismics.docs.core.util.TransactionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener on review created.
 * 
 * @author alexisaxon
 */
public class ReviewCreatedAsyncListener {
    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(ReviewCreatedAsyncListener.class);
    /**
     * Review created.
     * 
     * @param event Review created event
     */
    @Subscribe
    @AllowConcurrentEvents
    public void on(final ReviewCreatedAsyncEvent event) {
        if (log.isInfoEnabled()) {
            log.info("Review created event: " + event.toString());
        }

        TransactionUtil.handle(() -> {
            // Fetch a review
            Review review = new ReviewDao().getById(event.getReviewId());
            if (review == null) {
                // The review has been deleted since
                return;
            }

            // Add the reviewer
            ContributorDao contributorDao = new ContributorDao();
            Contributor contributor = new Contributor();
            contributor.setDocumentId(event.getDocId());
            contributor.setUserId(event.getUserId());
            contributorDao.create(contributor);
        });
    }
}
