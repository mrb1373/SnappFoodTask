package com.snapp.network.di

import com.snapp.network.BuildConfig
import com.snapp.network.api.CharacterSearchService
import com.snapp.network.api.FilmService
import com.snapp.network.api.PeopleService
import com.snapp.network.api.PlanetService
import com.snapp.network.api.SpeciesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

    @Provides
    @Singleton
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(loggingInterceptor)
        readTimeout(10, TimeUnit.SECONDS)
        writeTimeout(10, TimeUnit.SECONDS)
        connectTimeout(10, TimeUnit.SECONDS)
    }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
        baseUrl(BASE_URL)
        client(okHttpClient)
        addConverterFactory(GsonConverterFactory.create())
    }.build()


    @Provides
    @Singleton
    fun provideSearchService(retrofit: Retrofit): CharacterSearchService =
        retrofit.create(CharacterSearchService::class.java)

    @Provides
    @Singleton
    fun providePeopleService(retrofit: Retrofit): PeopleService =
        retrofit.create(PeopleService::class.java)

    @Provides
    @Singleton
    fun provideSpeciesService(retrofit: Retrofit): SpeciesService =
        retrofit.create(SpeciesService::class.java)

    @Provides
    @Singleton
    fun providePlanetService(retrofit: Retrofit): PlanetService =
        retrofit.create(PlanetService::class.java)

    @Provides
    @Singleton
    fun provideFilmService(retrofit: Retrofit): FilmService =
        retrofit.create(FilmService::class.java)



}

private const val BASE_URL = "https://swapi.dev/api/"
