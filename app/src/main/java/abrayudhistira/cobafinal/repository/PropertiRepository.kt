package abrayudhistira.cobafinal.repository

import abrayudhistira.cobafinal.model.Properti
import abrayudhistira.cobafinal.service.PropertyService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface PropertiRepository {
    suspend fun getProperty() : List<Properti>
    suspend fun getbyidProperti(id_properti : String): Properti
    suspend fun insertProperti(properti: Properti)
    suspend fun editProperti(id_properti: String,properti: Properti)
    suspend fun deleteProperti(id_properti: String)

}
class NetworkPropertiRepository(private val propertyService: PropertyService)
    : PropertiRepository {

    override suspend fun getProperty(): List<Properti> = propertyService.getProperti()
    override suspend fun getbyidProperti(id_properti: String): Properti {
        return propertyService.getbyidProperti(id_properti)
    }

    override suspend fun insertProperti(properti: Properti) {
        propertyService.insertProperti(properti)
    }

    override suspend fun editProperti(id_properti: String, properti: Properti) {
        propertyService.editProperti(id_properti,properti)
    }

    override suspend fun deleteProperti(id_properti: String) {
        try {
            val response = propertyService.deleteProperti(id_properti)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete properti. HTTP Status Code: ${response.code()}")
            }
            else{
                response.message()
                println(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

}