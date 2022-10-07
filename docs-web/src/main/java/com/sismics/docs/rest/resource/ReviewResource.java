package com.sismics.docs.rest.resource;
import com.sismics.rest.exception.ForbiddenClientException;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.dao.dto.ReviewDto;
import com.sismics.docs.core.model.jpa.Review;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;


/**
 * Document REST resources.
 * 
 * @author coleman-isner-cmu
 */
@Path("/review")
public class ReviewResource extends BaseResource {
    
    /**
     * Returns a Review.
     *
     * @api {get} /review/:id Get a review
     * @apiName GetReview
     * @apiGroup Review
     * @apiParam {String} reviewId Review ID
     * @apiSuccess {String} reviewId ID
     * @apiSuccess {String} docId Document ID 
     * @apiSuccess {String} reviewerName Reviewer Name
     * @apiSuccess {String} reviewerEmail Reviewer Email
     * @apiSuccess {String} comment Comment
     * @apiSuccess {String} GPAScore GPA
     * @apiSuccess {String} experienceScore Experience
     * @apiSuccess {String} effortScore Effort
     * @apiSuccess {String} skillScore Skill
     * @apiSuccess {Number} createTimeStamp Create Data
     * @apiError (client) NotFound Review not found
     * @apiPermission none
     * @apiVersion 1.0
     * 
     * @param reviewId Review ID
     * @return Response
     */
    @GET
    @Path("{id: [a-z0-9\\-]+}")
    public Response get(
            @PathParam("id") String reviewID) {
        authenticate();
        
        ReviewDao reviewDao = new ReviewDao();

        // Do we need a ReviewDto type, like in Document?
        ReviewDto reviewDto = reviewDao.getReview(reviewID);
        if (reviewDto == null) {
            throw new NotFoundException();
        }
            
        JsonObjectBuilder review = Json.createObjectBuilder()
                .add("reviewId", reviewDto.getReviewId())
                .add("docId", reviewDto.getDocId())
                .add("reviewerId", reviewDto.getReviewerId())
                .add("GPAScore", reviewDto.getGPAScore())
                .add("experienceScore", reviewDto.getExperienceScore())
                .add("effortScore", reviewDto.getEffortScore())
                .add("skillScore", reviewDto.getSkillScore())
                .add("createTimeStamp", reviewDto.getCreateTimestamp());

        // I don't think we have to add anything else
        return Response.ok().entity(review.build()).build();
    }

    /**
     * Returns all documents.
     *
     * @api {get} /review/list Get reviews
     * @apiName GetReviewList
     * @apiGroup Review
     * @apiParam {String} limit Limit
     * @apiParam {String} offset Start at this index
     * @apiSuccess {Number} total Total number of reviews
     * @apiSuccess {Object[]} reviews List of reviews
     * @apiSuccess {String} reviews.reviewId ID
     * @apiSuccess {String} reviews.docId Document ID 
     * @apiSuccess {String} reviews.reviewerId Reviewer ID
     * @apiSuccess {String} reviews.GPAScore GPA
     * @apiSuccess {String} reviews.experienceScore Experience
     * @apiSuccess {String} reviews.effortScore Effort
     * @apiSuccess {String} reviews.skillScore Skill
     * @apiSuccess {Number} reviews.createDate Create Date
     * @apiError (client) ForbiddenError Access denied
     * @apiError (server) SearchError Error searching in documents
     * @apiPermission user
     * @apiVersion 1.5.0
     *
     * @param offset Page offset
     * @return Response
     */
    @GET
    @Path("list")
    public Response list(
            @QueryParam("limit") Integer limit,
            @QueryParam("offset") Integer offset) {
        if (!authenticate()) {
            throw new ForbiddenClientException();
        }
        ReviewDao reviewDao = new ReviewDao();
        // There was an issue here of ReviewDao.getTotal or whatever being a long
        List<Review> reviewList = reviewDao.findAll(0, Integer.MAX_VALUE);
        JsonArrayBuilder reviews = Json.createArrayBuilder();

        JsonObjectBuilder response = Json.createObjectBuilder();

        for (Review review : reviewList) {

            JsonObjectBuilder documentObjectBuilder = Json.createObjectBuilder()
                    .add("reviewId", review.getReviewId())
                    .add("docId", review.getDocId())
                    .add("GPAScore", review.getGPAScore())
                    .add("experienceScore", review.getExperienceScore())
                    .add("effortScore", review.getEffortScore())
                    .add("skillScore", review.getSkillScore());
            reviews.add(documentObjectBuilder);
        }

        response.add("total", reviewList.size())
                .add("reviews", reviews);
        
        return Response.ok().entity(response.build()).build();
    }
}
