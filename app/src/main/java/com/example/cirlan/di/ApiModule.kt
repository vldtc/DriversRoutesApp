package com.example.cirlan.di

import com.example.cirlan.data.remote.ApiDetails
import com.example.cirlan.data.remote.ApiRequest
import com.example.cirlan.data.repository.driversroutesapi.ApiRepo
import com.example.cirlan.data.repository.driversroutesapi.ApiRepoImplemented
import com.example.cirlan.domain.mapper.DriverModelMapper
import com.example.cirlan.domain.mapper.RouteModelMapper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ApiModule{

    @Provides
    fun provideGson(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun provideRetrofit(
        gson: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BASE_URL)
            .addConverterFactory(gson)
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideApi(
        retrofit: Retrofit
    ): ApiRequest{
        return retrofit.create(ApiRequest::class.java)
    }

    @Provides
    fun provideDriverModelMapper(): DriverModelMapper{
        return DriverModelMapper()
    }

    @Provides
    fun provideRouteModelMapper(): RouteModelMapper{
        return RouteModelMapper()
    }

    @Provides
    fun provideRepo(
        api: ApiRequest,
        driverModelMapper: DriverModelMapper,
        routeModelMapper: RouteModelMapper
    ): ApiRepo {
        return ApiRepoImplemented(api, driverModelMapper, routeModelMapper)
    }
}