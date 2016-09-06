package com.aspigrow.portel.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aspigrow.portel.exception.UnauthorizedException;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;

@Provider
@Component
public class UnauthorizedExceptionHandler implements ExceptionMapper<UnauthorizedException> {
   
    @Override
    public Response toResponse(UnauthorizedException e) {

        return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponseWrapper(
                new ErrorResponse("Authentication Failed",
                        e.getMessage(), 401))).build();
    }
}
