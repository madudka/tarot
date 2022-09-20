package com.madudka.tarot.model.repository

import com.madudka.tarot.model.api.API

class AstroRepository constructor(private val api: API) {
    suspend fun getAstroData(sign: String, day: String) = api.provideAstroAPI().getAstroData(sign, day)
}