package com.example.exceptions

import java.io.Serializable

class CustomException : RuntimeException, Serializable {
    var someValue: String private set

    companion object {
        private const val serialVersionUID = 7670035637612294165L
    }

    constructor(message: String) : super(message) {
        this.someValue = "This value was set by the CustomException(message: String) constructor."
    }
}