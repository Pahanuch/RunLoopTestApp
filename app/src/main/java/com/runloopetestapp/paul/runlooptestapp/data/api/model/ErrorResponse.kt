package com.runloopetestapp.paul.runlooptestapp.data.api.model

// TODO need to use in ErrorHandlingInterceptor.kt
class ErrorResponse(val errors: List<ErrorItem>?)

class ErrorItem(val key: String?) {

    val value: List<String>? = null

    override fun equals(other: Any?): Boolean {
        return other is ErrorItem && key.equals(other.key)
    }

    override fun hashCode(): Int {
        return key?.hashCode() ?: 0
    }
}