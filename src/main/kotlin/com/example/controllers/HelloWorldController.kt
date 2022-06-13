package com.example.controllers

import com.example.exceptions.CustomException
import com.example.models.ProblemDetails
import io.smallrye.mutiny.Uni
import org.jboss.logging.Logger
import org.jboss.resteasy.reactive.RestPath
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Path("")
class HelloWorldController {
    private val logger = Logger.getLogger(HelloWorldController::class.java.name)

    @GET
    @Path("/greetings")
    @Produces(MediaType.TEXT_PLAIN)
    fun greetings2(): String {
        return "Greetings!"
    }

    @GET
    @Path("/greetings/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    fun greetingsToPerson(@RestPath name: String): Uni<String> {
        if (name == "null") {
            val customException = CustomException("Argument \"name\" contains an invalid value.")
            throw customException
        }

        return Uni.createFrom().item("Greetings, ${name}!")
    }

    @GET
    @Path("/problem")
    @Produces(MediaType.APPLICATION_JSON)
    fun problem(): Uni<ProblemDetails> {
        var problemDetails = ProblemDetails(
            status = Response.Status.BAD_REQUEST,
            detail = "Something bad happened.",
            data = mapOf("someValue" to "TESTING")
        )

        return Uni.createFrom().item(problemDetails)
    }
}
