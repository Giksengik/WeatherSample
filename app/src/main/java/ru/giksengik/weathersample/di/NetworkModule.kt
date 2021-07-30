package ru.giksengik.weathersample.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import ru.giksengik.weathersample.network.WeatherApiJsonPlaceholder
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  NetworkModule {

    private const val  CONNECT_TIMEOUT = 10L
    private const val WRITE_TIMEOUT = 30L
    private const val READ_TIMEOUT = 10L

    @Provides
    @Reusable
    fun provideHttpClient(apiKeyInterceptor: ApiKeyInterceptor) : OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .addInterceptor(apiKeyInterceptor)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()


    @Provides
    @Reusable
    fun provideRetrofit(httpClient: OkHttpClient) : Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .client(httpClient)
            .build()

    @Provides
    @Singleton
    fun provideWeatherJsonApiPlaceholder(retrofit: Retrofit) : WeatherApiJsonPlaceholder =
        retrofit.create(WeatherApiJsonPlaceholder::class.java)



    class ApiKeyInterceptor @Inject constructor() : Interceptor{

        companion object {
            private const val API_KEY = "b981792569cb7dda696a3239b499a3d7"
        }

        override fun intercept(chain: Interceptor.Chain): Response {

            val newUrl = chain.request().url.newBuilder()
                .addQueryParameter("appid", API_KEY)
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            return chain.proceed(newRequest)
        }

    }
}