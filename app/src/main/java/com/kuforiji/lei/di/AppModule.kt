package com.kuforiji.lei.di

import com.kuforiji.lei.core.BaseUseCase
import com.kuforiji.lei.datasource.model.FetchUrlRequest
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.datasource.model.UploadUrlRequest
import com.kuforiji.lei.datasource.model.UploadUrlResponse
import com.kuforiji.lei.datasource.remote.*
import com.kuforiji.lei.datasource.repository.AppRepository
import com.kuforiji.lei.datasource.repository.AppRepositoryImpl
import com.kuforiji.lei.domain.FetchAudioUrlUseCase
import com.kuforiji.lei.domain.UploadAudioUrlUseCase
import com.kuforiji.lei.utils.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface AppModule {

    // Data Module
    @Binds
    fun bindAppRepository(appRepositoryImpl: AppRepositoryImpl): AppRepository

    // Remote Module
    @Binds
    fun bindRemoteUpload(
        remoteUploadUrlsRequestImpl: RemoteUploadUrlsRequestImpl
    ): RemoteUploadUrlsRequest

    @Binds
    fun bindUploadAudioUrlUseCase(uploadAudioUrlUseCase: UploadAudioUrlUseCase):
            BaseUseCase.PostUseCase<UploadUrlRequest, UploadUrlResponse>

    @Binds
    fun bindRemoteFetchUrlRequest(
        remoteFetchUrlsRequestImpl: RemoteFetchUrlsRequestImpl
    ): RemoteFetchUrlsRequest

    @Binds
    fun bindFetchUrlsUseCase(
        fetchAudioUrlUseCase: FetchAudioUrlUseCase
    ): BaseUseCase.PostUseCase<FetchUrlRequest, FetchUrlResponse>

    companion object {

        // Network Module Items
        @Provides
        fun provideUploadUrlApi(retrofit: Retrofit): UploadUrlApi = retrofit.create(
            UploadUrlApi::class.java
        )

        @Provides
        fun provideFetchUrlApi(retrofit: Retrofit): FetchUrlApi = retrofit.create(
            FetchUrlApi::class.java
        )

        @Provides
        @Singleton
        fun provideOkHttpClient(): OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        @Provides
        @Singleton
        fun provideRetrofitObject(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit
            .Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

        @Provides
        fun provideMoshiObject(): Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
}