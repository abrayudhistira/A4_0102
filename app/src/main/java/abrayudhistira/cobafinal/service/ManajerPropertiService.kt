package abrayudhistira.cobafinal.service

import abrayudhistira.cobafinal.model.ManajerProperti
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ManajerPropertiService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("manajerProperti/")
    suspend fun getManajerProperti(): List<ManajerProperti>

    @GET("manajerProperti/{id_manajer}")
    suspend fun getbyidManajerProperti(@Path("id_manajer")idManajerProperti: String): ManajerProperti

    @POST("manajerProperti/add")
    suspend fun insertManajerProperti(@Body manajerProperti: ManajerProperti)

    @PUT("manajerProperti/edit/{id_manajer}")
    suspend fun editManajerProperti(@Path("id_manajer")idManajerProperti: String, @Body manajerProperti: ManajerProperti)

    @DELETE("manajerProperti/delete/{id_manajer}")
    suspend fun deleteManajerProperti(@Path("id_manajer")idManajerProperti: String): Response<Void>
}