package abrayudhistira.cobafinal.service

import abrayudhistira.cobafinal.model.Pemilik
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemilikService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )

    @GET("pemilik/listpemilik")
    suspend fun getPemilik(): List<Pemilik>

    @GET("pemilik/{id_pemilik}")
    suspend fun getbyidPemilik(@Path("id_pemilik")idPemilik: String): Pemilik

    @POST("pemilik/add")
    suspend fun insertPemilik(@Body pemilik: Pemilik)

    @PUT("pemilik/edit/{id_pemilik}")
    suspend fun editPemilik(@Path("id_pemilik")idPemilik: String, @Body pemilik: Pemilik)

    @DELETE("pemilik/delete/{id_pemilik}")
    suspend fun deletePemilik(@Path("id_pemilik")idPemilik: String): Response<Void>
}