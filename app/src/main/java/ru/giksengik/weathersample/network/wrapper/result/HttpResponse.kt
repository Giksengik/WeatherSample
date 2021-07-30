package ru.giksengik.weathersample.network.wrapper.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}