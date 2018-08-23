package com.runloopetestapp.paul.runlooptestapp.data.api

import com.runloopetestapp.paul.runlooptestapp.BuildConfig
import com.runloopetestapp.paul.runlooptestapp.data.api.interceptor.ErrorHandlingInterceptor
import com.runloopetestapp.paul.runlooptestapp.data.service.FeedService
import me.toptas.rssconverter.RssConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiFactory {

    private val serviceToUrl = mapOf<Class<*>, String>(
            FeedService::class.java to BuildConfig.FEED_API_URL
    )

    private val urlToService = HashMap<String, Any>()

    private val lock = Any()

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(ErrorHandlingInterceptor())
                .addLogging()
                .build()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
                .addConverterFactory(RssConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttp)
    }

    fun <T : Any> getRetrofit(retrofitClass: Class<T>): T {
        synchronized(lock) {
            val url = serviceToUrl.get(retrofitClass)!!

            var service = urlToService.get(url) as T?
            if (service == null) {
                service = retrofitBuilder
                        .baseUrl(url)
                        .build()
                        .create(retrofitClass) as T
                urlToService.put(url, service)
            }
            return service
        }
    }

}