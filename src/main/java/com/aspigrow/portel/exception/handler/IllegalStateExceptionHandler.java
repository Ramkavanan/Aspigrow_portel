package com.aspigrow.portel.exception.handler;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;

@Provider
@Component
public class IllegalStateExceptionHandler implements ExceptionMapper<IllegalStateException> {
    
    @Override
    public Response toResponse(IllegalStateException e) {

        return Response.status(Response.Status.CONFLICT).entity(new ErrorResponseWrapper(
                new ErrorResponse("IllegalStateException",
                        e.getMessage(), 409))).build();
    }
}
