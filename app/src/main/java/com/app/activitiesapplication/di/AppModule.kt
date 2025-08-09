package com.app.activitiesapplication.di

import android.content.Context
import com.app.activitiesapplication.data.local.DataStoreManager
import com.app.activitiesapplication.data.remote.posts.PostApiService
import com.app.activitiesapplication.data.remote.posts.PostApiServiceImpl
import com.app.activitiesapplication.data.repository.posts.PostRepositoryImpl
import com.app.activitiesapplication.domain.repository.posts.PostRepository
import com.app.activitiesapplication.domain.usecase.post.GetPostsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import android.util.Log
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.HttpTimeout

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("Ktor", message)
                }
            }
            level = LogLevel.ALL
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000
            connectTimeoutMillis = 15_000
            socketTimeoutMillis = 15_000
        }
    }

    @Provides
    @Singleton
    fun providePostApiService(client: HttpClient): PostApiService = PostApiServiceImpl(client)

    @Provides
    @Singleton
    fun providePostRepository(api: PostApiService): PostRepository = PostRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: PostRepository): GetPostsUseCase = GetPostsUseCase(repository)

    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager = DataStoreManager(context)
}
