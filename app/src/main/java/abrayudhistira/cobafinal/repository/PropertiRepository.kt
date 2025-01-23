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

}
class NetworkPropertiRepository(private val propertyService: PropertyService)
    : PropertiRepository {

    override suspend fun getProperty(): List<Properti> = propertyService.getProperti()
}