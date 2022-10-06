package com.sismics.docs.rest.resource;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sismics.docs.core.constant.AclType;
import com.sismics.docs.core.constant.ConfigType;
import com.sismics.docs.core.constant.Constants;
import com.sismics.docs.core.constant.PermType;
import com.sismics.docs.core.dao.*;
import com.sismics.docs.core.dao.criteria.DocumentCriteria;
import com.sismics.docs.core.dao.criteria.TagCriteria;
import com.sismics.docs.core.dao.dto.*;
import com.sismics.docs.core.event.DocumentCreatedAsyncEvent;
import com.sismics.docs.core.event.DocumentDeletedAsyncEvent;
import com.sismics.docs.core.event.DocumentUpdatedAsyncEvent;
import com.sismics.docs.core.event.FileDeletedAsyncEvent;
import com.sismics.docs.core.model.context.AppContext;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.model.jpa.File;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.util.*;
import com.sismics.docs.core.util.jpa.PaginatedList;
import com.sismics.docs.core.util.jpa.PaginatedLists;
import com.sismics.docs.core.util.jpa.SortCriteria;
import com.sismics.rest.exception.ClientException;
import com.sismics.rest.exception.ForbiddenClientException;
import com.sismics.rest.exception.ServerException;
import com.sismics.rest.util.AclUtil;
import com.sismics.rest.util.RestUtil;
import com.sismics.rest.util.ValidationUtil;
import com.sismics.util.EmailUtil;
import com.sismics.util.JsonUtil;
import com.sismics.util.context.ThreadLocalContext;
import com.sismics.util.mime.MimeType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import com.sismics.docs.core.dao.ReviewDao;
import com.sismics.docs.core.dao.dto.ReviewDto;
import com.sismics.docs.core.model.jpa.Review;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.MessageFormat;
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
                .add("reviewerName", reviewDto.getReviewerName())
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
     * @apiSuccess {String} reviews.reviewerName Reviewer Name
     * @apiSuccess {String} reviews.reviewerEmail Reviewer Email
     * @apiSuccess {String} reviews.comment Comment
     * @apiSuccess {String} reviews.GPAScore GPA
     * @apiSuccess {String} reviews.experienceScore Experience
     * @apiSuccess {String} reviews.effortScore Effort
     * @apiSuccess {String} reviews.skillScore Skill
     * @apiSuccess {Number} reviews.createTimeStamp Create Data
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
        List<Review> reviewList = reviewDao.findAll(0, reviewDao.getReviewCount() + 1);
        JsonArrayBuilder reviews = Json.createArrayBuilder();

        JsonObjectBuilder response = Json.createObjectBuilder();

        for (Review review : reviewList) {

            JsonObjectBuilder documentObjectBuilder = Json.createObjectBuilder()
                    .add("reviewId", review.getReviewId())
                    .add("docId", review.getDocId())
                    .add("reviewerName", review.getReviewerName())
                    .add("GPAScore", review.getGPAScore())
                    .add("experienceScore", review.getExperienceScore())
                    .add("effortScore", review.getEffortScore())
                    .add("skillScore", review.getSkillScore())
                    .add("createTimeStamp", review.getCreateTimestamp());
            reviews.add(documentObjectBuilder);
        }

        response.add("total", reviewList.size())
                .add("reviews", reviews);
        
        return Response.ok().entity(response.build()).build();
    }
}
