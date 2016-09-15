/**
 * Company : Aspigrow
 * Date : Sep 15 2016 
 */

package  com.aspigrow.portel.resources;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.aspigrow.portel.exception.CommonRuntimeException;
import com.aspigrow.portel.model.AccountModel;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;
import com.aspigrow.portel.service.AccountService;

/**
 * This Resource class provide the Http request handler functionality 
 * and Response back to the Http claller. This provide the swagger UI 
 * for developer documentation.
 * 
 *  It expose the REST end point to access external system
 *  to communicate with our service through this controller.
 * 
 * @author Ramachandran K <ramakavanan@gmail.com>
 */

@Path("/account")
@Api(value = "Account", description = "This Api to store the user favourite posts , ideas and thoughts "
		+ "to refer in future in user need time to read.")
public class AccountResource {
	
    private AccountService accountService;

    @Autowired
    public AccountResource(AccountService accountService) {
	   this.accountService = accountService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    @ApiOperation(
            value = "Register a new account",
            response = AccountModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response save(@RequestBody AccountModel account) {
    	try {
    		if (account.getExternalId() == null)
        		return Response.status(400)
                        .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Posts / User not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
           		account = accountService.save(account);
        		if(account == null)
        			throw new CommonRuntimeException("Error while debiting account information");
        		return Response.status(200).entity(account).build();
    	} catch(Exception ex) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponseWrapper(
                            new ErrorResponse("Some error occurred", ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))).build();
    	}
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    @ApiOperation(
            value = "Update a new account",
            response = AccountModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response doUpdate(@RequestBody AccountModel account) {
    	try{
    		if (account.getExternalId() == null) {
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Account id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            } else {
            	account = accountService.update(account);
            	if(account == null) 
            		return Response.status(400).entity(null).build();
            	return Response.status(200).entity(account).build();
            }
    	} catch( Exception ex) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponseWrapper(
                            new ErrorResponse("Some error occurred", ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))).build();
    	}
	
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delete/{accountId}")
    @ApiOperation(
            value = "delete a Account based on account id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response deleteById(@PathParam("accountId") String accountId) {
        try {
        	if(accountId == null || accountId.isEmpty()){
        		return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Account id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            }
            return Response.status(200).entity(accountService.delete(accountId)).build();
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
    @Path("/delete")
    @ApiOperation(
            value = "delete a Account based on account id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@RequestBody AccountModel model) {
        try {
        	if(model == null || model.getExternalId().isEmpty()){
                return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Account id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            }
            return Response.status(200).entity(accountService.delete(model)).build();
        }
        catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/account/{accountId}")
    @ApiOperation(
            value = "Resend email code",
            response = AccountModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response getAccount(@PathParam("accountId")String accountId ) {
    	try{
    		if(accountId == null || accountId.isEmpty())
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Account id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
    		 return Response.status(200).entity(accountService.getAccountById(accountId)).build();
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(e)
            .build();
    	}
    }
}
