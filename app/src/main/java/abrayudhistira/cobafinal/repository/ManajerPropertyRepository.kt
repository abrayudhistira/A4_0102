package abrayudhistira.cobafinal.repository

import abrayudhistira.cobafinal.model.ManajerProperti
import abrayudhistira.cobafinal.service.ManajerPropertiService


interface ManajerPropertyRepository {
    suspend fun getManajerProperti() : List<ManajerProperti>
    suspend fun getbyidManajerProperti(idManajerProperti : String): ManajerProperti
    suspend fun insertManajerProperti(idManajerProperti: ManajerProperti)
    suspend fun editManajerProperti(idManajerProperti: String,manajerProperti: ManajerProperti)
    suspend fun deleteManajerProperti(idManajerProperti: String)
}
class NetworkManajerPropertyRepository(private val manajerPropertiService: ManajerPropertiService)
    : ManajerPropertyRepository {
    override suspend fun getManajerProperti(): List<ManajerProperti> = manajerPropertiService.getManajerProperti()
    override suspend fun getbyidManajerProperti(idManajerProperti: String): ManajerProperti {
        return manajerPropertiService.getbyidManajerProperti(idManajerProperti)
    }

    override suspend fun insertManajerProperti(manajerProperti: ManajerProperti) {
        manajerPropertiService.insertManajerProperti(manajerProperti)
    }

    override suspend fun editManajerProperti(idManajerProperti: String, manajerProperti: ManajerProperti) {
        manajerPropertiService.editManajerProperti(idManajerProperti, manajerProperti)
    }

    override suspend fun deleteManajerProperti(idManajerProperti: String) {
        try {
            val response = manajerPropertiService.deleteManajerProperti(idManajerProperti)
            if (!response.isSuccessful) {
                throw Exception("Failed to delete manajer properti. HTTP Status Code: ${response.code()}")
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