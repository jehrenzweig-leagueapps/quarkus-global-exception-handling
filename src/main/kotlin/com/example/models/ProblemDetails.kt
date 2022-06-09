package com.example.models

class ProblemDetails {
    var status: Int private set
    private var title: String
    var detail: String? private set
    var instance: String? private set
    var data: Map<String, Any?>? private set
    var type: String? private set

    companion object {
        const val DEFAULT_TYPE: String = "about:config"
    }

    constructor(status: javax.ws.rs.core.Response.Status, detail: String? = null, instance: String? = null, data: Map<String, Any?>? = null, type: String? = DEFAULT_TYPE) {
        this.status = status.statusCode
        this.title = status.reasonPhrase
        this.detail = detail
        this.instance = instance
        this.data = data
        this.type = type
    }
}
