package com.example.controllers

import com.example.exceptions.CustomException
import com.example.models.ProblemDetails
import io.quarkus.vertx.web.Param
import io.quarkus.vertx.web.Route
import io.smallrye.mutiny.Uni
import org.jboss.logging.Logger
import javax.inject.Singleton
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response


@Singleton
class HelloWorldController {
    private val logger = Logger.getLogger(HelloWorldController::class.java.name)

    @Route(methods = [Route.HttpMethod.GET], path = "/greetings", produces = [MediaType.TEXT_PLAIN])
    fun greetings(): Uni<String> {
        return Uni.createFrom().item("Greetings!")
    }

    @Route(methods = [Route.HttpMethod.GET], path = "/greetings/:name", produces = [MediaType.TEXT_PLAIN])
    fun greetingsToPerson(@Param name: String): Uni<String> {
        if (name == "null") {
            val customException = CustomException("Argument \"name\" contains an invalid value.")
            throw customException
        }

        return Uni.createFrom().item("Greetings, ${name}!")
    }

    @Route(methods = [Route.HttpMethod.GET], path = "/problem", produces = [MediaType.TEXT_PLAIN])
    fun problem(): Uni<ProblemDetails> {
        var problemDetails = ProblemDetails(
            status = Response.Status.BAD_REQUEST,
            detail = "Something bad happened.",
            data = mapOf("someValue" to "TESTING")
        )

        return Uni.createFrom().item(problemDetails)
    }
}
