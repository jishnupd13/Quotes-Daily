package com.app.quotes_daily.di

import android.content.Context
import com.app.quotes_daily.data.local.DataStoreManager
import com.app.quotes_daily.data.remote.posts.PostApiService
import com.app.quotes_daily.data.remote.posts.PostApiServiceImpl
import com.app.quotes_daily.data.repository.posts.PostRepositoryImpl
import com.app.quotes_daily.domain.repository.posts.PostRepository
import com.app.quotes_daily.domain.usecase.post.GetPostsUseCase
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
import com.app.quotes_daily.data.remote.quotes.QuotesApiService
import com.app.quotes_daily.data.remote.quotes.QuotesApiServiceImpl
import com.app.quotes_daily.data.repository.quotes.QuotesRepositoryImpl
import com.app.quotes_daily.domain.repository.quotes.QuotesRepository
import com.app.quotes_daily.domain.usecase.quotes.GetQuotesUseCase
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.DefaultRequest
import com.app.quotes_daily.utils.BASE_URL
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(DefaultRequest) {
            url(BASE_URL)
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

    @Provides
    @Singleton
    fun provideQuotesApiService(client: HttpClient): QuotesApiService = QuotesApiServiceImpl(client)

    @Provides
    @Singleton
    fun provideQuotesRepository(api: QuotesApiService): QuotesRepository = QuotesRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideGetQuotesUseCase(repository: QuotesRepository): GetQuotesUseCase = GetQuotesUseCase(repository)

    // Room Database for Favourites
    @Provides
    @Singleton
    fun provideFavouritesDatabase(@ApplicationContext context: Context): com.app.quotes_daily.data.local.room.FavouritesDatabase =
        androidx.room.Room.databaseBuilder(
            context,
            com.app.quotes_daily.data.local.room.FavouritesDatabase::class.java,
            "favourites"
        ).build()

    @Provides
    @Singleton
    fun provideFavouriteDao(db: com.app.quotes_daily.data.local.room.FavouritesDatabase): com.app.quotes_daily.data.local.room.FavouriteDao = db.favouriteDao()

    @Provides
    @Singleton
    fun provideFavouriteRepository(dao: com.app.quotes_daily.data.local.room.FavouriteDao): com.app.quotes_daily.domain.repository.favourite.FavouriteRepository =
        com.app.quotes_daily.data.repository.favourite.FavouriteRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAddToFavouriteUseCase(repository: com.app.quotes_daily.domain.repository.favourite.FavouriteRepository): com.app.quotes_daily.domain.usecase.favourite.AddToFavouriteUseCase =
        com.app.quotes_daily.domain.usecase.favourite.AddToFavouriteUseCase(repository)

    @Provides
    @Singleton
    fun provideRemoveFromFavouriteUseCase(repository: com.app.quotes_daily.domain.repository.favourite.FavouriteRepository): com.app.quotes_daily.domain.usecase.favourite.RemoveFromFavouriteUseCase =
        com.app.quotes_daily.domain.usecase.favourite.RemoveFromFavouriteUseCase(repository)

    @Provides
    @Singleton
    fun provideIsFavouriteUseCase(repository: com.app.quotes_daily.domain.repository.favourite.FavouriteRepository): com.app.quotes_daily.domain.usecase.favourite.IsFavouriteUseCase =
        com.app.quotes_daily.domain.usecase.favourite.IsFavouriteUseCase(repository)

    @Provides
    @Singleton
    fun provideGetFavouritesUseCase(repository: com.app.quotes_daily.domain.repository.favourite.FavouriteRepository): com.app.quotes_daily.domain.usecase.favourite.GetFavouritesUseCase =
        com.app.quotes_daily.domain.usecase.favourite.GetFavouritesUseCase(repository)

    // Firebase Remote Config
    @Provides
    @Singleton
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance().apply {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(3600)
            .build()
        setConfigSettingsAsync(configSettings)
        // Default value
        setDefaultsAsync(mapOf("daily_quote" to "{ \"quote\":\"Do or Die\", \"author\":\"Mahatma Gandhi\" }"))
    }

}
