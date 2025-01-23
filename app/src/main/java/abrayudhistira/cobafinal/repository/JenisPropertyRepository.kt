package abrayudhistira.cobafinal.repository

import abrayudhistira.cobafinal.model.JenisProperti
import abrayudhistira.cobafinal.service.JenisPropertyService

interface JenisPropertyRepository {
    suspend fun getJenisProperty() : List<JenisProperti>
    suspend fun getbyidJenisProperti(id_jenis : String): JenisProperti
    suspend fun insertJenisProperti(id_jenis: JenisProperti)
    suspend fun editJenisProperti(id_jenis: String,jenisProperti: JenisProperti)
    suspend fun deleteJenisProperti(id_jenis: String)
}
class NetworkJenisPropertyRepository(private val jenisPropertyService: JenisPropertyService)
    : JenisPropertyRepository {
    override suspend fun getJenisProperty(): List<JenisProperti> = jenisPropertyService.getJenisProperti()
    override suspend fun getbyidJenisProperti(id_jenis: String): JenisProperti {
        return jenisPropertyService.getbyidJenisProperti(id_jenis)
    }

    override suspend fun insertJenisProperti(JenisProperti: JenisProperti) {
        jenisPropertyService.insertJenisProperti(JenisProperti)
    }

    override suspend fun editJenisProperti(id_jenis: String, jenisProperti: JenisProperti) {
        jenisPropertyService.editJenisProperti(id_jenis, jenisProperti)
    }

    override suspend fun deleteJenisProperti(id_jenis: String) {
        try {
            val response = jenisPropertyService.deleteJenisProperti(id_jenis)
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