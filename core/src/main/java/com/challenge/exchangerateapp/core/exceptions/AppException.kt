package com.challenge.exchangerateapp.core.exceptions

open class AppException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

class NetworkException(message: String? = "Network error") : AppException(message)

class DataParseException(message: String? = "Data parsing error") : AppException(message)