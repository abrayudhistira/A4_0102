package abrayudhistira.cobafinal.service

import abrayudhistira.cobafinal.model.JenisProperti
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JenisPropertyService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    // API FOR ENTITY PROPERTI
    @GET("jenisProperti/listjenisproperti")
    suspend fun getJenisProperti(): List<JenisProperti>//GET LIST PROPERTI

    @GET("jenisProperti/{id_jenis}")
    suspend fun getbyidJenisProperti(@Path("id_jenis")id_jenis: String): JenisProperti //GET BY ID

    @POST("jenisProperti/add")
    suspend fun insertJenisProperti(@Body jenisProperti: JenisProperti) // INSERT PROPERTI

    @PUT("jenisProperti/edit/{id_jenis}")
    suspend fun editJenisProperti(@Path("id_jenis")id_jenis: String, @Body jenisProperti: JenisProperti) // EDIT PROPERTI

    @DELETE("jenisProperti/delete/{id_jenis}")
    suspend fun deleteJenisProperti(@Path("id_jenis")id_jenis: String): Response<Void> //DELETE PROPERTI
}