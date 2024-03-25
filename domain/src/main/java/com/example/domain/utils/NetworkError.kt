package com.example.domain.utils

/**
 * Network error wrapper class
 */
sealed class NetworkError {

    /**
     * Unexpected error for example HTTP code - 500 or exception when mapping data
     */
    class Unexpected(val error: String) : NetworkError()

    /**
     * Default errors from server side
     */
    class Api(val error: String) : NetworkError()
}