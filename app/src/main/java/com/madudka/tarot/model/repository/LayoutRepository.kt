package com.madudka.tarot.model.repository

import com.madudka.tarot.model.LayoutFullModel
import com.madudka.tarot.model.LayoutModel
import com.madudka.tarot.view.App.db

class LayoutRepository {
    private val dbAccess = db.getLayoutsDao()

    fun getAllLayouts() : List<LayoutModel> = dbAccess.selectAllLayouts()
    fun getLayout(id: Int) : LayoutFullModel = dbAccess.selectLayout(id)
}