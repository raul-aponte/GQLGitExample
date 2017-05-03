package com.fungisoft.gqlgitexample.network

import com.fungisoft.gqlgitexample.BuildConfig
import com.fungisoft.gqlgitexample.network.request.GQLQuery
import com.fungisoft.gqlgitexample.network.response.UserInfoResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Raul Aponte on 26/04/17.
 */

interface ApiService {
    @POST("graphql")
    @Headers("Authorization: bearer ${BuildConfig.ACCESS_TOKEN}")
    fun getUserInfo(@Body body: GQLQuery) : Call<UserInfoResponse>

    @POST("graphql")
    @Headers("Authorization: bearer ${BuildConfig.ACCESS_TOKEN}")
    fun getRepositories(@Body query: GQLQuery): Call<ResponseBody>
}
