package com.madudka.tarot.model.api

import com.madudka.tarot.model.AstroModel
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface AstroApi {

    @POST(".")
    suspend fun getAstroData(
        @Query("sign") sign: String,
        @Query("day") day: String = "Today"
    ) : Response<AstroModel>
}