package com.rezkalla.blacklane.di


import com.rezkalla.blacklane.network.RetrofitClient
import com.rezkalla.core.data.api.PostApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Named("BASE_URL")
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideHttpClientBuilder(): OkHttpClient.Builder = OkHttpClient().newBuilder()

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = Retrofit.Builder()

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        @Named("BASE_URL") baseURL: String,
        httpClientBuilder: OkHttpClient.Builder,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        retrofitBuilder: Retrofit.Builder
    ): Retrofit {
        return RetrofitClient(
            baseURL,
            httpClientBuilder,
            httpLoggingInterceptor,
            retrofitBuilder
        ).getInstance()
    }

    @Provides
    @Singleton
    fun providePostsApiService(retrofit: Retrofit): PostApiService =
        retrofit.create(PostApiService::class.java)
}