package com.snapattack.dvlog4ja;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("vulnerable")
public class RestAPI {
    
    private static final Logger logger = LogManager.getLogger();
    
    @GET
    @Path("/user")
    public Response user(@HeaderParam("user-agent") String userAgent){
        // Sanitize the user agent string to remove any potential log entry injection
        String sanitizedUserAgent = userAgent.replace("\n", "").replace("\r", "");
        logger.info("User Agent: " + sanitizedUserAgent);
        return Response
                .ok("User Agent: " + sanitizedUserAgent + "\n")
                .build();
    }
    
    @GET
    @Path("/get")
    public Response getParam(@QueryParam("x") String x){
        logger.info("GET: " + x);
        return Response
                .ok("GET '?x=': " + x + "\n")
                .build();
    }
    
    @POST
    @Path("/post")
    public Response postParam(String post){
        String safePost = post.replaceAll("[^a-zA-Z0-9 ]", ""); // Simple sanitization
        logger.info("POST: " + safePost);
        return Response
                .ok("POST: " + safePost + "\n")
                .build();
    }
    
    
}
