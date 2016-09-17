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
import com.aspigrow.portel.model.OpportunityModel;
import com.aspigrow.portel.model.QuesProcessHeaderModel;
import com.aspigrow.portel.model.QuesProcessLineItemModel;
import com.aspigrow.portel.model.UserModel;
import com.aspigrow.portel.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;

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

@Path("/salesforce")
@Api(value = "Salesforce", description = "This Api to store the user favourite posts , ideas and thoughts "
		+ "to refer in future in user need time to read.")
public class SalesforceResource {
	
    private AccountService accountService;

    @Autowired
    public SalesforceResource(AccountService accountService) {
	   this.accountService = accountService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    @ApiOperation(
            value = "Register a new salesforce",
            response = AccountModel.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Input data error"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Response save(@RequestBody String json) {
    	try {
    		ObjectMapper mapper = new ObjectMapper();
    		AccountModel acctModel = mapper.readValue(json, AccountModel.class);
    		System.out.println(":Account Model ---- "+acctModel.getAccName());
    		OpportunityModel opt = acctModel.getOpportunity();
    		System.out.println(opt.getOppName());
    		QuesProcessHeaderModel qphm = opt.getQuestProcessHeader();
    		System.out.println("Question Process Header === "+qphm.getQuesName());
    		QuesProcessLineItemModel[] qplt = qphm.getQuestProcessLineItems();
    		System.out.println("Ques Linre Item Size === "+qplt.length);
    		
    		return null;
    	} catch(Exception ex) {
    		ex.printStackTrace();
    		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponseWrapper(
                            new ErrorResponse("Some error occurred", ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))).build();
    	}
    }
   
}
