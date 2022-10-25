package com.germanart.germantourguide.api.air

import com.germanart.germantourguide.BuildConfig
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AirQualityServiceAPI {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val NAME_CITY = "berlin"
    }


    @GET("airquality")
    suspend fun getRecipes(
        @Query("city") city : String = NAME_CITY,
    ): Response<AirQualitySingle>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", AirQualityServiceAPI.API_KEY)
            .build()
        return chain.proceed(request)
    }
}