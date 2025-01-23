package abrayudhistira.cobafinal.service

import abrayudhistira.cobafinal.model.Properti
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PropertyService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    // API FOR ENTITY PROPERTI
    @GET("properti/")
    suspend fun getProperti(): List<Properti>//GET LIST PROPERTI

    @GET("properti/{id_properti}")
    suspend fun getbyidProperti(@Path("id_properti")id_properti: String): Properti //GET BY ID

    @POST("properti/add")
    suspend fun insertProperti(@Body properti: Properti) // INSERT PROPERTI

    @PUT("properti/edit/{id_properti}")
    suspend fun editProperti(@Path("id_properti")id_properti: String, @Body properti: Properti) // EDIT PROPERTI

    @DELETE("properti/delete/{id_properti}")
    suspend fun deleteProperti(@Path("id_properti")id_properti: String): Response<Void> //DELETE PROPERTI
}