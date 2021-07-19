package starwars.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import starwars.data.BuildConfig
import starwars.data.api.SwApi
import starwars.data.repository.Repository
import starwars.data.util.ContextProviders
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    private val okHttpClient: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY else
                HttpLoggingInterceptor.Level.NONE
        }
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .cache(null)
            .build()
    }

    private val baseUrl: String by lazy {
        BuildConfig.SWAPI_BASE_URL
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideSwApi(): SwApi = retrofit.create(SwApi::class.java)

    @Provides
    @Singleton
    fun provideContextProviders(): ContextProviders = ContextProviders()

    @Provides
    @Singleton
    fun providesRepository(swApi: SwApi, contextProviders: ContextProviders) : Repository = Repository(swApi, contextProviders)

}