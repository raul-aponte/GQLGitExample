package com.fungisoft.gqlgitexample.network

import android.util.Log
import com.fungisoft.gqlgitexample.BuildConfig
import com.fungisoft.gqlgitexample.ViewerInfo
import com.fungisoft.gqlgitexample.network.events.ApolloEvent
import com.fungisoft.gqlgitexample.network.events.RetrofitEvent
import com.fungisoft.gqlgitexample.network.request.GQLQuery
import com.fungisoft.gqlgitexample.network.response.UserInfoResponse
import okhttp3.ResponseBody
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Raul Aponte on 26/04/17.
 */

class ApiClient {
    private val TAG = "ApiClient"

    fun getUserInfo() {
        val query = GQLQuery("query { viewer {id login name} }")
        Log.d(TAG, "Query: $query")
        val service = getService()

        val call = service.getUserInfo(query)
        call.enqueue(object : Callback<UserInfoResponse> {
            override fun onResponse(call: Call<UserInfoResponse>, response: Response<UserInfoResponse>) {

                val info = response.body()?.userData?.viewer
                if (info != null) {
                    Log.d(TAG, info.toString())
                    EventBus.getDefault().post(RetrofitEvent(info))
                }
            }

            override fun onFailure(call: Call<UserInfoResponse>, t: Throwable) {
                Log.e(TAG, t.localizedMessage)
            }
        })

    }
    private fun getService(): ApiService {
        val retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(ApiService::class.java)
    }

    fun getRepositories() {
        val query = apolloQuery()

        Log.d(TAG, "Query: $query")
        val service = getService()

        val call = service.getRepositories(query)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val info = response.body().string()
                if (info != null) {
                    Log.d(TAG, info.toString())
                    EventBus.getDefault().post(ApolloEvent(info))
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                Log.e(TAG, t.localizedMessage)
            }
        })
    }

    private fun apolloQuery(): GQLQuery {
        return GQLQuery(
                ViewerInfo.QUERY_DOCUMENT
                        .replace("ViewerInfo", "", false)
                        .replace("__typename", "")
                        .replace("\n", "")
                        .replace("\\s+".toRegex(), " ")
        )
    }
}
