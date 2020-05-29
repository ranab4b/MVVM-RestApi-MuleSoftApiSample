package com.ranamulesoftapi.bilal.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ranamulesoftapi.bilal.apis.ApiService
import com.ranamulesoftapi.bilal.models.MyDataList
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    private val apiService: ApiService
    private val responseLiveData: MutableLiveData<MyDataList?>
    fun ApiCall(client_id: String?, client_secret: String?) {
        apiService.ListingApi(client_id, client_secret)
                .enqueue(object : Callback<MyDataList?> {
                    override fun onResponse(call: Call<MyDataList?>, response: Response<MyDataList?>) {
                        if (response.body() != null) {
                            responseLiveData.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<MyDataList?>, t: Throwable) {
                        responseLiveData.postValue(null)
                    }
                })
    }

    fun getApiResponseLiveData(): LiveData<MyDataList?> {
        return responseLiveData
    }

    companion object {
        private const val BOOK_SEARCH_SERVICE_BASE_URL = "https://anypoint.mulesoft.com/"
    }

    init {
        responseLiveData = MutableLiveData()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(OAuthInterceptor()).addInterceptor(interceptor).build()
        apiService = Retrofit.Builder()
                .baseUrl(BOOK_SEARCH_SERVICE_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
    class OAuthInterceptor() : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()

            request = request.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()

            return chain.proceed(request)
        }
    }
}
