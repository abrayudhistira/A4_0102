package abrayudhistira.cobafinal.service

import abrayudhistira.cobafinal.model.Properti
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface PropertyService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    // API FOR ENTITY PROPERTI
    @GET("listproperti.php")
    suspend fun getProperti(): List<Properti>//GET LIST PROPERTI

    @GET("getbyidProperti.php/{id_properti}")
    suspend fun getbyidProperti(@Query("id_properti")idProperti: Properti): Properti //GET BY ID

    @POST("insertProperti.php")
    suspend fun insertProperti(@Body properti: Properti) // INSERT PROPERTI

    @PUT("editProperti.php")
    suspend fun editProperti(@Query("id_properti")idProperti: Properti, @Body properti: Properti) // EDIT PROPERTI

    @DELETE("deleteProperti.php/{id_properti}")
    suspend fun deleteProperti(@Query("id_properti")idProperti: Properti): Response<Void> //DELETE PROPERTI
}