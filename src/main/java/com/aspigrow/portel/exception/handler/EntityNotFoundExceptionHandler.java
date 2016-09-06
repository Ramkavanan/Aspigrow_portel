package com.aspigrow.portel.exception.handler;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.aspigrow.portel.exception.EntityNotFoundException;
import com.aspigrow.portel.model.ErrorResponse;
import com.aspigrow.portel.model.ErrorResponseWrapper;

@Provider
@Component
public class EntityNotFoundExceptionHandler implements ExceptionMapper <EntityNotFoundException> {

    @Override
    public Response toResponse(EntityNotFoundException exception) {

        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponseWrapper(
                new ErrorResponse("NotFoundException",
                        exception.getMessage(), 404))).build();
    }
}
