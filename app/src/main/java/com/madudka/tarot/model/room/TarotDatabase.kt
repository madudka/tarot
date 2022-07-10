package com.madudka.tarot.model.room

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CardsEntity::class, CardTypesEntity::class,
    CardInfoEntity::class, ImagesClassicEntity::class],
    exportSchema = false, version = 1)
abstract class TarotDatabase : RoomDatabase() {

    abstract fun getDayCardDao() : DayCardDao

    companion object : SingletonHolder<TarotDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, TarotDatabase::class.java, "tarot.db")
            .createFromAsset("database/tarot.db")
            .fallbackToDestructiveMigration()
            .build()
    })
}