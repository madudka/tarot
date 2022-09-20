package com.madudka.tarot.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class API {
    private val astro: Retrofit by lazy { initApi() }

    private fun initApi() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://aztro.sameerkumar.website/")
        .build()

    fun provideAstroAPI() : AstroApi = astro.create(AstroApi::class.java)
}