package com.madudka.tarot.model.repository

import com.madudka.tarot.model.LayoutFullModel
import com.madudka.tarot.model.LayoutModel
import com.madudka.tarot.view.App.Companion.db

class LayoutRepository {
    fun getAllLayouts() : List<LayoutModel> = db.getLayoutsDao().selectAllLayouts()
    fun getLayout(id: Int) : LayoutFullModel = db.getLayoutsDao().selectLayout(id)
}