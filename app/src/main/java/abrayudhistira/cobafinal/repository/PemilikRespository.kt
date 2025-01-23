package abrayudhistira.cobafinal.repository

import abrayudhistira.cobafinal.model.Pemilik
import abrayudhistira.cobafinal.service.PemilikService


interface PemilikRepository {
    suspend fun getPemilik() : List<Pemilik>
    suspend fun getbyidPemilik(idPemilik : String): Pemilik
    suspend fun insertPemilik(idPemilik: Pemilik)
    suspend fun editPemilik(idPemilik: String,pemilik: Pemilik)
    suspend fun deletePemilik(idPemilik: String)
}
class NetworkPemilikRepository(private val pemilikService: PemilikService)
    : PemilikRepository {
    override suspend fun getPemilik(): List<Pemilik> = pemilikService.getPemilik()
    override suspend fun getbyidPemilik(idPemilik: String): Pemilik {
        return pemilikService.getbyidPemilik(idPemilik)
    }

    override suspend fun insertPemilik(pemilik: Pemilik) {
        pemilikService.insertPemilik(pemilik)
    }

    override suspend fun editPemilik(idPemilik: String, pemilik: Pemilik) {
        pemilikService.editPemilik(idPemilik, pemilik)
    }

    override suspend fun deletePemilik(idPemilik: String) {
        try {
            val response = pemilikService.deletePemilik(idPemilik)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete jenis properti. HTTP Status Code: ${response.code()}")
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