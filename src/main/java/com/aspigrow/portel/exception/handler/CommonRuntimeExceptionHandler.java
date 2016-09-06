package com.aspigrow.portel.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aspigrow.portel.exception.CommonRuntimeException;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;

@Provider
@Component
public class CommonRuntimeExceptionHandler implements ExceptionMapper<CommonRuntimeException> {
    
    @Override
    public Response toResponse(CommonRuntimeException e) {

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ErrorResponseWrapper(
                new ErrorResponse("InternalServerError",
                        e.getMessage(), 500))).build();
    }
}
