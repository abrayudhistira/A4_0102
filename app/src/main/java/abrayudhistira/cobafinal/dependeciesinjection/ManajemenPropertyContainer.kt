package abrayudhistira.cobafinal.dependeciesinjection

import abrayudhistira.cobafinal.repository.JenisPropertyRepository
import abrayudhistira.cobafinal.repository.ManajerPropertyRepository
import abrayudhistira.cobafinal.repository.NetworkJenisPropertyRepository
import abrayudhistira.cobafinal.repository.NetworkManajerPropertyRepository
import abrayudhistira.cobafinal.repository.NetworkPemilikRepository
import abrayudhistira.cobafinal.repository.NetworkPropertiRepository
import abrayudhistira.cobafinal.repository.PemilikRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.service.JenisPropertyService
import abrayudhistira.cobafinal.service.ManajerPropertiService
import abrayudhistira.cobafinal.service.PemilikService
import abrayudhistira.cobafinal.service.PropertyService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

interface AppContainer {
    val propertyRepository : PropertiRepository
    val jenisPropertyRepository : JenisPropertyRepository
}

class ManajemenPropertyContainer: AppContainer {
    private val baseUrl = "http://172.20.10.7:3000/api/"
    private val json = Json { ignoreUnknownKeys = true }

    // Buat HttpLoggingInterceptor
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Level.BODY akan menampilkan body request dan response
    }

    // Buat OkHttpClient dengan logging interceptor
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Buat Retrofit dengan OkHttpClient yang sudah ditambahkan logging interceptor
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttpClient) // Tambahkan OkHttpClient ke Retrofit
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()

    // Buat service Retrofit
    private val propertyService: PropertyService by lazy {
        retrofit.create(PropertyService::class.java)
    }

    // Implementasi repository
    override val propertyRepository: PropertiRepository by lazy {
        NetworkPropertiRepository(propertyService)
    }

    private val jenisPropertyService: JenisPropertyService by lazy {
        retrofit.create(JenisPropertyService::class.java)
    }

    override val jenisPropertyRepository : JenisPropertyRepository by lazy {
        NetworkJenisPropertyRepository(jenisPropertyService)
    }

    private val pemilikService : PemilikService by lazy {
        retrofit.create(PemilikService::class.java)
    }
    val pemilikRepository : PemilikRepository by lazy {
        NetworkPemilikRepository(pemilikService)
    }

    private val manajerPropertiService : ManajerPropertiService by lazy {
        retrofit.create(ManajerPropertiService::class.java)
    }
    val manajerPropertyRepository : ManajerPropertyRepository by lazy {
        NetworkManajerPropertyRepository(manajerPropertiService)
    }
}