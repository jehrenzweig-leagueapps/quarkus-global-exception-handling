package com.example.exceptions.handlers

import com.example.exceptions.CustomException
import com.example.models.ProblemDetails
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider


@Provider
class CustomExceptionHandler : ExceptionMapper<CustomException> {
    override fun toResponse(e: CustomException): Response {
        val problemDetails = ProblemDetails(
            status = Response.Status.BAD_REQUEST,
            detail = "Something bad happened.",
            data = mapOf("someValue" to e.someValue)
        )

        val response = Response
            .status(problemDetails.status)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .entity(problemDetails)
            .build()

        return response
    }
}
