package abrayudhistira.cobafinal.dependeciesinjection

import abrayudhistira.cobafinal.repository.NetworkPropertiRepository
import abrayudhistira.cobafinal.repository.PropertiRepository
import abrayudhistira.cobafinal.service.PropertyService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val DetailPropRepository : PropertiRepository
}

class PropertyContainer : AppContainer {
    private val baseUrl = "https://icoass.com/apiforpam/"
    private val json = Json { ignoreUnknownKeys = true}
    private val retrofit: Retrofit = Retrofit.Builder()

        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val propertyService: PropertyService by lazy {
        retrofit.create(PropertyService::class.java)
    }
    override val DetailPropRepository: PropertiRepository by lazy {
        NetworkPropertiRepository(propertyService)
    }
}