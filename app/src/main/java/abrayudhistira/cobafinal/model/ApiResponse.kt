package abrayudhistira.cobafinal.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val status : Boolean,
    val message : String,
    val data : T
)
@Serializable
data class ApiResponseSingle<T>(
    val status : Boolean,
    val message : String,
    val data : T
)