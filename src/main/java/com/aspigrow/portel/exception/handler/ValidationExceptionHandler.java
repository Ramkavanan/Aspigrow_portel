package com.aspigrow.portel.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.aspigrow.portel.exception.ValidationException;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;

@Component
@Provider
public class ValidationExceptionHandler
        implements ExceptionMapper<ValidationException> {
    
    @Override
    public Response toResponse(ValidationException exception) {

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponseWrapper(
                        new ErrorResponse("Validation Error",
                                exception.getMessage(),
                                400))).build();
    }
}
