package com.mosh.testgloballogic.utils

class NetworkStatus<out T>(
    val status: Status,
    val data: T?,
    val msg: String?
) {
    companion object {
        fun <T> success(data: T?): NetworkStatus<T> {
            return NetworkStatus(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): NetworkStatus<T> {
            return NetworkStatus(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T? = null): NetworkStatus<T> {
            return NetworkStatus(Status.LOADING, data, null)
        }
    }
}