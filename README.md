# quarkus-global-exception-handling

My application is written in Kotlin & configured to run on port 7333. Here are some example `GET` request URLs:
- http://localhost:7333/greetings
- http://localhost:7333/greetings/Dave
- http://localhost:7333/problem

The problem I'm running into can be seen when the following endpoint is called:
- http://localhost:7333/greetings/null

When this endpoint is called, a response body similar to that of the `/problem` route _should_ be returned:
```
{
    "status": 400,
    "detail": "Something bad happened.",
    "instance": null,
    "data": {
        "someValue": "This value was set by the CustomException(message: String) constructor."
    },
    "type": "about:config"
}
```

Instead, the response body looks something like this:
```
{
    "details": "Error id 315741ed-bb70-4bf9-8ebd-3a97a8d7d9ec-3, com.example.exceptions.CustomException: Argument \"name\" contains an invalid value.",
    "stack": "com.example.exceptions.CustomException: Argument \"name\" contains an invalid value.\n\t<STACK TRACE SPAM>"
}
```

A few more notes:
- The `CustomException` class extends `RuntimeException`.
- The `CustomExceptionHandler` class should be handling `CustomException` when it's thrown by the `HelloWorldController.greetingsToPerson()` method.

Am I doing something wrong 
and/or missing a step somewhere?