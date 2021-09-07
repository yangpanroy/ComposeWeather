package com.roy.composeweather.logic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private const val BASE_URL = "https://api.caiyunapp.com/"
    private const val DEFAULT_TIMEOUT = 6L

    private val builder = OkHttpClient.Builder().apply {
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS) // 默认6秒超时
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }

    private val retrofit = Retrofit.Builder()
        .client(builder.build()) // 设置 OkHttpClient
        .baseUrl(BASE_URL) // 设置 BaseUrl
        .addConverterFactory(GsonConverterFactory.create()) // 设置网络请求和返回值的序列化转换器为 Gson 转换器
        .build()

    /**
     * 此方法调用 Retrofit 对象的 create 方法创建了动态代理实例
     * @param serviceClass 动态代理实例的 java 类型
     */
    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    /**
     * 此方法封装了上面的 create 方法的语法糖，可以用
     * val weatherService = ServiceCreator.create<WeatherService>()
     * 的写法形式替换以下写法：
     * val placeService = ServiceCreator.create(PlaceService::class.java)
     */
    inline fun <reified T> create(): T = create(T::class.java)

}