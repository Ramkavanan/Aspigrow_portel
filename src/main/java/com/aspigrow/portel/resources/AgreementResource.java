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
import com.aspigrow.portel.model.AgreementModel;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;
import com.aspigrow.portel.service.AgreementService;

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

@Path("/agreement")
@Api(value = "Agreement", description = "This Api to store the user favourite posts , ideas and thoughts "
		+ "to refer in future in user need time to read.")
public class AgreementResource {
	
    private AgreementService agreementService;

    @Autowired
    public AgreementResource(AgreementService agreementService) {
	   this.agreementService = agreementService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    @ApiOperation(
            value = "Register a new agreement",
            response = AgreementModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response save(@RequestBody AgreementModel agreement) {
    	try {
    		if (agreement.getExternalId() == null)
        		return Response.status(400)
                        .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Posts / User not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
           		agreement = agreementService.save(agreement);
        		if(agreement == null)
        			throw new CommonRuntimeException("Error while debiting agreement information");
        		return Response.status(200).entity(agreement).build();
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
            value = "Update a new agreement",
            response = AgreementModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response doUpdate(@RequestBody AgreementModel agreement) {
    	try{
    		if (agreement.getExternalId() == null) {
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Agreement id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            } else {
            	agreement = agreementService.update(agreement);
            	if(agreement == null) 
            		return Response.status(400).entity(null).build();
            	return Response.status(200).entity(agreement).build();
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
    @Path("/delete/{agreementId}")
    @ApiOperation(
            value = "delete a Agreement based on agreement id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response deleteById(@PathParam("agreementId") String agreementId) {
        try {
        	if(agreementId == null || agreementId.isEmpty()){
        		return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Agreement id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            }
            return Response.status(200).entity(agreementService.delete(agreementId)).build();
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
            value = "delete a Agreement based on agreement id",
            response = Boolean.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response delete(@RequestBody AgreementModel model) {
        try {
        	if(model == null || model.getExternalId().isEmpty()){
                return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Agreement id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
            }
            return Response.status(200).entity(agreementService.delete(model)).build();
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
    @Path("/agreement/{agreementId}")
    @ApiOperation(
            value = "Resend email code",
            response = AgreementModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response getAgreement(@PathParam("agreementId")String agreementId ) {
    	try{
    		if(agreementId == null || agreementId.isEmpty())
    			return Response.status(400)
    		            .entity(new ErrorResponseWrapper(
                                new ErrorResponse("Data error", "Agreement id not valid", Response.Status.BAD_REQUEST.getStatusCode()))).build();
    		 return Response.status(200).entity(agreementService.getAgreementById(agreementId)).build();
    	} catch(Exception e) {
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(e)
            .build();
    	}
    }
}
