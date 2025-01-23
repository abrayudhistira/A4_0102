package abrayudhistira.cobafinal.dependeciesinjection

import abrayudhistira.cobafinal.repository.NetworkPropertiRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.service.PropertyService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val DetailPropRepository : PropertiRepository
}

class PropertyContainer : AppContainer {
    private val baseUrl = "http://localhost:3000/api/"

    // Buat logging interceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Buat OkHttpClient dengan logging interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Buat instance Retrofit
    private val json = Json { ignoreUnknownKeys = true }
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient) // Tambahkan OkHttpClient dengan logging interceptor
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    // Buat service Retrofit
    private val propertyService: PropertyService by lazy {
        retrofit.create(PropertyService::class.java)
    }

    // Implementasi repository
    override val DetailPropRepository: PropertiRepository by lazy {
        NetworkPropertiRepository(propertyService)
    }
}