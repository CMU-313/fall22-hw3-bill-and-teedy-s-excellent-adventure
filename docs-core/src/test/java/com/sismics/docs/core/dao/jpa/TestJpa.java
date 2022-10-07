package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.dao.DocumentDao;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.model.jpa.Review;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.util.TransactionUtil;
import com.sismics.docs.core.util.authentication.InternalAuthenticationHandler;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;

/**
 * Tests the persistance layer.
 * 
 * @author jtremeaux
 */
public class TestJpa extends BaseTransactionalTest {
    @Test
    public void testJpa() throws Exception {
        // Create a user
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("username");
        user.setPassword("12345678");
        user.setEmail("toto@docs.com");
        user.setRoleId("admin");
        user.setStorageQuota(10L);
        String id = userDao.create(user, "me");
        
        TransactionUtil.commit();

        // Search a user by his ID
        user = userDao.getById(id);
        Assert.assertNotNull(user);
        Assert.assertEquals("toto@docs.com", user.getEmail());

        // Authenticate using the database
        Assert.assertNotNull(new InternalAuthenticationHandler().authenticate("username", "12345678"));

        // Create a document
        DocumentDao documentDao = new DocumentDao();
        Document document = new Document();
        document.setUserId(id);
        document.setTitle("Testing Doc");
        document.setLanguage("en");
        document.setCreateDate(new Date());
        String documentId = documentDao.create(document,id);

        TransactionUtil.commit();

        // Verify new Document
        document = documentDao.getById(documentId);
        Assert.assertNotNull(document);
        Assert.assertEquals("Testing Doc", document.getTitle());

        // Create a Review
        ReviewDao reviewDao = new ReviewDao();
        Review review = new Review();
        review.setDocId(documentId);
        review.setReviewerId(id);
        review.setGPAScore("1");
        review.setEffortScore("2");
        review.setExperienceScore("3");
        review.setSkillScore("4");
        String reviewId = reviewDao.create(review, id);

        TransactionUtil.commit();

        // Verify Review
        review = reviewDao.getById(reviewId);
        Assert.assertNotNull(review);
        Assert.assertEquals("1", review.getGPAScore());
        Assert.assertEquals("2", review.getEffortScore());
        Assert.assertEquals("3", review.getExperienceScore());
        Assert.assertEquals("4", review.getSkillScore());
    }
}
