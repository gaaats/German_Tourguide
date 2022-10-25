package com.germanart.germantourguide.api.recipes

import com.germanart.germantourguide.BuildConfig
import okhttp3.Interceptor
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeServiceAPI {

    companion object {
        const val BASE_URL = "https://api.api-ninjas.com/v1/"
        const val API_KEY = BuildConfig.API_KEY
        const val NAME_FOOD = "sausages"
    }


    @GET("recipe")
    suspend fun getRecipes(
        @Query("query") query : String = NAME_FOOD,
    ): Response<RecipesListNet>


}

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("X-Api-Key", RecipeServiceAPI.API_KEY)
            .build()
        return chain.proceed(request)
    }
}