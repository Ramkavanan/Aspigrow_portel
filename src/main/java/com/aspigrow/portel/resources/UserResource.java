package  com.aspigrow.portel.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspigrow.portel.exception.CommonRuntimeException;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;
import com.aspigrow.portel.model.UserModel;
import com.aspigrow.portel.service.UserService;
import com.aspigrow.portel.util.Validator;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/user")
@Api(value = "User", description = "This api allows user authentication," +
        " registration and updating user related information")
public class UserResource {
	
    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
	   this.userService = userService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/signup")
    @ApiOperation(
            value = "Register a new user",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response doSignup(@RequestBody UserModel user) {
    	try {
    		if (user.getEmailId() == null || user.getPhone() == null || user.getPassword() == null 
        			|| !Validator.isValidemail(user.getEmailId()) || !Validator.isValidPhone(user.getPhone()))
        		return Response.status(400)
                        .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Email / phone not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
           		user = userService.save(user);
        		if(user == null)
        			throw new CommonRuntimeException("Error while debiting user information");
        		return Response.status(200).entity(user).build();
    	} catch(Exception ex) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponseWrapper(
                            new ErrorResponse("Some error occurred", ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))).build();
    	}
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    @ApiOperation(
            value = "Update a new user",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response doUpdate(@RequestBody UserModel user) {
    	try{
    		if (user.getEmailId() == null || user.getExternalId() == null
                    || user.getPhone() == null) {
               // throw new Exception("Phone/Email is not valid");
    		 return Response.status(400).entity(null).build();
            } else {
            	user = userService.save(user);
            	if(user == null) 
            		return Response.status(400).entity(null).build();
            	return Response.status(200).entity(user).build();
            }
    	} catch( Exception ex) {
    		return null;
    	}
	
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    @ApiOperation(
            value = "Log in a User",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response doLogin(@RequestBody UserModel user) {
	System.out.println("Came Resource iumpl");
        try {
        	//ObjectMapper objectMapper = new ObjectMapper();
        	//UserModel user = objectMapper.readValue(json, UserModel.class);
        	if(user.getEmailId() == null || user.getPassword() == null){
                return Response.status(400)
                        .entity(null).build();
            }
            user = userService.login(user);
            if(user == null){
                return Response.status(400)
                        .entity(null).build();
            }

            return Response.status(200).entity(user).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e)
                    .build();
        }
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/verifyEmailCode/{code}/{userId}")
    @ApiOperation(
            value = "Verify email code",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response verifyEmailCode(@PathParam("code") String code, 
    					@PathParam("userId")String userId ) {
    	try{
    		if(userId == null || userId == "")
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "User id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
    		boolean isVerified = userService.verifyEmailCode(code, userId);
    		if(isVerified) {
    			return Response.status(200).entity(isVerified).build();
    		} else {
    			return Response.status(200).entity(isVerified).build();
    		}
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(e)
            .build();
    	}
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/resendCode/{userId}")
    @ApiOperation(
            value = "Resend email code",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response resendCode(@PathParam("userId")String userId ) {
    	try{
    		if(userId == null || userId == "")
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "User id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
    		boolean isVerified = userService.reSendCodeToEmail(userId);
    		if(isVerified) {
    			return Response.status(200).entity(isVerified).build();
    		} else {
    			return Response.status(200).entity(isVerified).build();
    		}
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(e)
            .build();
    	}
    }

    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/sendMail")
    @ApiOperation(
            value = "Update a new user",
            response = UserModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response sendMailNotification(@RequestBody UserModel user) {
    	try{
    		if (user.getEmailId() == null || user.getExternalId() == null
                    || user.getPhone() == null) {
               // throw new Exception("Phone/Email is not valid");
    		 return Response.status(400).entity(null).build();
            } else {
            	if(!userService.sendNotificationMail(user.getEmailId(), null)) 
            		return Response.status(400).entity(null).build();
            	return Response.status(200).entity(user).build();
            }
    	} catch( Exception ex) {
    		return null;
    	}
	
    }
}
